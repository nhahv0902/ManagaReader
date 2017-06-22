package toandoan.framgia.com.rxjavaretrofit.screen.mana.allmanga;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.BR;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.screen.mangaDetail.MangaDetailActivity;
import toandoan.framgia.com.rxjavaretrofit.utils.navigator.Navigator;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Exposes the data to be used in the AllManga screen.
 */

public class AllMangaViewModel extends BaseObservable implements AllMangaContract.ViewModel {

    private AllMangaContract.Presenter mPresenter;
    private AllMangaAdapter mAdapter;
    private Navigator mNavigator;
    private int mProgressVisible = VISIBLE;
    private int mEmptyViewVisible = GONE;

    public AllMangaViewModel(Navigator navigator) {
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
    public void setPresenter(AllMangaContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showProgressBar() {
        setProgressVisible(VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        setProgressVisible(GONE);
    }

    @Override
    public void onGetMangaSucess(List<AllMangaAdapter.MangaModel> mangas) {
        mAdapter.updateData(mangas);
        setEmptyViewVisible(mangas == null || mangas.size() == 0 ? VISIBLE : GONE);
    }

    @Override
    public void onGetMangaFailed(String msg) {
        mNavigator.showToast(msg);
    }

    @Override
    public void onItemClick(Manga source) {
        mNavigator.startActivity(MangaDetailActivity.getInstance(mNavigator.getContext(), source));
    }

    @Override
    public void searchMangaByName(String key) {
        mPresenter.searchMangaByName(key);
        mAdapter.clear();
    }

    public AllMangaAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(AllMangaAdapter adapter) {
        mAdapter = adapter;
    }

    @Bindable
    public int getProgressVisible() {
        return mProgressVisible;
    }

    public void setProgressVisible(int progressVisible) {
        mProgressVisible = progressVisible;
        notifyPropertyChanged(BR.progressVisible);
    }

    @Bindable
    public int getEmptyViewVisible() {
        return mEmptyViewVisible;
    }

    public void setEmptyViewVisible(int emptyViewVisible) {
        mEmptyViewVisible = emptyViewVisible;
        notifyPropertyChanged(BR.emptyViewVisible);
    }
}
