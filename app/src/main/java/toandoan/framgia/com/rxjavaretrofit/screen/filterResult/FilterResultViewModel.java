package toandoan.framgia.com.rxjavaretrofit.screen.filterResult;

/**
 * Exposes the data to be used in the FilterResult screen.
 */

public class FilterResultViewModel implements FilterResultContract.ViewModel {

    private FilterResultContract.Presenter mPresenter;

    public FilterResultViewModel() {
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
    public void setPresenter(FilterResultContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
