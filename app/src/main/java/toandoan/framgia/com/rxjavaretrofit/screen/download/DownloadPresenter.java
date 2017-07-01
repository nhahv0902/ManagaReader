package toandoan.framgia.com.rxjavaretrofit.screen.download;

/**
 * Listens to user actions from the UI ({@link DownloadActivity}), retrieves the data and updates
 * the UI as required.
 */
final class DownloadPresenter implements DownloadContract.Presenter {
    private static final String TAG = DownloadPresenter.class.getName();

    private final DownloadContract.ViewModel mViewModel;

    public DownloadPresenter(DownloadContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
