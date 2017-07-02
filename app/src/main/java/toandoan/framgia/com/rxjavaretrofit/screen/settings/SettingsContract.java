package toandoan.framgia.com.rxjavaretrofit.screen.settings;

import toandoan.framgia.com.rxjavaretrofit.screen.BasePresenter;
import toandoan.framgia.com.rxjavaretrofit.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface SettingsContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onShareAppClick();

        void onRateAppClick();

        void onDisclaimerClick();

        void onAboutUsClick();

        void onEmailClick();

        void onClearCacheClick();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
