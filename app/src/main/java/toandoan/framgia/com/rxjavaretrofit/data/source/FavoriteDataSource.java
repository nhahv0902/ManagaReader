package toandoan.framgia.com.rxjavaretrofit.data.source;

import java.util.List;
import rx.Observable;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;

/**
 * Created by toand on 6/29/2017.
 */

public interface FavoriteDataSource {
    Observable<List<Manga>> getAllFavoriteManga();

    void addFavoriteManga(Manga manga);

    void removeFavoriteMangaById(int mangaId);

    void removeAllFavoriteManga();

    boolean isExitFavoriteManga(int id);
}
