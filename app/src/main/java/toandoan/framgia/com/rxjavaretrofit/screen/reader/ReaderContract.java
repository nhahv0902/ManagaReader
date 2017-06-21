package toandoan.framgia.com.rxjavaretrofit.screen.reader;

import toandoan.framgia.com.rxjavaretrofit.data.model.Chap;
import toandoan.framgia.com.rxjavaretrofit.screen.BasePresenter;
import toandoan.framgia.com.rxjavaretrofit.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface ReaderContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void showProgress();

        void getChapterSuccess(Chap chap);

        void hideProgress();

        void getChapterFailed(String message);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getChap(String chapId);
    }
}
