package toandoan.framgia.com.rxjavaretrofit.screen.source;

import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.data.model.Source;
import toandoan.framgia.com.rxjavaretrofit.screen.BasePresenter;
import toandoan.framgia.com.rxjavaretrofit.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface SourceContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void showProgress();

        void hideProgress();

        void onGetSourceSuccess(List<Source> sources);

        void onGetSourceFailed(String message);

        void onDoneClick();

        void onSaveSourceSucess();

        void onSaveSourceFailed();

        void onGetCurrentSourceSuccess();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getSource();

        void saveCurrentSource(Source source);
    }
}
