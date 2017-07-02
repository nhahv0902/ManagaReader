package toandoan.framgia.com.rxjavaretrofit.screen.download;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import com.android.databinding.library.baseAdapters.BR;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.data.model.Chap;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.screen.mangaDetail.mangachapter.OnChapterClickListtenner;
import toandoan.framgia.com.rxjavaretrofit.service.DownloadService;
import toandoan.framgia.com.rxjavaretrofit.utils.ImageDownloadManager;

/**
 * Exposes the data to be used in the Download screen.
 */

public class DownloadViewModel extends BaseObservable implements DownloadContract.ViewModel {

    private final Context mContext;
    private DownloadContract.Presenter mPresenter;
    private DownloadMangakAdapter mAdapter;
    private Manga mMangak;
    private int mNumberChapDownload;
    private boolean mIsSelectAll;
    private ImageDownloadManager mDownloadManager;
    private int mProgressStatus = 0;
    public DownloadService mDownloadService;
    public boolean mBinded;
    private String mChapIndex;
    private boolean mIsDownload;

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            DownloadService.DownloadBinder binder = (DownloadService.DownloadBinder) iBinder;
            mDownloadService = binder.getService();
            mBinded = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBinded = false;
        }
    };

    public DownloadViewModel(Context context, Manga mangak) {
        mContext = context;
        mMangak = mangak;
        Collections.sort(mMangak.getChaps());
        mAdapter = new DownloadMangakAdapter(mangak.getChaps(), new OnChapterClickListtenner() {
            @Override
            public void onChapterItemClick(Chap chap, int pos) {
                if (mChapIndex == null) mChapIndex = chap.getChap();
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
        mDownloadManager = ImageDownloadManager.getInstance(context);
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
        if (mBinded) {
            mContext.unbindService(mConnection);
            mBinded = false;
        }
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
        Intent intent = new Intent(mContext, DownloadService.class);
        mContext.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onClickDownload() {
        if (mIsDownload) {
            setDownload(false);
            return;
        }
        if (mMangak == null || mMangak.getChaps() == null || mMangak.getChaps().size() == 0) return;
        List<Chap> chaps = new ArrayList<>();
        setDownload(true);
        for (Chap item : mMangak.getChaps()) {
            if (item.isChecked()) {
                chaps.add(item);
            }
        }

        int size = chaps.size();
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                mPresenter.getChap(chaps.get(i), true);
            } else {
                mPresenter.getChap(chaps.get(i), false);
            }
        }
    }

    @Override
    public void getChapterSuccess(final Chap chap, final boolean isItemEnd) {

        mDownloadManager.downloads(String.valueOf(mMangak.getId()), String.valueOf(chap.getId()),
                chap.getContent(), new ImageDownloadManager.PercentCallback() {
                    @Override
                    public void onPercent(int percent) {
                        setChapIndex(chap.getChap());
                        setProgressStatus(percent);
                    }

                    @Override
                    public void onDownloaded() {
                        if (isItemEnd) {
                            setDownload(false);
                            Log.d("TAG", "success download");
                            Toast.makeText(mContext, R.string.msg_download_success,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {
    }

    @Override
    public void getChapterFailed(String message) {

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

    @Bindable
    public boolean isDownload() {
        return mIsDownload;
    }

    public void setDownload(boolean download) {
        mIsDownload = download;
        notifyPropertyChanged(BR.download);
    }

    @Bindable
    public String getChapIndex() {
        return mChapIndex;
    }

    public void setChapIndex(String chapIndex) {
        mChapIndex = chapIndex;
        notifyPropertyChanged(BR.chapIndex);
    }

    @Bindable
    public int getProgressStatus() {
        return mProgressStatus;
    }

    public void setProgressStatus(int progressStatus) {
        mProgressStatus = progressStatus;
        notifyPropertyChanged(BR.progressStatus);
    }
}
