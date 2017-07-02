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

        void onUndoDeleteClick();

        void onDeleteAllMangakDownloaded();

        void onRemoveMangakDownload(Manga manga, int position);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getAllMangakDownload();

        void deleteAllMangakDownload();

        void deleteMangak(Manga manga);
    }
}
