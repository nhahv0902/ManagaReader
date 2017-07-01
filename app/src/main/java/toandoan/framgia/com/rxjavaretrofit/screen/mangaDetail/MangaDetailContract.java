package toandoan.framgia.com.rxjavaretrofit.screen.mangaDetail;

import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.screen.BasePresenter;
import toandoan.framgia.com.rxjavaretrofit.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface MangaDetailContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void showProgress();

        void onGetMangaSuccess(Manga manga);

        void onGetMangaFailed(String message);

        void hideProgress();

        void onFavoriteClick();

        void onStartDownload();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getMangaDetail(int id);

        void onFavoriteClick(Manga manga);
    }
}
