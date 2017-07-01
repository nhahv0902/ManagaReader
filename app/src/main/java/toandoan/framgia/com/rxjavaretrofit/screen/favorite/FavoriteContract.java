package toandoan.framgia.com.rxjavaretrofit.screen.favorite;

import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.screen.BasePresenter;
import toandoan.framgia.com.rxjavaretrofit.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface FavoriteContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onRemoveFavorite(Manga manga, int pos);

        void onItemClick(Manga manga);

        void onGetFavoriteSuccess(List<Manga> mangas);

        void onUndoDeleteClick();

        void onDeleteAllFavoriteManga();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getFavoritesManga();

        void deleteAllFavorite();

        void removeFavorite(int pos);
    }
}
