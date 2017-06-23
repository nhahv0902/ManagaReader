package toandoan.framgia.com.rxjavaretrofit.screen.reader;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import toandoan.framgia.com.rxjavaretrofit.BR;
import toandoan.framgia.com.rxjavaretrofit.data.model.Chap;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.utils.navigator.Navigator;

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
    private Navigator mNavigator;
    private ReaderAdapter mAdapter;
    private ReaderPreviewAdapter mPreviewAdapter;
    private RecyclerView mRecyclerReader;
    private int mCurrentPosition = 0;
    private int mChapSize;
    private Chap mChap;
    private Manga mManga;
    private int mChapPos;

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
        setChap(chap);
        setAdapter(new ReaderAdapter(this, chap.getContent()));
        setPreviewAdapter(new ReaderPreviewAdapter(this, chap.getContent()));
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
        mCurrentPosition++;
        if (mCurrentPosition != mChap.getContent().size()) {
            setCurrentPosition(mCurrentPosition);
            mRecyclerReader.smoothScrollToPosition(mCurrentPosition);
        }
        setLayoutControlVisible(VISIBLE);
    }

    @Override
    public void onBackClick() {
        mCurrentPosition--;
        if (mCurrentPosition != -1) {
            setCurrentPosition(mCurrentPosition);
            mRecyclerReader.smoothScrollToPosition(mCurrentPosition);
        }
        setLayoutControlVisible(VISIBLE);
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
        if (mLayoutControlVisible == GONE) setPreviewVisible(GONE);
    }

    public void onScrollDown() {
        setLayoutControlVisible(GONE);
        if (mLayoutControlVisible == GONE) setPreviewVisible(GONE);
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
    public int getChapSize() {
        return mChapSize;
    }

    public void setChapSize(int chapSize) {
        mChapSize = chapSize;
        notifyPropertyChanged(BR.chapSize);
    }

    @Bindable
    public Manga getManga() {
        return mManga;
    }

    public void setManga(Manga manga) {
        mManga = manga;
        notifyPropertyChanged(BR.manga);
    }
}
