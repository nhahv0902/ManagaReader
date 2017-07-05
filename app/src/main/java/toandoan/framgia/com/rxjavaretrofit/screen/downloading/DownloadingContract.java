package toandoan.framgia.com.rxjavaretrofit.screen.downloading;

import toandoan.framgia.com.rxjavaretrofit.data.model.MessageDownloading;
import toandoan.framgia.com.rxjavaretrofit.screen.BasePresenter;
import toandoan.framgia.com.rxjavaretrofit.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface DownloadingContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onClickExpandable(MessageDownloading message, int position);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}