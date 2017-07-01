package toandoan.framgia.com.rxjavaretrofit.screen.settings;

/**
 * Exposes the data to be used in the Settings screen.
 */

public class SettingsViewModel implements SettingsContract.ViewModel {

    private SettingsContract.Presenter mPresenter;

    public SettingsViewModel() {
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
    public void setPresenter(SettingsContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
