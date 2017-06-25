package toandoan.framgia.com.rxjavaretrofit.screen.recentManga;

import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.screen.mangaDetail.MangaDetailActivity;
import toandoan.framgia.com.rxjavaretrofit.utils.navigator.Navigator;

/**
 * Exposes the data to be used in the RecentManga screen.
 */

public class RecentMangaViewModel implements RecentMangaContract.ViewModel {

    private RecentMangaContract.Presenter mPresenter;
    private RecentMangaFragment mFragment;
    private Navigator mNavigator;

    public RecentMangaViewModel(RecentMangaFragment fragment) {
        mFragment = fragment;
        mNavigator = new Navigator(mFragment);
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
        mNavigator.startActivity(MangaDetailActivity.getInstance(mNavigator.getContext(), manga,
                manga.getLastLocalChap()));
    }

    @Override
    public void onDeleteMangaClick(Manga manga, int pos) {
        mPresenter.deleteManga(manga);
        mFragment.onDeleteMangaClick(pos);
    }

    @Override
    public void onGetRecentMangaSuccess(List<Manga> mangas) {
        mFragment.onGetRecentMangaSuccess(mangas);
    }

    @Override
    public void onUndoDeleteClick() {
        mPresenter.getAllRecentMangas();
    }

    @Override
    public void onDeleteAllManga() {
        mPresenter.deleteAllManga();
    }
}
