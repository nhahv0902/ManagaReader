package toandoan.framgia.com.rxjavaretrofit.data.source;

import java.util.List;
import rx.Observable;
import toandoan.framgia.com.rxjavaretrofit.data.model.Chap;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;

/**
 * Created by framgia on 18/04/2017.
 */

public class MangaDataRepository implements MangaDataSource {
    private MangaDataSource mRemoteDataSource;

    public MangaDataRepository(MangaDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    @Override
    public Observable<List<Manga>> getPopularManga(String source, String option, int page) {
        return mRemoteDataSource.getPopularManga(source, option, page);
    }

    @Override
    public Observable<List<Manga>> getMangaByGenres(String source, List<String> genres, int page) {
        return mRemoteDataSource.getMangaByGenres(source, genres, page);
    }

    @Override
    public Observable<List<Manga>> getAllMangas(String source) {
        return mRemoteDataSource.getAllMangas(source);
    }

    @Override
    public Observable<Manga> getMangaById(int id) {
        return mRemoteDataSource.getMangaById(id);
    }

    @Override
    public Observable<Chap> getChapById(String chapId) {
        return mRemoteDataSource.getChapById(chapId);
    }
}
