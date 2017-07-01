package toandoan.framgia.com.rxjavaretrofit.screen.download;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.android.databinding.library.baseAdapters.BR;
import toandoan.framgia.com.rxjavaretrofit.data.model.Chap;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.screen.mangaDetail.mangachapter.OnChapterClickListtenner;

/**
 * Exposes the data to be used in the Download screen.
 */

public class DownloadViewModel extends BaseObservable implements DownloadContract.ViewModel {

    private DownloadContract.Presenter mPresenter;
    private DownloadMangakAdapter mAdapter;
    private Manga mMangak;
    private int mNumberChapDownload;
    private boolean mIsSelectAll;

    public DownloadViewModel(Manga mangak) {
        mMangak = mangak;
        mAdapter = new DownloadMangakAdapter(mangak.getChaps(), new OnChapterClickListtenner() {
            @Override
            public void onChapterItemClick(Chap chap, int pos) {
                chap.setChecked(!chap.isChecked());
                setNumberChapDownload(0);
                for (Chap item : mMangak.getChaps()) {
                    if (item.isChecked()) {
                        mNumberChapDownload++;
                    }
                }
                setNumberChapDownload(mNumberChapDownload);
            }
        });
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
    public void setPresenter(DownloadContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onSelectChapsDownload() {
        if (mIsSelectAll) {
            onUnSelectAll();
        } else {
            onSelectAll();
        }
        setSelectAll(!mIsSelectAll);
    }

    @Override
    public void onSelectAll() {
        if (mMangak == null || mMangak.getChaps() == null || mMangak.getChaps().size() == 0) return;
        for (Chap item : mMangak.getChaps()) {
            item.setChecked(true);
        }
        setNumberChapDownload(mMangak.getChaps().size());
    }

    @Override
    public void onUnSelectAll() {
        if (mMangak == null || mMangak.getChaps() == null || mMangak.getChaps().size() == 0) return;
        for (Chap item : mMangak.getChaps()) {
            item.setChecked(false);
        }
        setNumberChapDownload(0);
    }

    @Override
    public void onCancelDownload() {

    }

    @Override
    public void onClickDownload() {

    }

    @Bindable
    public DownloadMangakAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(DownloadMangakAdapter adapter) {
        mAdapter = adapter;
        notifyPropertyChanged(BR.adapter);
    }

    @Bindable
    public int getNumberChapDownload() {
        return mNumberChapDownload;
    }

    public void setNumberChapDownload(int numberChapDownload) {
        mNumberChapDownload = numberChapDownload;
        notifyPropertyChanged(BR.numberChapDownload);
    }

    @Bindable
    public Manga getMangak() {
        return mMangak;
    }

    public void setMangak(Manga mangak) {
        mMangak = mangak;
        notifyPropertyChanged(BR.mangak);
    }

    @Bindable
    public boolean isSelectAll() {
        return mIsSelectAll;
    }

    public void setSelectAll(boolean selectAll) {
        mIsSelectAll = selectAll;
        notifyPropertyChanged(BR.selectAll);
    }
}
