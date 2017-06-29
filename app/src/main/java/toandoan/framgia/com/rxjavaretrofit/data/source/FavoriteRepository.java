package toandoan.framgia.com.rxjavaretrofit.data.source;

import android.content.Context;
import java.util.List;
import rx.Observable;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.data.source.local.FavoriteLocalDataSource;

/**
 * Created by toand on 6/29/2017.
 */

public class FavoriteRepository implements FavoriteDataSource {
    private FavoriteDataSource mFavoriteLocalaSource;

    public FavoriteRepository(Context context) {
        mFavoriteLocalaSource = new FavoriteLocalDataSource(context);
    }

    @Override
    public Observable<List<Manga>> getAllFavoriteManga() {
        return mFavoriteLocalaSource.getAllFavoriteManga();
    }

    @Override
    public void addFavoriteManga(Manga manga) {
        mFavoriteLocalaSource.addFavoriteManga(manga);
    }

    @Override
    public void removeFavoriteMangaById(int mangaId) {
        mFavoriteLocalaSource.removeFavoriteMangaById(mangaId);
    }

    @Override
    public void removeAllFavoriteManga() {
        mFavoriteLocalaSource.removeAllFavoriteManga();
    }

    @Override
    public boolean isExitFavoriteManga(int id) {
        return mFavoriteLocalaSource.isExitFavoriteManga(id);
    }
}
