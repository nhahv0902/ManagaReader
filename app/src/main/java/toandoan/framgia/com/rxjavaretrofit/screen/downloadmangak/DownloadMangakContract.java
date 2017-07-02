package toandoan.framgia.com.rxjavaretrofit.screen.downloadmangak;

import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.screen.BasePresenter;
import toandoan.framgia.com.rxjavaretrofit.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface DownloadMangakContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onItemClick(Manga mangak);

        void onGetMangakDownloadSuccess(List<Manga> mangas);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getAllMangakDownload();
    }
}
