package toandoan.framgia.com.rxjavaretrofit.screen.mana.mangaDashboard;

/**
 * Listens to user actions from the UI ({@link MangaDashboardFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class MangaDashboardPresenter implements MangaDashboardContract.Presenter {
    private static final String TAG = MangaDashboardPresenter.class.getName();

    private final MangaDashboardContract.ViewModel mViewModel;

    public MangaDashboardPresenter(MangaDashboardContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
