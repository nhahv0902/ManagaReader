package toandoan.framgia.com.rxjavaretrofit.screen.reader;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.SeekBar;
import toandoan.framgia.com.rxjavaretrofit.BR;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.data.model.Chap;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.data.model.Setting;
import toandoan.framgia.com.rxjavaretrofit.screen.mangaDetail.mangachapter.OnChapterClickListtenner;
import toandoan.framgia.com.rxjavaretrofit.utils.navigator.Navigator;
import toandoan.framgia.com.rxjavaretrofit.utils.widget.dialog.ChapterDialog;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Exposes the data to be used in the Reader screen.
 */

public class ReaderViewModel extends BaseObservable implements ReaderContract.ViewModel {

    private static final String TAG = "ReaderViewModel";
    private ReaderContract.Presenter mPresenter;
    private int mProgressVisible = VISIBLE;
    private int mLayoutControlVisible = VISIBLE;
    private int mPreviewVisible = GONE;
    private int mSettingVissile = GONE;
    private Navigator mNavigator;
    private ReaderAdapter mAdapter;
    private ReaderPreviewAdapter mPreviewAdapter;
    private RecyclerView mRecyclerReader;
    private int mCurrentPosition = 0;
    private Chap mChap;
    private Manga mManga;
    private int mChapPos;
    private Setting mSetting;
    private int mLayoutOrientation;

    private RecyclerView.OnScrollListener mSroll = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (dy > dx) {
                onScrollDown();
            } else if (dy < dx) {
                onScrollUp();
            }
            int pos =
                    ((LinearLayoutManager) recyclerView.getLayoutManager())
                            .findFirstVisibleItemPosition();
            if (pos != mCurrentPosition) {
                setCurrentPosition(pos);
            }
        }
    };

    public ReaderViewModel(Navigator navigator, Manga manga, int chapPos) {
        mNavigator = navigator;
        mManga = manga;
        mChapPos = chapPos;
    }

    public void setRecyclerReader(RecyclerView recyclerReader) {
        mRecyclerReader = recyclerReader;
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
    public void setPresenter(ReaderContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showProgress() {
        setProgressVisible(VISIBLE);
    }

    @Override
    public void getChapterSuccess(Chap chap) {
        String nextChap = mChapPos < mManga.getChaps().size() - 1 ? mManga.getChaps()
                .get(mChapPos + 1)
                .getChap() : null;
        setChap(chap);
        setAdapter(new ReaderAdapter(this, chap.getContent(), nextChap));
        setPreviewAdapter(new ReaderPreviewAdapter(this, chap.getContent()));
        setCurrentPosition(0);
    }

    @Override
    public void hideProgress() {
        setProgressVisible(GONE);
    }

    @Override
    public void getChapterFailed(String message) {
        mNavigator.showToast(message);
    }

    @Override
    public void onPreviewItemClick(int adapterPosition) {
        mRecyclerReader.smoothScrollToPosition(adapterPosition);
    }

    @Override
    public void onCloseClick() {
        mNavigator.finishActivity();
    }

    @Override
    public void onPreviewItemClick() {
        setPreviewVisible(mPreviewVisible == VISIBLE ? GONE : VISIBLE);
    }

    @Override
    public void onNextClick() {
        mRecyclerReader.scrollToPosition(0);
        mCurrentPosition++;
        if (mCurrentPosition != mChap.getContent().size()) {
            setCurrentPosition(mCurrentPosition);
            mRecyclerReader.scrollToPosition(mCurrentPosition);
        } else {
            mCurrentPosition--;
        }
        setLayoutControlVisible(VISIBLE);
    }

    @Override
    public void onBackClick() {
        mCurrentPosition--;
        if (mCurrentPosition != -1) {
            setCurrentPosition(mCurrentPosition);
            mRecyclerReader.scrollToPosition(mCurrentPosition);
        } else {
            mCurrentPosition--;
        }
        setLayoutControlVisible(VISIBLE);
    }

    public void onLoadNextChapClick() {
        mChapPos++;
        if (mChapPos != mManga.getChaps().size()) {
            setChap(mManga.getChaps().get(mChapPos));
            setCurrentPosition(0);
            setLayoutControlVisible(VISIBLE);
            mPresenter.getChap(mChap);
        } else {
            mNavigator.showToast(R.string.last_chap);
        }
    }

    @Override
    public void onOpenChapListClick() {
        final ChapterDialog dialog = new ChapterDialog(mNavigator.getContext());
        dialog.initData(mManga.getChaps(), mChapPos, new OnChapterClickListtenner() {
            @Override
            public void onChapterItemClick(Chap chap, int pos) {
                dialog.dismiss();
                mChapPos = pos;
                if (mChapPos != mManga.getChaps().size()) {
                    setChap(mManga.getChaps().get(mChapPos));
                    setCurrentPosition(0);
                    setLayoutControlVisible(VISIBLE);
                    mPresenter.getChap(mChap);
                } else {
                    mNavigator.showToast(R.string.last_chap);
                }
            }
        });
        dialog.show();
    }

    @Override
    public void onOpenSettingClick() {
        setSettingVissile(mSettingVissile == VISIBLE ? GONE : VISIBLE);
    }

    @Override
    public void onGetSettingSuccess(Setting setting) {
        setSetting(setting);
        mRecyclerReader.setLayoutManager(
                new LinearLayoutManager(mRecyclerReader.getContext(), setting.getReadingMode(),
                        false));
    }

    @Override
    public void onReadingModeClick(int readingMode) {
        mSetting.setReadingMode(readingMode);
        mPresenter.saveSetting(mSetting);
        mRecyclerReader.setLayoutManager(
                new LinearLayoutManager(mRecyclerReader.getContext(), mSetting.getReadingMode(),
                        false));
        setSettingVissile(GONE);
    }

    @Bindable
    public int getProgressVisible() {
        return mProgressVisible;
    }

    public void setProgressVisible(int progressVisible) {
        mProgressVisible = progressVisible;
        notifyPropertyChanged(BR.progressVisible);
    }

    @Bindable
    public ReaderAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(ReaderAdapter adapter) {
        mAdapter = adapter;
        notifyPropertyChanged(BR.adapter);
    }

    public void onItemImageReaderClick() {
        setLayoutControlVisible(mLayoutControlVisible == VISIBLE ? GONE : VISIBLE);
        if (mLayoutControlVisible == GONE) {
            setPreviewVisible(GONE);
            setSettingVissile(GONE);
        }
    }

    public void onScrollDown() {
        setLayoutControlVisible(GONE);
        if (mLayoutControlVisible == GONE) {
            setPreviewVisible(GONE);
            setSettingVissile(GONE);
        }
    }

    private void onScrollUp() {
        setLayoutControlVisible(VISIBLE);
    }

    @Bindable
    public ReaderPreviewAdapter getPreviewAdapter() {
        return mPreviewAdapter;
    }

    public void setPreviewAdapter(ReaderPreviewAdapter previewAdapter) {
        mPreviewAdapter = previewAdapter;
        notifyPropertyChanged(BR.previewAdapter);
    }

    @Bindable
    public int getPreviewVisible() {
        return mPreviewVisible;
    }

    public void setPreviewVisible(int previewVisible) {
        mPreviewVisible = previewVisible;
        notifyPropertyChanged(BR.previewVisible);
    }

    @Bindable
    public int getLayoutControlVisible() {
        return mLayoutControlVisible;
    }

    public void setLayoutControlVisible(int layoutControlVisible) {
        mLayoutControlVisible = layoutControlVisible;
        notifyPropertyChanged(BR.layoutControlVisible);
    }

    @Bindable
    public RecyclerView.OnScrollListener getSroll() {
        return mSroll;
    }

    public void setSroll(RecyclerView.OnScrollListener sroll) {
        mSroll = sroll;
        notifyPropertyChanged(BR.sroll);
    }

    @Bindable
    public int getCurrentPosition() {
        return mCurrentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        mCurrentPosition = currentPosition;
        notifyPropertyChanged(BR.currentPosition);
    }

    @Bindable
    public Chap getChap() {
        return mChap;
    }

    public void setChap(Chap chap) {
        mChap = chap;
        notifyPropertyChanged(BR.chap);
    }

    @Bindable
    public Manga getManga() {
        return mManga;
    }

    public void setManga(Manga manga) {
        mManga = manga;
        notifyPropertyChanged(BR.manga);
    }

    @Bindable
    public Setting getSetting() {
        return mSetting;
    }

    public void setSetting(Setting setting) {
        mSetting = setting;
        setLayoutOrientation(setting.getReadingMode());
        notifyPropertyChanged(BR.setting);
    }

    @Bindable
    public int getSettingVissile() {
        return mSettingVissile;
    }

    public void setSettingVissile(int settingVissile) {
        mSettingVissile = settingVissile;
        notifyPropertyChanged(BR.settingVissile);
    }

    @Bindable
    public int getLayoutOrientation() {
        return mLayoutOrientation;
    }

    public void setLayoutOrientation(int layoutOrientation) {
        mLayoutOrientation = layoutOrientation;
        notifyPropertyChanged(BR.layoutOrientation);
    }
}
