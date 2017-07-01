package toandoan.framgia.com.rxjavaretrofit.screen.settings;

/**
 * Listens to user actions from the UI ({@link SettingsFragment}), retrieves the data and updates
 * the UI as required.
 */
final class SettingsPresenter implements SettingsContract.Presenter {
    private static final String TAG = SettingsPresenter.class.getName();

    private final SettingsContract.ViewModel mViewModel;

    public SettingsPresenter(SettingsContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
