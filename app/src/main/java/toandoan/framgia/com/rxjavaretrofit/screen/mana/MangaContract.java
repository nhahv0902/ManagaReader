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
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {

        void getData(String option, int page);
    }
}
