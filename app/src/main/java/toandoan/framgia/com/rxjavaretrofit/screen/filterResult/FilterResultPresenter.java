package toandoan.framgia.com.rxjavaretrofit.screen.filterResult;

/**
 * Listens to user actions from the UI ({@link FilterResultActivity}), retrieves the data and
 * updates
 * the UI as required.
 */
final class FilterResultPresenter implements FilterResultContract.Presenter {
    private static final String TAG = FilterResultPresenter.class.getName();

    private final FilterResultContract.ViewModel mViewModel;

    public FilterResultPresenter(FilterResultContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
