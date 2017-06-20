package toandoan.framgia.com.rxjavaretrofit.screen.mangaDetail.mangachapter;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import toandoan.framgia.com.rxjavaretrofit.BR;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;

/**
 * Exposes the data to be used in the MangaChapter screen.
 */

public class MangaChapterViewModel extends BaseObservable
        implements MangaChapterContract.ViewModel {

    private MangaChapterContract.Presenter mPresenter;
    private Manga mManga;

    public MangaChapterViewModel(Manga manga) {
        mManga = manga;
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
}
