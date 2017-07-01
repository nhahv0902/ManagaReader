package toandoan.framgia.com.rxjavaretrofit.screen.download;

import toandoan.framgia.com.rxjavaretrofit.data.model.Chap;
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

        void showProgress();

        void hideProgress();

        void getChapterFailed(String message);

        void getChapterSuccess(Chap chap, boolean isItemEnd);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getChap(Chap chap, boolean b);
    }
}
