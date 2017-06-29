package toandoan.framgia.com.rxjavaretrofit.screen.favorite;

/**
 * Listens to user actions from the UI ({@link FavoriteFragment}), retrieves the data and updates
 * the UI as required.
 */
final class FavoritePresenter implements FavoriteContract.Presenter {
    private static final String TAG = FavoritePresenter.class.getName();

    private final FavoriteContract.ViewModel mViewModel;

    public FavoritePresenter(FavoriteContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
