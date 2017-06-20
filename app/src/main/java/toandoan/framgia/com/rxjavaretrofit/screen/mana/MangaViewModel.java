package toandoan.framgia.com.rxjavaretrofit.screen.mana;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.BR;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.screen.mangaDetail.MangaDetailActivity;
import toandoan.framgia.com.rxjavaretrofit.utils.navigator.Navigator;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Exposes the data to be used in the Mana screen.
 */

public class MangaViewModel extends BaseObservable implements MangaContract.ViewModel {

    private MangaContract.Presenter mPresenter;
    private MangaAdapter mAdapter;
    private Navigator mNavigator;

    private boolean mIsLoadMore;
    private int mProgressbarVisible = VISIBLE;

    private RecyclerView.OnScrollListener mScrollListenner = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (dy <= 0) {
                return;
            }

            LinearLayoutManager layoutManager =
                    (LinearLayoutManager) recyclerView.getLayoutManager();

            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

            if (!mIsLoadMore && (visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                setLoadMore(true);
                mPresenter.onLoadMoreData();
            }
        }
    };

    public MangaViewModel(Navigator navigator) {
        mAdapter = new MangaAdapter(this);
        mNavigator = navigator;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(MangaContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Bindable
    public MangaAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(MangaAdapter adapter) {
        mAdapter = adapter;
        notifyPropertyChanged(BR.adapter);
    }

    @Override
    public void onGetMangaSuccess(List<Manga> mangas) {
        mAdapter.updateManga(mangas);
        setLoadMore(false);
    }

    @Override
    public void onGetMangaFailed(String msg) {
        mNavigator.showToast(msg);
        setLoadMore(false);
    }

    @Override
    public void hideProgressBar() {
        setProgressbarVisible(GONE);
    }

    @Override
    public void showProgressBar() {
        setProgressbarVisible(VISIBLE);
    }

    @Override
    public void onItemClick(Manga manga) {
        mNavigator.startActivity(
                MangaDetailActivity.getInstance(mNavigator.getContext(), manga));
    }

    @Bindable
    public boolean isLoadMore() {
        return mIsLoadMore;
    }

    public void setLoadMore(boolean loadMore) {
        mIsLoadMore = loadMore;
        notifyPropertyChanged(BR.loadMore);
    }

    @Bindable
    public RecyclerView.OnScrollListener getScrollListenner() {
        return mScrollListenner;
    }

    public void setScrollListenner(RecyclerView.OnScrollListener scrollListenner) {
        mScrollListenner = scrollListenner;
        notifyPropertyChanged(BR.scrollListenner);
    }

    @Bindable
    public int getProgressbarVisible() {
        return mProgressbarVisible;
    }

    public void setProgressbarVisible(int progressbarVisible) {
        mProgressbarVisible = progressbarVisible;
        notifyPropertyChanged(BR.progressbarVisible);
    }
}
