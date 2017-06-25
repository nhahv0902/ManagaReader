package toandoan.framgia.com.rxjavaretrofit.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import toandoan.framgia.com.rxjavaretrofit.data.model.Chap;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.data.source.RecentMangaDataSource;
import toandoan.framgia.com.rxjavaretrofit.data.source.local.sqlite.RecentMangaPersistenceContract;

import static toandoan.framgia.com.rxjavaretrofit.data.source.local.sqlite
        .RecentMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_ID;
import static toandoan.framgia.com.rxjavaretrofit.data.source.local.sqlite
        .RecentMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_MODIFIED_DATE;
import static toandoan.framgia.com.rxjavaretrofit.data.source.local.sqlite
        .RecentMangaPersistenceContract.RecentMangaEntry.TABLE_NAME;

/**
 * Created by toand on 6/25/2017.
 */

public class RecentMangaLocalDataSource extends BaseLocalDataSource
        implements RecentMangaDataSource {

    public RecentMangaLocalDataSource(Context context) {
        super(context);
    }

    @Override
    public Observable<List<Manga>> getAllRecentManga() {
        Cursor cursor = null;
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            String oderBy = COLUMN_MANGA_MODIFIED_DATE + " DESC";
            cursor = sqLiteDatabase.query(TABLE_NAME, null, null, null, null, null, oderBy);
            return Observable.just(getMangas(cursor));
        } catch (Exception e) {
            e.printStackTrace();
            return Observable.error(new NullPointerException(e.getMessage()));
        } finally {
            close();
            if (cursor!=null) cursor.close();
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
                RecentMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_NAME)));
        manga.setAvatar(cursor.getString(cursor.getColumnIndex(
                RecentMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_AVATAR)));
        manga.setLastModifiedData(
                cursor.getLong(cursor.getColumnIndex(COLUMN_MANGA_MODIFIED_DATE)));
        Chap chap = new Chap();
        chap.setName(cursor.getString(cursor.getColumnIndex(
                RecentMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_LASTED_CHAP_NAME)));
        chap.setId(cursor.getString(cursor.getColumnIndex(
                RecentMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_LASTED_CHAP_ID)));
        manga.setLastLocalChap(chap);
        return manga;
    }

    private ContentValues getContentValues(Manga manga) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_MANGA_ID, manga.getId());
        contentValues.put(RecentMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_NAME,
                manga.getName());
        contentValues.put(RecentMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_AVATAR,
                manga.getAvatar());
        contentValues.put(COLUMN_MANGA_MODIFIED_DATE, manga.getLastModifiedData());
        contentValues.put(
                RecentMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_LASTED_CHAP_NAME,
                manga.getLastLocalChap().getChap());
        contentValues.put(
                RecentMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_LASTED_CHAP_ID,
                manga.getLastLocalChap().getId());
        return contentValues;
    }

    private boolean isExitManga(Manga manga) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String arg = COLUMN_MANGA_ID + "=?";
        String[] args = { String.valueOf(manga.getId()) };
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, arg, args, null, null, null);
        if (cursor != null && cursor.moveToNext()) {
            cursor.close();
            return true;
        }
        if (cursor != null) cursor.close();
        return false;
    }

    @Override
    public void addRecentManga(Manga manga) {
        if (manga == null) {
            return;
        }
        if (isExitManga(manga)) {
            editManga(manga);
        } else {
            addManga(manga);
        }
    }

    public void addManga(Manga manga) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert(TABLE_NAME, null, getContentValues(manga));
        close();
    }

    public void editManga(Manga manga) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String arg = COLUMN_MANGA_ID + "=?";
        String[] args = { String.valueOf(manga.getId()) };
        sqLiteDatabase.update(TABLE_NAME, getContentValues(manga), arg, args);
        close();
    }

    @Override
    public void removeRecentMangaById(int mangaId) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String arg = COLUMN_MANGA_ID + "=?";
        String[] args = { String.valueOf(mangaId) };
        sqLiteDatabase.delete(TABLE_NAME, arg, args);
        close();
    }

    @Override
    public void removeAllRecentManga() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, null, null);
        close();
    }
}
