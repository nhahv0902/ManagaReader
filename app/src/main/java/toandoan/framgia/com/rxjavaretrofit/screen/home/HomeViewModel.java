package toandoan.framgia.com.rxjavaretrofit.screen.home;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.app.AppCompatActivity;
import toandoan.framgia.com.rxjavaretrofit.BR;
import toandoan.framgia.com.rxjavaretrofit.screen.mana.MangaFragment;

/**
 * Exposes the data to be used in the Home screen.
 */

public class HomeViewModel extends BaseObservable implements HomeContract.ViewModel {

    private HomeContract.Presenter mPresenter;
    private ManaPagerAdapter mAdapter;
    private AppCompatActivity mActivity;

    public HomeViewModel(AppCompatActivity activity) {
        mActivity = activity;
        initAdapter();
    }

    private void initAdapter() {
        mAdapter = new ManaPagerAdapter(mActivity.getSupportFragmentManager());
        mAdapter.addFragment(MangaFragment.newInstance(), "Popular");
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
}
