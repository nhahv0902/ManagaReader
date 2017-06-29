package toandoan.framgia.com.rxjavaretrofit.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.data.source.FavoriteDataSource;
import toandoan.framgia.com.rxjavaretrofit.data.source.local.sqlite
        .FavoriteMangaPersistenceContract;

import static toandoan.framgia.com.rxjavaretrofit.data.source.local.sqlite
        .FavoriteMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_ID;
import static toandoan.framgia.com.rxjavaretrofit.data.source.local.sqlite
        .FavoriteMangaPersistenceContract.RecentMangaEntry.TABLE_NAME;

/**
 * Created by toand on 6/25/2017.
 */

public class FavoriteLocalDataSource extends BaseLocalDataSource implements FavoriteDataSource {

    public FavoriteLocalDataSource(Context context) {
        super(context);
    }

    @Override
    public Observable<List<Manga>> getAllFavoriteManga() {
        Cursor cursor = null;
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            cursor = sqLiteDatabase.query(TABLE_NAME, null, null, null, null, null, null);
            return Observable.just(getMangas(cursor));
        } catch (Exception e) {
            e.printStackTrace();
            return Observable.error(new NullPointerException(e.getMessage()));
        } finally {
            close();
            if (cursor != null) cursor.close();
        }
    }

    private List<Manga> getMangas(Cursor cursor) {
        List<Manga> result = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                result.add(getManga(cursor));
            } while (cursor.moveToNext());
        }
        return result;
    }

    private Manga getManga(Cursor cursor) {
        if (cursor == null) return null;
        Manga manga = new Manga();
        manga.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_MANGA_ID)));
        manga.setName(cursor.getString(cursor.getColumnIndex(
                FavoriteMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_NAME)));
        manga.setAvatar(cursor.getString(cursor.getColumnIndex(
                FavoriteMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_AVATAR)));
        return manga;
    }

    private ContentValues getContentValues(Manga manga) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_MANGA_ID, manga.getId());
        contentValues.put(FavoriteMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_NAME,
                manga.getName());
        contentValues.put(FavoriteMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_AVATAR,
                manga.getAvatar());
        return contentValues;
    }

    @Override
    public boolean isExitFavoriteManga(int id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String arg = COLUMN_MANGA_ID + "=?";
        String[] args = { String.valueOf(id) };
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, arg, args, null, null, null);
        if (cursor != null && cursor.moveToNext()) {
            cursor.close();
            return true;
        }
        if (cursor != null) cursor.close();
        return false;
    }

    @Override
    public void addFavoriteManga(Manga manga) {
        if (manga == null) {
            return;
        }
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert(TABLE_NAME, null, getContentValues(manga));
        close();
    }

    @Override
    public void removeFavoriteMangaById(int mangaId) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String arg = COLUMN_MANGA_ID + "=?";
        String[] args = { String.valueOf(mangaId) };
        sqLiteDatabase.delete(TABLE_NAME, arg, args);
        close();
    }

    @Override
    public void removeAllFavoriteManga() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, null, null);
        close();
    }
}
