package toandoan.framgia.com.rxjavaretrofit.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.greenrobot.eventbus.EventBus;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.data.model.Chap;
import toandoan.framgia.com.rxjavaretrofit.data.model.DownloadMessage;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.data.source.DownloadRepository;
import toandoan.framgia.com.rxjavaretrofit.data.source.MangaDataRepository;
import toandoan.framgia.com.rxjavaretrofit.data.source.MangaDataSource;
import toandoan.framgia.com.rxjavaretrofit.data.source.remote.ManagaRemoteDataSource;
import toandoan.framgia.com.rxjavaretrofit.data.source.remote.api.service.AppServiceClient;
import toandoan.framgia.com.rxjavaretrofit.utils.ImageDownloadManager;

/**
 * Created by nhahv0902 on 7/1/2017.
 * <></>
 */

public class DownloadService extends Service {

    private static final String BUNDLE_MANGAK = "BUNDLE_MANGAK";
    private static final String BUNDLE_CHAPTER = "BUNDLE_CHAPTER";
    private DownloadRepository mDownloadRepository;
    private MangaDataSource mMangaDataSource;
    private Manga mMangak;
    private List<Chap> mChapters = new ArrayList<>();
    private CompositeSubscription mSubscription;
    private ImageDownloadManager mDownloadManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mDownloadRepository = new DownloadRepository(this);
        mMangaDataSource =
                new MangaDataRepository(new ManagaRemoteDataSource(AppServiceClient.getInstance()));
        mSubscription = new CompositeSubscription();
        mDownloadManager = ImageDownloadManager.getInstance(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        getDataIntent(intent);

        int size = mChapters.size();
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                getChap(mChapters.get(i), true);
            } else {
                getChap(mChapters.get(i), false);
            }
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mSubscription != null) mSubscription.clear();
    }

    private void getDataIntent(Intent intent) {
        if (intent == null) return;
        Bundle bundle = intent.getExtras();
        if (bundle == null) return;

        mMangak = (Manga) bundle.getSerializable(BUNDLE_MANGAK);
        String contents = bundle.getString(BUNDLE_CHAPTER);
        Type type = new TypeToken<ArrayList<Chap>>() {
        }.getType();
        List<Chap> array = new Gson().fromJson(contents, type);
        assert array != null;
        mChapters.addAll(array);
    }

    public void getChap(Chap chap, final boolean isItemEnd) {
        Subscription subscription = mMangaDataSource.getChapById(chap.getId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<Chap>() {
                    @Override
                    public void call(Chap chap) {
                        getChapterSuccess(chap, isItemEnd);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.d("TAG", "error  = " + throwable.toString());
                    }
                });
        mSubscription.add(subscription);
    }

    private void getChapterSuccess(final Chap chap, final boolean isItemEnd) {
        mDownloadRepository.addChapter(chap);
        mDownloadManager.downloads(String.valueOf(mMangak.getId()), String.valueOf(chap.getId()),
                chap.getContent(), new ImageDownloadManager.PercentCallback() {
                    @Override
                    public void onPercent(int percent) {
                        //                        setChapIndex(chap.getChap());
                        //                        setProgressStatus(percent);

                        DownloadMessage message = new DownloadMessage();
                        message.setId(mMangak.getId());
                        message.setName(mMangak.getName());
                        message.setAuthor(mMangak.getAuthorStr());
                        message.setChapter(chap.getChap());
                        message.setPercent(percent);
                        EventBus.getDefault().post(message);
                    }

                    @Override
                    public void onDownloaded() {
                        if (isItemEnd) {
                            //                            setDownload(false);
                            //                            mPresenter.getMangakByIdOfLocal
                            // (mMangakId);
                            Toast.makeText(DownloadService.this, R.string.msg_download_success,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
