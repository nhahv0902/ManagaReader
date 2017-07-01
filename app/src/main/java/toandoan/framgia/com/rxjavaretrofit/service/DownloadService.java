package toandoan.framgia.com.rxjavaretrofit.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by nhahv0902 on 7/1/2017.
 * <></>
 */

public class DownloadService extends Service {

    public class DownloadBinder extends Binder {
        public DownloadService getService() {
            return DownloadService.this;
        }
    }

    private final IBinder mDownloadBinder = new DownloadBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {


        return mDownloadBinder;
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}
