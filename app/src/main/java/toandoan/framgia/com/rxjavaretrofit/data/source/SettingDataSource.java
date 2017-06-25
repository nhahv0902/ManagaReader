package toandoan.framgia.com.rxjavaretrofit.data.source;

import rx.Observable;
import toandoan.framgia.com.rxjavaretrofit.data.model.Setting;

/**
 * Created by toand on 6/25/2017.
 */

public interface SettingDataSource {
    void saveSetting(Setting setting);

    Observable<Setting> getSettings();
}
