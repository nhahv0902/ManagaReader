package toandoan.framgia.com.rxjavaretrofit.screen.home;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.app.AppCompatActivity;
import toandoan.framgia.com.rxjavaretrofit.BR;
import toandoan.framgia.com.rxjavaretrofit.screen.mana.MangaFragment;
import toandoan.framgia.com.rxjavaretrofit.screen.source.SourceActivity;
import toandoan.framgia.com.rxjavaretrofit.utils.Constant;
import toandoan.framgia.com.rxjavaretrofit.utils.navigator.Navigator;

/**
 * Exposes the data to be used in the Home screen.
 */

public class HomeViewModel extends BaseObservable implements HomeContract.ViewModel {

    private HomeContract.Presenter mPresenter;
    private ManaPagerAdapter mAdapter;
    private AppCompatActivity mActivity;
    private Navigator mNavigator;

    public HomeViewModel(AppCompatActivity activity) {
        mActivity = activity;
        mNavigator = new Navigator(mActivity);
        initAdapter();
    }

    private void initAdapter() {
        mAdapter = new ManaPagerAdapter(mActivity.getSupportFragmentManager());
        mAdapter.addFragment(MangaFragment.newInstance(Constant.MangaType.POPULAR), "Popular");
        mAdapter.addFragment(MangaFragment.newInstance(Constant.MangaType.NEW), "New Manga");
        mAdapter.addFragment(MangaFragment.newInstance(Constant.MangaType.UPDATE), "Last Update");
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
    public ManaPagerAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(ManaPagerAdapter adapter) {
        mAdapter = adapter;
        notifyPropertyChanged(BR.adapter);
    }

    @Override
    public void onMenuSourceClick() {
        mNavigator.startActivity(SourceActivity.getInstance(mNavigator.getContext(), true));
    }
}
