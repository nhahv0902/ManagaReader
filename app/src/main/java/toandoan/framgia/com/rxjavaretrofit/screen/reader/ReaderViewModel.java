package toandoan.framgia.com.rxjavaretrofit.screen.reader;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import toandoan.framgia.com.rxjavaretrofit.BR;
import toandoan.framgia.com.rxjavaretrofit.data.model.Chap;
import toandoan.framgia.com.rxjavaretrofit.utils.navigator.Navigator;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Exposes the data to be used in the Reader screen.
 */

public class ReaderViewModel extends BaseObservable implements ReaderContract.ViewModel {

    private static final String TAG = "ReaderViewModel";
    private ReaderContract.Presenter mPresenter;
    private int mProgressVisible = VISIBLE;
    private Navigator mNavigator;

    public ReaderViewModel(Navigator navigator) {
        mNavigator = navigator;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(ReaderContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showProgress() {
        setProgressVisible(VISIBLE);
    }

    @Override
    public void getChapterSuccess(Chap chap) {
        Log.d(TAG, "getChapterSuccess: " + chap.getContent().size());
    }

    @Override
    public void hideProgress() {
        setProgressVisible(GONE);
    }

    @Override
    public void getChapterFailed(String message) {
        mNavigator.showToast(message);
    }

    @Bindable
    public int getProgressVisible() {
        return mProgressVisible;
    }

    public void setProgressVisible(int progressVisible) {
        mProgressVisible = progressVisible;
        notifyPropertyChanged(BR.progressVisible);
    }
}
