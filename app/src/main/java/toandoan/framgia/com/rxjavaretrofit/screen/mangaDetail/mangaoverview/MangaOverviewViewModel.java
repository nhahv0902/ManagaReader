package toandoan.framgia.com.rxjavaretrofit.screen.mangaDetail.mangaoverview;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import java.util.ArrayList;
import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.BR;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.screen.filterResult.FilterResultActivity;
import toandoan.framgia.com.rxjavaretrofit.utils.navigator.Navigator;

/**
 * Exposes the data to be used in the MangaOverview screen.
 */

public class MangaOverviewViewModel extends BaseObservable
        implements MangaOverviewContract.ViewModel {

    private MangaOverviewContract.Presenter mPresenter;
    private MangaGenreAdapter mAdapter;
    private Navigator mNavigator;

    public MangaOverviewViewModel(Navigator navigator, Manga manga) {
        mNavigator = navigator;
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
        List<String> genresList = new ArrayList<>();
        genresList.add(genres);
        mNavigator.startActivity(
                FilterResultActivity.getInstance(mNavigator.getContext(), genresList));
    }
}
