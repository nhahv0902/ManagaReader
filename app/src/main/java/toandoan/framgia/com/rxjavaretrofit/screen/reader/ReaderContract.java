package toandoan.framgia.com.rxjavaretrofit.screen.reader;

import toandoan.framgia.com.rxjavaretrofit.data.model.Chap;
import toandoan.framgia.com.rxjavaretrofit.data.model.Setting;
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

        void onPreviewItemClick(int adapterPosition);

        void onCloseClick();

        void onPreviewItemClick();

        void onNextClick();

        void onBackClick();

        void onLoadNextChapClick();

        void onOpenChapListClick();

        void onOpenSettingClick();

        void onGetSettingSuccess(Setting setting);

        void onReadingModeClick(int readingMode);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getChap(Chap chap);

        void getSetting();

        void saveSetting(Setting setting);
    }
}
