package toandoan.framgia.com.rxjavaretrofit.screen.mangaDetail.mangaoverview;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import toandoan.framgia.com.rxjavaretrofit.BR;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;

/**
 * Exposes the data to be used in the MangaOverview screen.
 */

public class MangaOverviewViewModel extends BaseObservable
        implements MangaOverviewContract.ViewModel {

    private MangaOverviewContract.Presenter mPresenter;
    private MangaGenreAdapter mAdapter;

    public MangaOverviewViewModel(Manga manga) {
        mAdapter = new MangaGenreAdapter(this, manga.getGenre());
        notifyPropertyChanged(BR.adapter);
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
    public void setPresenter(MangaOverviewContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Bindable
    public MangaGenreAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(MangaGenreAdapter adapter) {
        mAdapter = adapter;
        notifyPropertyChanged(BR.adapter);
    }

    @Override
    public void onGenresClick(String genres) {
        // TODO: 21/06/2017 later 
    }
}
