package toandoan.framgia.com.rxjavaretrofit.data.source.local;

import android.content.Context;
import java.util.List;
import rx.Observable;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.data.source.RecentMangaDataSource;

/**
 * Created by toand on 6/25/2017.
 */

public class RecentMangaRepository implements RecentMangaDataSource {
    private RecentMangaLocalDataSource mSource;

    public RecentMangaRepository(Context context) {
        mSource = new RecentMangaLocalDataSource(context);
    }

    @Override
    public Observable<List<Manga>> getAllRecentManga() {
        return mSource.getAllRecentManga();
    }

    @Override
    public void addRecentManga(Manga manga) {
        mSource.addRecentManga(manga);
    }

    @Override
    public void removeRecentMangaById(int mangaId) {
        mSource.removeRecentMangaById(mangaId);
    }

    @Override
    public void removeAllRecentManga() {
        mSource.removeAllRecentManga();
    }
}
