package toandoan.framgia.com.rxjavaretrofit.data.source;

import rx.Observable;
import toandoan.framgia.com.rxjavaretrofit.data.model.Setting;
import toandoan.framgia.com.rxjavaretrofit.data.source.local.SettingLocalDataSource;
import toandoan.framgia.com.rxjavaretrofit.data.source.local.sharedprf.SharedPrefsApi;

/**
 * Created by toand on 6/25/2017.
 */

public class SettingDataRepository implements SettingDataSource {
    private SettingDataSource mLocalDataSource;

    public SettingDataRepository(SharedPrefsApi sharedPrefsApi) {
        mLocalDataSource = new SettingLocalDataSource(sharedPrefsApi);
    }

    @Override
    public void saveSetting(Setting setting) {
        mLocalDataSource.saveSetting(setting);
    }

    @Override
    public Observable<Setting> getSettings() {
        return mLocalDataSource.getSettings();
    }
}
