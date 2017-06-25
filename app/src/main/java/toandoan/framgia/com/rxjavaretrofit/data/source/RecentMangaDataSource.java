package toandoan.framgia.com.rxjavaretrofit.data.source;

import java.util.List;
import rx.Observable;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;

/**
 * Created by toand on 6/25/2017.
 */

public interface RecentMangaDataSource {
    Observable<List<Manga>> getAllRecentManga();

    void addRecentManga(Manga manga);

    void removeRecentMangaById(int mangaId);

    void removeAllRecentManga();
}
