package toandoan.framgia.com.rxjavaretrofit.screen.mangaDetail;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.app.AppCompatActivity;
import toandoan.framgia.com.rxjavaretrofit.BR;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.data.model.Chap;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.screen.home.MangaPagerAdapter;
import toandoan.framgia.com.rxjavaretrofit.screen.mangaDetail.mangachapter.MangaChapterFragment;
import toandoan.framgia.com.rxjavaretrofit.screen.mangaDetail.mangaoverview.MangaOverviewFragment;
import toandoan.framgia.com.rxjavaretrofit.utils.navigator.Navigator;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Exposes the data to be used in the MangaDetail screen.
 */

public class MangaDetailViewModel extends BaseObservable implements MangaDetailContract.ViewModel {

    private static final String TAG = "MangaDetailViewModel";

    private MangaDetailContract.Presenter mPresenter;
    private Manga mManga;
    private Navigator mNavigator;
    private String mAvatar;
    private MangaPagerAdapter mAdapter;
    private AppCompatActivity mActivity;
    private int mProgressVisible = VISIBLE;
    private Chap mCurrentChap;

    public MangaDetailViewModel(Context context, Navigator navigator, Chap currentChap) {
        mActivity = (AppCompatActivity) context;
        mNavigator = navigator;
        mCurrentChap = currentChap;
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
        setProgressVisible(VISIBLE);
    }

    @Override
    public void onGetMangaSuccess(Manga manga) {
        setManga(manga);
        setAvatar(manga.getAvatar());
        mAdapter = new MangaPagerAdapter(mActivity.getSupportFragmentManager());
        mAdapter.addFragment(MangaOverviewFragment.newInstance(mManga),
                mActivity.getString(R.string.title_sumarry));
        mAdapter.addFragment(MangaChapterFragment.newInstance(mManga, mCurrentChap),
                mActivity.getString(R.string.title_chapter));
        notifyPropertyChanged(BR.adapter);
    }

    @Override
    public void onGetMangaFailed(String message) {
        mNavigator.showToast(message);
    }

    @Override
    public void hideProgress() {
        setProgressVisible(GONE);
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

    @Bindable
    public int getProgressVisible() {
        return mProgressVisible;
    }

    public void setProgressVisible(int progressVisible) {
        mProgressVisible = progressVisible;
        notifyPropertyChanged(BR.progressVisible);
    }
}
