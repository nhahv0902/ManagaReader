package toandoan.framgia.com.rxjavaretrofit.screen.home;

import android.support.v4.view.ViewPager;

/**
 * Listens to user actions from the UI ({@link HomeActivity}), retrieves the data and updates
 * the UI as required.
 */
final class HomePresenter implements HomeContract.Presenter {
    private static final String TAG = HomePresenter.class.getName();

    private final HomeContract.ViewModel mViewModel;

    public HomePresenter(HomeContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
