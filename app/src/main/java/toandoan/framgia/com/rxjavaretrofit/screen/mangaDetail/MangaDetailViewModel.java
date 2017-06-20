package toandoan.framgia.com.rxjavaretrofit.screen.mangaDetail;

import android.app.ProgressDialog;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.app.AppCompatActivity;
import toandoan.framgia.com.rxjavaretrofit.BR;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.screen.home.MangaPagerAdapter;
import toandoan.framgia.com.rxjavaretrofit.screen.mangaDetail.mangachapter.MangaChapterFragment;
import toandoan.framgia.com.rxjavaretrofit.screen.mangaDetail.mangaoverview.MangaOverviewFragment;
import toandoan.framgia.com.rxjavaretrofit.utils.navigator.Navigator;

/**
 * Exposes the data to be used in the MangaDetail screen.
 */

public class MangaDetailViewModel extends BaseObservable implements MangaDetailContract.ViewModel {

    private static final String TAG = "MangaDetailViewModel";

    private MangaDetailContract.Presenter mPresenter;
    private ProgressDialog mDialog;
    private Manga mManga;
    private Navigator mNavigator;
    private String mAvatar;
    private MangaPagerAdapter mAdapter;
    private AppCompatActivity mActivity;

    public MangaDetailViewModel(Context context, Navigator navigator) {
        mDialog = new ProgressDialog(context);
        mActivity = (AppCompatActivity) context;
        mNavigator = navigator;
        mDialog.setMessage(context.getString(R.string.loading));
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
    public void setPresenter(MangaDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showProgress() {
        if (mDialog != null) {
            mDialog.show();
        }
    }

    @Override
    public void onGetMangaSuccess(Manga manga) {
        setManga(manga);
        setAvatar(manga.getAvatar());
        mAdapter = new MangaPagerAdapter(mActivity.getSupportFragmentManager());
        mAdapter.addFragment(MangaOverviewFragment.newInstance(mManga),
                mActivity.getString(R.string.title_sumarry));
        mAdapter.addFragment(MangaChapterFragment.newInstance(mManga),
                mActivity.getString(R.string.title_chapter));
        notifyPropertyChanged(BR.adapter);
    }

    @Override
    public void onGetMangaFailed(String message) {
        mNavigator.showToast(message);
    }

    @Override
    public void hideProgress() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

    public Manga getManga() {
        return mManga;
    }

    public void setManga(Manga manga) {
        mManga = manga;
    }

    @Bindable
    public String getAvatar() {
        return mAvatar;
    }

    public void setAvatar(String avatar) {
        mAvatar = avatar;
        notifyPropertyChanged(BR.avatar);
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
