package toandoan.framgia.com.rxjavaretrofit.screen.mana;

import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.screen.BasePresenter;
import toandoan.framgia.com.rxjavaretrofit.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface MangaContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onGetMangaSuccess(List<Manga> mangas);

        void onGetMangaFailed(String msg);

        void hideProgressBar();

        void showProgressBar();

        void onItemClick(Manga manga);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {

        void getData();

        void onLoadMoreData();
    }
}
