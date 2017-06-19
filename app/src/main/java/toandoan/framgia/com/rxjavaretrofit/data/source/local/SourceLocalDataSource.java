package toandoan.framgia.com.rxjavaretrofit.data.source.local;

import com.google.gson.Gson;
import java.util.List;
import rx.Observable;
import toandoan.framgia.com.rxjavaretrofit.data.model.Source;
import toandoan.framgia.com.rxjavaretrofit.data.source.SourcesDataSource;
import toandoan.framgia.com.rxjavaretrofit.data.source.local.sharedprf.SharedPrefsApi;

import static toandoan.framgia.com.rxjavaretrofit.data.source.local.sharedprf.SharedPrefsKey
        .PREF_SOURCE;

/**
 * Created by framgia on 19/06/2017.
 */

public class SourceLocalDataSource implements SourcesDataSource {
    private SharedPrefsApi mSharedPrefsApi;

    public SourceLocalDataSource(SharedPrefsApi sharedPrefsApi) {
        mSharedPrefsApi = sharedPrefsApi;
    }

    @Override
    public Observable<List<Source>> getSources() {
        // no ops
        return null;
    }

    @Override
    public Observable<Source> getSelectedSources() {
        return Observable.just(getSelectedSource());
    }

    @Override
    public Observable<Boolean> saveCurrentSource(Source source) {
        return Observable.just(saveSelectedSource(source));
    }

    @Override
    public String getCurrentSourceCode() {
        Source source = getSelectedSource();
        return source != null ? source.getSlug() : null;
    }

    public Source getSelectedSource() {
        String str = mSharedPrefsApi.get(PREF_SOURCE, String.class);
        return new Gson().fromJson(str, Source.class);
    }

    public Boolean saveSelectedSource(Source source) {
        mSharedPrefsApi.put(PREF_SOURCE, source.toString());
        return true;
    }
}
