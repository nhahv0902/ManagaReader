package toandoan.framgia.com.rxjavaretrofit.screen.download;

import toandoan.framgia.com.rxjavaretrofit.screen.BasePresenter;
import toandoan.framgia.com.rxjavaretrofit.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface DownloadContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onSelectAll();

        void onUnSelectAll();

        void onCancelDownload();

        void onSelectChapsDownload();

        void onClickDownload();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
