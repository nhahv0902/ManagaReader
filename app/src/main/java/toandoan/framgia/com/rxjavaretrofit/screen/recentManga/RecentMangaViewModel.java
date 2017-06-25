package toandoan.framgia.com.rxjavaretrofit.screen.recentManga;

import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;

/**
 * Exposes the data to be used in the RecentManga screen.
 */

public class RecentMangaViewModel implements RecentMangaContract.ViewModel {

    private RecentMangaContract.Presenter mPresenter;
    private RecentMangaFragment mFragment;

    public RecentMangaViewModel(RecentMangaFragment fragment) {
        mFragment = fragment;
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
    public void setPresenter(RecentMangaContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onItemMangaClick(Manga manga) {

    }

    @Override
    public void onDeleteMangaClick(Manga manga) {

    }

    @Override
    public void onGetRecentMangaSuccess(List<Manga> mangas) {
        mFragment.onGetRecentMangaSuccess(mangas);
    }
}
