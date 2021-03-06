package toandoan.framgia.com.rxjavaretrofit.data.source.remote;

import java.util.List;
import rx.Observable;
import rx.functions.Func1;
import toandoan.framgia.com.rxjavaretrofit.AppApplication;
import toandoan.framgia.com.rxjavaretrofit.data.model.Chap;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.data.model.Response;
import toandoan.framgia.com.rxjavaretrofit.data.source.MangaDataSource;
import toandoan.framgia.com.rxjavaretrofit.data.source.remote.api.service.AppApi;
import toandoan.framgia.com.rxjavaretrofit.utils.Utils;

import static toandoan.framgia.com.rxjavaretrofit.utils.Constant.LIMIT_OFFSET;
import static toandoan.framgia.com.rxjavaretrofit.utils.Constant.VERSION_APP;

/**
 * Created by framgia on 18/04/2017.
 */

public class ManagaRemoteDataSource extends BaseRemoteDataSource implements MangaDataSource {
    public ManagaRemoteDataSource(AppApi api) {
        super(api);
    }

    @Override
    public Observable<List<Manga>> getPopularManga(String source, String option, int page) {
        return mApi.getManga(VERSION_APP, source, LIMIT_OFFSET, option, page)
                .flatMap(new Func1<Response<List<Manga>>, Observable<List<Manga>>>() {
                    @Override
                    public Observable<List<Manga>> call(Response<List<Manga>> listResponse) {
                        return Utils.getResponse(listResponse);
                    }
                });
    }

    @Override
    public Observable<List<Manga>> getMangaByGenres(String source, List<String> genres, int page) {
        return mApi.getMangaByGenres(VERSION_APP, source, LIMIT_OFFSET, genres, page)
                .flatMap(new Func1<Response<List<Manga>>, Observable<List<Manga>>>() {
                    @Override
                    public Observable<List<Manga>> call(Response<List<Manga>> listResponse) {
                        return Utils.getResponse(listResponse);
                    }
                });
    }

    @Override
    public Observable<List<Manga>> getAllMangas(String source) {
        if (AppApplication.sMangas != null) return Observable.just(AppApplication.sMangas);
        return mApi.getAllMangas(VERSION_APP, source)
                .flatMap(new Func1<Response<List<Manga>>, Observable<List<Manga>>>() {
                    @Override
                    public Observable<List<Manga>> call(Response<List<Manga>> listResponse) {
                        return Utils.getResponse(listResponse);
                    }
                });
    }

    @Override
    public Observable<Manga> getMangaById(int id) {
        return mApi.getMangaById(id).flatMap(new Func1<Response<Manga>, Observable<Manga>>() {
            @Override
            public Observable<Manga> call(Response<Manga> mangaResponse) {
                return Utils.getResponse(mangaResponse);
            }
        });
    }

    @Override
    public Observable<Chap> getChapById(String chapId) {
        return mApi.getChapById(chapId).flatMap(new Func1<Response<Chap>, Observable<Chap>>() {
            @Override
            public Observable<Chap> call(Response<Chap> chapResponse) {
                return Utils.getResponse(chapResponse);
            }
        });
    }
}
