package toandoan.framgia.com.rxjavaretrofit.screen.mangaDetail.mangachapter;

import android.databinding.BaseObservable;
import toandoan.framgia.com.rxjavaretrofit.data.model.Chap;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.screen.reader.ReaderActivity;
import toandoan.framgia.com.rxjavaretrofit.utils.navigator.Navigator;

/**
 * Exposes the data to be used in the MangaChapter screen.
 */

public class MangaChapterViewModel extends BaseObservable
        implements MangaChapterContract.ViewModel {

    private MangaChapterContract.Presenter mPresenter;
    private Navigator mNavigator;

    public MangaChapterViewModel(Navigator navigator) {
        mNavigator = navigator;
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
    public void setPresenter(MangaChapterContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onChapterItemClick(Chap chap) {
        mNavigator.startActivity(ReaderActivity.getInstance(mNavigator.getContext(), chap));
    }
}
