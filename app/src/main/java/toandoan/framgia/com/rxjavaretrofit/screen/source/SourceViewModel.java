package toandoan.framgia.com.rxjavaretrofit.screen.source;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.MenuItem;
import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.AppApplication;
import toandoan.framgia.com.rxjavaretrofit.BR;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.data.model.Source;
import toandoan.framgia.com.rxjavaretrofit.screen.home.HomeActivity;
import toandoan.framgia.com.rxjavaretrofit.utils.navigator.Navigator;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Exposes the data to be used in the Source screen.
 */

public class SourceViewModel extends BaseObservable implements SourceContract.ViewModel {

    private static final String TAG = "SourceViewModel";

    private SourceContract.Presenter mPresenter;
    private int mProgressVisible = GONE;
    private Navigator mNavigator;
    private SourceAdapter mAdapter;
    private boolean mIsStartFromMain;
    private MenuItem mMenuDone;

    public SourceViewModel(Navigator navigator, boolean isStartFromMain) {
        mNavigator = navigator;
        mIsStartFromMain = isStartFromMain;
    }

    public MenuItem getMenuDone() {
        return mMenuDone;
    }

    public void setMenuDone(MenuItem menuDone) {
        mMenuDone = menuDone;
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
    public void setPresenter(SourceContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showProgress() {
        setProgressVisible(VISIBLE);
    }

    @Override
    public void hideProgress() {
        setProgressVisible(GONE);
    }

    @Override
    public void onGetSourceSuccess(List<Source> sources) {
        mAdapter.updateData(sources);
    }

    @Override
    public void onGetSourceFailed(String message) {
        mNavigator.showToast(message);
    }

    @Override
    public void onDoneClick() {
        Source source = mAdapter.getSelectedItem();
        if (source != null) {
            mPresenter.saveCurrentSource(source);
        } else {
            mNavigator.showToast(R.string.pls_select_cource);
        }
    }

    @Override
    public void onSaveSourceSucess() {
        AppApplication.sMangas = null;
        mNavigator.startActivity(HomeActivity.getInstance(mNavigator.getContext()));
    }

    @Override
    public void onSaveSourceFailed() {
        mNavigator.showToast(R.string.some_thing_wrong);
    }

    @Override
    public void onGetCurrentSourceSuccess() {
        if (!mIsStartFromMain) {
            mNavigator.startActivity(HomeActivity.getInstance(mNavigator.getContext()));
        }
    }

    @Override
    public void onSourceClick(Source source) {
        mMenuDone.setVisible(true);
    }

    @Bindable
    public int getProgressVisible() {
        return mProgressVisible;
    }

    public void setProgressVisible(int progressVisible) {
        mProgressVisible = progressVisible;
        notifyPropertyChanged(BR.progressVisible);
    }

    @Bindable
    public SourceAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(SourceAdapter adapter) {
        mAdapter = adapter;
        notifyPropertyChanged(BR.adapter);
    }
}
