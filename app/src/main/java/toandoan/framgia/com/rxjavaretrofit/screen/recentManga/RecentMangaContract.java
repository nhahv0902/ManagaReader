package toandoan.framgia.com.rxjavaretrofit.screen.recentManga;

import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.screen.BasePresenter;
import toandoan.framgia.com.rxjavaretrofit.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface RecentMangaContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onItemMangaClick(Manga manga);

        void onDeleteMangaClick(Manga manga, int pos);

        void onGetRecentMangaSuccess(List<Manga> mangas);

        void onUndoDeleteClick();

        void onDeleteAllManga();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getAllRecentMangas();

        void deleteManga(Manga manga);

        void deleteAllManga();
    }
}
