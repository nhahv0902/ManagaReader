package toandoan.framgia.com.rxjavaretrofit;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.data.source.local.realm.DataLocalMigration;
import toandoan.framgia.com.rxjavaretrofit.data.source.remote.api.service.AppServiceClient;

/**
 * Created by framgia on 18/04/2017.
 */

public class AppApplication extends Application {
    private static final String REALM_SCHEMA_NAME = "data.realm";
    private static final int REALM_SCHEMA_VERSION = 0;
    private static Context sContext;
    public static List<Manga> sMangas;

    @Override
    public void onCreate() {
        super.onCreate();
        AppServiceClient.initialize(this);
        initAndMigrateRealmIfNeeded();
        sContext = this;
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private void initAndMigrateRealmIfNeeded() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name(REALM_SCHEMA_NAME)
                .schemaVersion(REALM_SCHEMA_VERSION)
                .migration(new DataLocalMigration())
                .build();
        Realm.setDefaultConfiguration(config);
        Realm realm = Realm.getDefaultInstance(); // Automatically run migration if needed
        realm.close();
    }

    public static Context getContext() {
        return sContext;
    }
}
