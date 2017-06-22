package toandoan.framgia.com.rxjavaretrofit.screen.search;

/**
 * Exposes the data to be used in the Search screen.
 */

public class SearchViewModel implements SearchContract.ViewModel {

    private SearchContract.Presenter mPresenter;

    public SearchViewModel() {
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
    public void setPresenter(SearchContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
