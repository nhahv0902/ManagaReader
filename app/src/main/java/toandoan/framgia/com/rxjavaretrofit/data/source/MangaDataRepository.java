package toandoan.framgia.com.rxjavaretrofit.data.source;

import java.util.List;
import rx.Observable;
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
    public Observable<List<Manga>> getPopularManga(String option, int page) {
        return mRemoteDataSource.getPopularManga(option, page);
    }
}
