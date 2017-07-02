package toandoan.framgia.com.rxjavaretrofit.screen.downloading;

import android.util.Log;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import toandoan.framgia.com.rxjavaretrofit.data.model.DownloadMessage;

/**
 * Exposes the data to be used in the Downloading screen.
 */

public class DownloadingViewModel implements DownloadingContract.ViewModel {

    private DownloadingContract.Presenter mPresenter;

    public DownloadingViewModel() {
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void setPresenter(DownloadingContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onMessageEvent(DownloadMessage event) {
        Log.d("TAG", "downloading  = " + event.toString());
    }
}
