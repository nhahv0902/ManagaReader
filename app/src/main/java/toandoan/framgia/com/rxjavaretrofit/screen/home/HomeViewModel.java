package toandoan.framgia.com.rxjavaretrofit.screen.home;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import toandoan.framgia.com.rxjavaretrofit.BR;
import toandoan.framgia.com.rxjavaretrofit.screen.favorite.FavoriteFragment;
import toandoan.framgia.com.rxjavaretrofit.screen.filter.FilterActivity;
import toandoan.framgia.com.rxjavaretrofit.screen.mana.mangaDashboard.MangaDashboardFragment;
import toandoan.framgia.com.rxjavaretrofit.screen.recentManga.RecentMangaFragment;
import toandoan.framgia.com.rxjavaretrofit.screen.search.SearchActivity;
import toandoan.framgia.com.rxjavaretrofit.screen.settings.SettingsFragment;
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
    private ViewPager mViewPager;

    public HomeViewModel(AppCompatActivity activity) {
        mActivity = activity;
        mNavigator = new Navigator(mActivity);
        initAdapter();
    }

    public void setViewPager(ViewPager viewPager) {
        mViewPager = viewPager;
    }

    private void initAdapter() {
        mAdapter = new MangaPagerAdapter(mActivity.getSupportFragmentManager());
        mAdapter.addFragment(MangaDashboardFragment.newInstance(), null);
        mAdapter.addFragment(RecentMangaFragment.newInstance(), null);
        mAdapter.addFragment(RecentMangaFragment.newInstance(), null);
        mAdapter.addFragment(FavoriteFragment.newInstance(), null);
        mAdapter.addFragment(SettingsFragment.newInstance(), null);
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

    @Override
    public void onMenuClearClick() {
        int currentPos = mViewPager.getCurrentItem();
        Fragment currentFragment = mAdapter.getItem(currentPos);
        if (currentFragment instanceof RecentMangaFragment) {
            ((RecentMangaFragment) currentFragment).deleteAllRecentManga();
            return;
        }
        if (currentFragment instanceof FavoriteFragment) {
            ((FavoriteFragment) currentFragment).removeAllFavorite();
            return;
        }
    }
}
