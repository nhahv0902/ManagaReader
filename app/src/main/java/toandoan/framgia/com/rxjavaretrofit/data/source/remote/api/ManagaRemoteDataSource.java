package toandoan.framgia.com.rxjavaretrofit.data.source.remote.api;

import java.util.List;
import rx.Observable;
import rx.functions.Func1;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.data.model.Response;
import toandoan.framgia.com.rxjavaretrofit.data.source.MangaDataSource;
import toandoan.framgia.com.rxjavaretrofit.data.source.remote.BaseRemoteDataSource;
import toandoan.framgia.com.rxjavaretrofit.data.source.remote.api.service.AppApi;
import toandoan.framgia.com.rxjavaretrofit.utils.Utils;

import static toandoan.framgia.com.rxjavaretrofit.utils.Constant.LIMIT_OFFSET;
import static toandoan.framgia.com.rxjavaretrofit.utils.Constant.SOURCE;
import static toandoan.framgia.com.rxjavaretrofit.utils.Constant.VERSION_APP;

/**
 * Created by framgia on 18/04/2017.
 */

public class ManagaRemoteDataSource extends BaseRemoteDataSource implements MangaDataSource {
    public ManagaRemoteDataSource(AppApi api) {
        super(api);
    }

    @Override
    public Observable<List<Manga>> getPopularManga(String option, int page) {

        return mApi.getManga(VERSION_APP, SOURCE, LIMIT_OFFSET, option, page)
                .flatMap(new Func1<Response<List<Manga>>, Observable<List<Manga>>>() {
                    @Override
                    public Observable<List<Manga>> call(Response<List<Manga>> listResponse) {
                        return Utils.getResponse(listResponse);
                    }
                });
    }
}
