package toandoan.framgia.com.rxjavaretrofit.screen.title;

/**
 * Listens to user actions from the UI ({@link TitleActivity}), retrieves the data and updates
 * the UI as required.
 */
final class TitlePresenter implements TitleContract.Presenter {
    private static final String TAG = TitlePresenter.class.getName();

    private final TitleContract.ViewModel mViewModel;

    public TitlePresenter(TitleContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
