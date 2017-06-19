package toandoan.framgia.com.rxjavaretrofit.data.source.remote;

import java.util.List;
import rx.Observable;
import rx.functions.Func1;
import toandoan.framgia.com.rxjavaretrofit.data.model.Response;
import toandoan.framgia.com.rxjavaretrofit.data.model.Source;
import toandoan.framgia.com.rxjavaretrofit.data.source.SourcesDataSource;
import toandoan.framgia.com.rxjavaretrofit.data.source.remote.api.service.AppApi;
import toandoan.framgia.com.rxjavaretrofit.utils.Utils;

import static toandoan.framgia.com.rxjavaretrofit.utils.Constant.VERSION_APP;

/**
 * Created by framgia on 19/06/2017.
 */

public class SourcesRemoteDataSource extends BaseRemoteDataSource implements SourcesDataSource {

    public SourcesRemoteDataSource(AppApi api) {
        super(api);
    }

    @Override
    public Observable<List<Source>> getSources() {
        return mApi.getSource(VERSION_APP)
                .flatMap(new Func1<Response<List<Source>>, Observable<List<Source>>>() {
                    @Override
                    public Observable<List<Source>> call(Response<List<Source>> listResponse) {
                        return Utils.getResponse(listResponse);
                    }
                });
    }

    @Override
    public Observable<Source> getSelectedSources() {
        // no ops
        return null;
    }

    @Override
    public Observable<Boolean> saveCurrentSource(Source source) {
        // no ops
        return null;
    }
}
