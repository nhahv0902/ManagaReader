package toandoan.framgia.com.rxjavaretrofit.screen.downloading;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.android.databinding.library.baseAdapters.BR;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.data.model.MessageDownloading;

/**
 * Exposes the data to be used in the Downloading screen.
 */

public class DownloadingViewModel extends BaseObservable implements DownloadingContract.ViewModel {

    private DownloadingContract.Presenter mPresenter;
    private DownloadingAdapter mAdapter;
    private List<MessageDownloading> mMessageDownloadings = new ArrayList<>();

    public DownloadingViewModel() {
        mMessageDownloadings.add(new MessageDownloading("Akaneiro ni Somaru Saka Manga",
                "http://3.c.mpcdn.net/34075//7/180.jpg", 45,
                new ArrayList<>(Arrays.asList("1", "3", "4")),
                MessageDownloading.StatusDownload.DOWNLOADING));
        mMessageDownloadings.add(new MessageDownloading("Akaneiro ni Somaru Saka Manga",
                "http://3.c.mpcdn.net/34075//7/180.jpg", 45,
                new ArrayList<>(Arrays.asList("1", "3", "4")),
                MessageDownloading.StatusDownload.WAITING));
        mMessageDownloadings.add(new MessageDownloading("Akaneiro ni Somaru Saka Manga",
                "http://3.c.mpcdn.net/34075//7/180.jpg", 45,
                new ArrayList<>(Arrays.asList("1", "3", "4")),
                MessageDownloading.StatusDownload.WAITING));
        mMessageDownloadings.add(new MessageDownloading("Akaneiro ni Somaru Saka Manga",
                "http://3.c.mpcdn.net/34075//7/180.jpg", 45,
                new ArrayList<>(Arrays.asList("1", "3", "4")),
                MessageDownloading.StatusDownload.WAITING));
        mMessageDownloadings.add(new MessageDownloading("Akaneiro ni Somaru Saka Manga",
                "http://3.c.mpcdn.net/34075//7/180.jpg", 45,
                new ArrayList<>(Arrays.asList("1", "3", "4")),
                MessageDownloading.StatusDownload.WAITING));

        mAdapter = new DownloadingAdapter(mMessageDownloadings, this);
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
    public void setPresenter(DownloadingContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Bindable
    public DownloadingAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(DownloadingAdapter adapter) {
        mAdapter = adapter;
        notifyPropertyChanged(BR.adapter);
    }
}
