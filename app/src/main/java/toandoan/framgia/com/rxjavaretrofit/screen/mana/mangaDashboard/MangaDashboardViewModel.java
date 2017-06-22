package toandoan.framgia.com.rxjavaretrofit.screen.mana.mangaDashboard;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.app.Fragment;
import toandoan.framgia.com.rxjavaretrofit.BR;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.screen.home.MangaPagerAdapter;
import toandoan.framgia.com.rxjavaretrofit.screen.mana.MangaFragment;
import toandoan.framgia.com.rxjavaretrofit.screen.mana.allmanga.AllMangaFragment;
import toandoan.framgia.com.rxjavaretrofit.utils.Constant;

/**
 * Exposes the data to be used in the MangaDashboard screen.
 */

public class MangaDashboardViewModel extends BaseObservable
        implements MangaDashboardContract.ViewModel {

    private MangaDashboardContract.Presenter mPresenter;
    private MangaPagerAdapter mAdapter;

    public MangaDashboardViewModel(Fragment fragment) {
        mAdapter = new MangaPagerAdapter(fragment.getChildFragmentManager());
        mAdapter.addFragment(AllMangaFragment.newInstance(),
                fragment.getString(R.string.all_manga));
        mAdapter.addFragment(MangaFragment.newInstance(Constant.MangaType.POPULAR),
                fragment.getString(R.string.popular));
        mAdapter.addFragment(MangaFragment.newInstance(Constant.MangaType.NEW),
                fragment.getString(R.string.title_new));
        mAdapter.addFragment(MangaFragment.newInstance(Constant.MangaType.UPDATE),
                fragment.getString(R.string.update));
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
    public void setPresenter(MangaDashboardContract.Presenter presenter) {
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
}
