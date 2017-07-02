package toandoan.framgia.com.rxjavaretrofit.screen.settings;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.net.Uri;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import com.bumptech.glide.Glide;
import java.io.File;
import java.util.List;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import toandoan.framgia.com.rxjavaretrofit.AppApplication;
import toandoan.framgia.com.rxjavaretrofit.BR;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.screen.title.TitleActivity;
import toandoan.framgia.com.rxjavaretrofit.utils.navigator.Navigator;

/**
 * Exposes the data to be used in the Settings screen.
 */

public class SettingsViewModel extends BaseObservable implements SettingsContract.ViewModel {

    private static final String TAG = "SettingsViewModel";
    private SettingsContract.Presenter mPresenter;
    private Navigator mNavigator;
    private String mCacheSize;

    public SettingsViewModel(Navigator navigator) {
        mNavigator = navigator;
    }

    private void getGlideCacheSize() {
        long fileSizeInBytes = dirSize(Glide.getPhotoCacheDir(mNavigator.getContext()));
        if (fileSizeInBytes < 1024) {
            setCacheSize(fileSizeInBytes + " Byte");
            return;
        }
        long fileSizeInKB = fileSizeInBytes / 1024;
        if (fileSizeInKB < 1024) {
            setCacheSize(fileSizeInKB + " KB");
            return;
        }
        long fileSizeInMB = fileSizeInKB / 1024;
        setCacheSize(fileSizeInMB + " MB");
    }

    private static long dirSize(File dir) {
        if (dir.exists()) {
            long result = 0;
            File[] fileList = dir.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                if (fileList[i].isDirectory()) {
                    result += dirSize(fileList[i]);
                } else {
                    result += fileList[i].length();
                }
            }
            return result;
        }
        return 0;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        getGlideCacheSize();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(SettingsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onShareAppClick() {
        try {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT,
                    AppApplication.getContext().getString(R.string.app_name));
            String appLink =
                    "https://play.google.com/store/apps/details?id=" + AppApplication.getContext()
                            .getPackageName();
            i.putExtra(Intent.EXTRA_TEXT, appLink);
            mNavigator.startActivity(Intent.createChooser(i, "Choose one"));
        } catch (Exception e) {
            e.toString();
        }
    }

    @Override
    public void onRateAppClick() {
        mNavigator.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(
                "https://play.google.com/store/apps/details?id=" + AppApplication.getContext()
                        .getPackageName())));
    }

    @Override
    public void onDisclaimerClick() {
        mNavigator.startActivity(
                TitleActivity.getInstance(mNavigator.getContext(), R.string.title_disclaimer,
                        R.string.description_disclaimer));
    }

    @Override
    public void onAboutUsClick() {
        mNavigator.startActivity(
                TitleActivity.getInstance(mNavigator.getContext(), R.string.title_about_us,
                        R.string.description_about_us));
    }

    @Override
    public void onEmailClick() {
        try {
            Intent sendFeedBack = new Intent(Intent.ACTION_SEND);
            sendFeedBack.setType("text/plain");
            PackageManager pm = AppApplication.getContext().getPackageManager();
            List<ResolveInfo> activityList = pm.queryIntentActivities(sendFeedBack, 0);

            PackageInfo pInfo = null;
            try {
                pInfo = AppApplication.getContext()
                        .getPackageManager()
                        .getPackageInfo(AppApplication.getContext().getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            DisplayMetrics displayMetrics =
                    AppApplication.getContext().getResources().getDisplayMetrics();
            int screenWidthInPix = displayMetrics.widthPixels;
            int screenheightInPix = displayMetrics.heightPixels;

            String details = "Model: "
                    + Build.MODEL
                    + "\nVersion: "
                    + Build.VERSION.RELEASE
                    + "\nScreen: "
                    + screenWidthInPix
                    + "x"
                    + screenheightInPix;

            String version = pInfo.versionName;

            for (final ResolveInfo app : activityList) {
                if ((app.activityInfo.name).contains("android.gm")) {
                    ComponentName name =
                            new ComponentName(app.activityInfo.applicationInfo.packageName,
                                    app.activityInfo.name);
                    sendFeedBack.putExtra(Intent.EXTRA_EMAIL, new String[] { "abc_def@gmail.com" });
                    sendFeedBack.putExtra(Intent.EXTRA_SUBJECT,
                            AppApplication.getContext().getString(R.string.app_name)
                                    + " _ "
                                    + version
                                    + " _ "
                                    + details);
                    sendFeedBack.setComponent(name);
                    mNavigator.startActivity(sendFeedBack);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClearCacheClick() {
        Observable<Boolean> observable = Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                File dir = Glide.getPhotoCacheDir(mNavigator.getContext());
                if (dir.isDirectory()) {
                    String[] children = dir.list();
                    for (int i = 0; i < children.length; i++) {
                        new File(dir, children[i]).delete();
                    }
                }
                subscriber.onNext(true);
            }
        });

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        getGlideCacheSize();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.d(TAG, "call: " + throwable.getMessage());
                    }
                });
    }

    @Bindable
    public String getCacheSize() {
        return mCacheSize;
    }

    public void setCacheSize(String cacheSize) {
        mCacheSize = cacheSize;
        notifyPropertyChanged(BR.cacheSize);
    }
}
