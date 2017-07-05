package toandoan.framgia.com.rxjavaretrofit.screen.downloading;

/**
 * Listens to user actions from the UI ({@link DownloadingActivity}), retrieves the data and updates
 * the UI as required.
 */
final class DownloadingPresenter implements DownloadingContract.Presenter {
    private static final String TAG = DownloadingPresenter.class.getName();

    private final DownloadingContract.ViewModel mViewModel;

    public DownloadingPresenter(DownloadingContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
