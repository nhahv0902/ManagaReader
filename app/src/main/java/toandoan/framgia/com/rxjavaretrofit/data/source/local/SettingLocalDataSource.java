package toandoan.framgia.com.rxjavaretrofit.data.source.local;

import android.text.TextUtils;
import com.google.gson.Gson;
import rx.Observable;
import toandoan.framgia.com.rxjavaretrofit.data.model.Setting;
import toandoan.framgia.com.rxjavaretrofit.data.source.SettingDataSource;
import toandoan.framgia.com.rxjavaretrofit.data.source.local.sharedprf.SharedPrefsApi;

import static toandoan.framgia.com.rxjavaretrofit.data.source.local.sharedprf.SharedPrefsKey
        .PREF_SETTINGS;

/**
 * Created by toand on 6/25/2017.
 */

public class SettingLocalDataSource implements SettingDataSource {
    private SharedPrefsApi mSharedPrefsApi;

    public SettingLocalDataSource(SharedPrefsApi sharedPrefsApi) {
        mSharedPrefsApi = sharedPrefsApi;
    }

    @Override
    public void saveSetting(Setting setting) {
        mSharedPrefsApi.put(PREF_SETTINGS, new Gson().toJson(setting));
    }

    @Override
    public Observable<Setting> getSettings() {
        return Observable.just(getCurrentSetting());
    }

    public Setting getCurrentSetting() {
        String str = mSharedPrefsApi.get(PREF_SETTINGS, String.class);
        if (TextUtils.isEmpty(str)) return new Setting();
        return new Gson().fromJson(str, Setting.class);
    }
}
