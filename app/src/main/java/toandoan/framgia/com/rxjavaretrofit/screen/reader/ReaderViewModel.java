package toandoan.framgia.com.rxjavaretrofit.screen.reader;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.widget.RecyclerView;
import toandoan.framgia.com.rxjavaretrofit.BR;
import toandoan.framgia.com.rxjavaretrofit.data.model.Chap;
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
    private Navigator mNavigator;
    private ReaderAdapter mAdapter;
    private ReaderPreviewAdapter mPreviewAdapter;
    private int mPreviewVisible = GONE;
    private RecyclerView mRecyclerReader;

    public ReaderViewModel(Navigator navigator) {
        mNavigator = navigator;
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
        setAdapter(new ReaderAdapter(chap.getContent()));
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
    public void onRecyclerReaderClick() {
        setPreviewVisible(mPreviewVisible == GONE ? VISIBLE : GONE);
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
}
