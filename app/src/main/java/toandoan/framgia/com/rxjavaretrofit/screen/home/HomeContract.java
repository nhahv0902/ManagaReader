package toandoan.framgia.com.rxjavaretrofit.screen.home;

import toandoan.framgia.com.rxjavaretrofit.screen.BasePresenter;
import toandoan.framgia.com.rxjavaretrofit.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface HomeContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onMenuSourceClick();

        void onFilterClick();

        void onSearchClick();

        void onMenuClearClick();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
