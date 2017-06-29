package toandoan.framgia.com.rxjavaretrofit.screen.favorite;

/**
 * Exposes the data to be used in the Favorite screen.
 */

public class FavoriteViewModel implements FavoriteContract.ViewModel {

    private FavoriteContract.Presenter mPresenter;

    public FavoriteViewModel() {
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
    public void setPresenter(FavoriteContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
