package toandoan.framgia.com.rxjavaretrofit.screen.downloadmangak;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.android.databinding.library.baseAdapters.BR;
import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.screen.mangaDetail.MangaDetailActivity;
import toandoan.framgia.com.rxjavaretrofit.utils.navigator.Navigator;

/**
 * Exposes the data to be used in the DownloadMangak screen.
 */

public class DownloadMangakViewModel extends BaseObservable
        implements DownloadMangakContract.ViewModel {

    private DownloadMangakContract.Presenter mPresenter;
    private DownloadMangakAdapter mAdapter;
    private Navigator mNavigator;

    public DownloadMangakViewModel(Navigator navigator) {
        mNavigator = navigator;
        mAdapter = new DownloadMangakAdapter(this);
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
    public void setPresenter(DownloadMangakContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onItemClick(Manga mangak) {
        mNavigator.startActivity(MangaDetailActivity.getInstance(mNavigator.getContext(), mangak, true));
    }

    @Override
    public void onGetMangakDownloadSuccess(List<Manga> mangas) {
        if (mangas == null) return;
        mAdapter.updateManga(mangas);
    }

    @Bindable
    public DownloadMangakAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(DownloadMangakAdapter adapter) {
        mAdapter = adapter;
        notifyPropertyChanged(BR.adapter);
    }
}
