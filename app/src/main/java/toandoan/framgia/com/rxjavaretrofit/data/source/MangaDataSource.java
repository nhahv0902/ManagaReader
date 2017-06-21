package toandoan.framgia.com.rxjavaretrofit.data.source;

import java.util.List;
import rx.Observable;
import toandoan.framgia.com.rxjavaretrofit.data.model.Chap;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;

/**
 * Created by framgia on 18/04/2017.
 */

public interface MangaDataSource {
    Observable<List<Manga>> getPopularManga(String source, String option, int page);

    Observable<List<Manga>> getAllMangas(String source);

    Observable<Manga> getMangaById(int id);

    Observable<Chap> getChapById(String chapId);
}
