package toandoan.framgia.com.rxjavaretrofit.screen.home;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.app.AppCompatActivity;
import toandoan.framgia.com.rxjavaretrofit.BR;
import toandoan.framgia.com.rxjavaretrofit.screen.filter.FilterActivity;
import toandoan.framgia.com.rxjavaretrofit.screen.mana.mangaDashboard.MangaDashboardFragment;
import toandoan.framgia.com.rxjavaretrofit.screen.search.SearchActivity;
import toandoan.framgia.com.rxjavaretrofit.screen.source.SourceActivity;
import toandoan.framgia.com.rxjavaretrofit.utils.navigator.Navigator;

/**
 * Exposes the data to be used in the Home screen.
 */

public class HomeViewModel extends BaseObservable implements HomeContract.ViewModel {

    private HomeContract.Presenter mPresenter;
    private MangaPagerAdapter mAdapter;
    private AppCompatActivity mActivity;
    private Navigator mNavigator;

    public HomeViewModel(AppCompatActivity activity) {
        mActivity = activity;
        mNavigator = new Navigator(mActivity);
        initAdapter();
    }

    private void initAdapter() {
        mAdapter = new MangaPagerAdapter(mActivity.getSupportFragmentManager());
        mAdapter.addFragment(MangaDashboardFragment.newInstance(), null);
        notifyPropertyChanged(BR.adapter);
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
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Bindable
    public MangaPagerAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(MangaPagerAdapter adapter) {
        mAdapter = adapter;
        notifyPropertyChanged(BR.adapter);
    }

    @Override
    public void onMenuSourceClick() {
        mNavigator.startActivity(SourceActivity.getInstance(mNavigator.getContext(), true));
    }

    @Override
    public void onFilterClick() {
        mNavigator.startActivity(FilterActivity.getInstance(mNavigator.getContext()));
    }

    @Override
    public void onSearchClick() {
        mNavigator.startActivity(SearchActivity.getInstance(mNavigator.getContext()));
    }
}
