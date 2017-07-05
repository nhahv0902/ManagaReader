package toandoan.framgia.com.rxjavaretrofit.screen.downloading;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.android.databinding.library.baseAdapters.BR;
import java.util.ArrayList;
import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.data.model.MessageDownloading;
import toandoan.framgia.com.rxjavaretrofit.data.model.PercentDownload;

/**
 * Exposes the data to be used in the Downloading screen.
 */

public class DownloadingViewModel extends BaseObservable implements DownloadingContract.ViewModel {

    private DownloadingContract.Presenter mPresenter;
    private DownloadingAdapter mAdapter;
    private List<MessageDownloading> mMessageDownloadings = new ArrayList<>();

    public DownloadingViewModel() {

        PercentDownload item = new PercentDownload("1", 10);
        PercentDownload item2 = new PercentDownload("3", 34);
        PercentDownload item3 = new PercentDownload("5", 55);
        List<PercentDownload> array = new ArrayList<>();
        array.add(item);
        array.add(item2);
        array.add(item3);

        List<PercentDownload> array2 = new ArrayList<>();
        array2.add(item);
        array2.add(item2);
        array2.add(item3);
        array2.add(item3);
        array2.add(item3);
        array2.add(item3);
        array2.add(item3);
        array2.add(item3);
        array2.add(item3);
        array2.add(item3);

        mMessageDownloadings.add(new MessageDownloading("Akaneiro ni Somaru Saka Manga",
                "http://3.c.mpcdn.net/34075//7/180.jpg", array2,
                MessageDownloading.StatusDownload.DOWNLOADING));
        mMessageDownloadings.add(new MessageDownloading("Akaneiro ni Somaru Saka Manga",
                "http://3.c.mpcdn.net/34075//7/180.jpg", array2,
                MessageDownloading.StatusDownload.WAITING));
        mMessageDownloadings.add(new MessageDownloading("Akaneiro ni Somaru Saka Manga",
                "http://3.c.mpcdn.net/34075//7/180.jpg", array2,
                MessageDownloading.StatusDownload.WAITING));
        mMessageDownloadings.add(new MessageDownloading("Akaneiro ni Somaru Saka Manga",
                "http://3.c.mpcdn.net/34075//7/180.jpg", array2,
                MessageDownloading.StatusDownload.WAITING));
        mMessageDownloadings.add(new MessageDownloading("Akaneiro ni Somaru Saka Manga",
                "http://3.c.mpcdn.net/34075//7/180.jpg", array2,
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

    @Override
    public void onClickExpandable(MessageDownloading message, int position) {
        message.setExpandable(!message.isExpandable());
        mAdapter.notifyItemChanged(position);
        setAdapter(mAdapter);
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
