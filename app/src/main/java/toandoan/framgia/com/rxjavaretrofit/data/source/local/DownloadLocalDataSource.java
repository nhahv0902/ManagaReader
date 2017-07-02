package toandoan.framgia.com.rxjavaretrofit.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import toandoan.framgia.com.rxjavaretrofit.data.model.Chap;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.data.source.DownloadDataSource;
import toandoan.framgia.com.rxjavaretrofit.data.source.local.sqlite
        .DownloadMangaPersistenceContract;

import static toandoan.framgia.com.rxjavaretrofit.data.source.local.sqlite
        .DownloadMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_ID;
import static toandoan.framgia.com.rxjavaretrofit.data.source.local.sqlite
        .DownloadMangaPersistenceContract.RecentMangaEntry.TABLE_NAME;

/**
 * Created by nhahv0902 on 7/2/2017.
 * <></>
 */

public class DownloadLocalDataSource extends BaseLocalDataSource implements DownloadDataSource {
    public DownloadLocalDataSource(Context context) {
        super(context);
    }

    @Override
    public Observable<List<Manga>> getAllMangakDownload() {
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
                DownloadMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_NAME)));
        manga.setAvatar(cursor.getString(cursor.getColumnIndex(
                DownloadMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_AVATAR)));
        String chapters = cursor.getString(cursor.getColumnIndex(
                DownloadMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_CHAP_DOWNLOAD));
        manga.setChaps(new ArrayList<Chap>());

        List<String> chapterArrary = new ArrayList<>();
        chapterArrary.addAll(new Gson().fromJson(chapters, chapterArrary.getClass()));
        for (String item : chapterArrary) {
            Chap chap = new Chap();
            chap.setId(item);
            manga.getChaps().add(chap);
        }

        String authors = cursor.getString(cursor.getColumnIndex(
                DownloadMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_AUTHOR));
        List<String> authorArray = new ArrayList<>();
        authorArray.addAll(new Gson().fromJson(authors, authorArray.getClass()));
        manga.setAuthor(authorArray);

        manga.setUpdateAt(cursor.getString(cursor.getColumnIndex(
                DownloadMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_UPDATE_AT)));

        manga.setReleased(cursor.getString(cursor.getColumnIndex(
                DownloadMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_RELEASE)));

        manga.setStatus(cursor.getString(cursor.getColumnIndex(
                DownloadMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_STATUS)));

        manga.setCreate_at(cursor.getString(cursor.getColumnIndex(
                DownloadMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_CREATE_AT)));

        manga.setDescribe(cursor.getString(cursor.getColumnIndex(
                DownloadMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_DESCRIBE)));
        try {
            manga.setView(Long.parseLong(cursor.getString(cursor.getColumnIndex(
                    DownloadMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_VIEW))));
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

        String ganre = cursor.getString(cursor.getColumnIndex(
                DownloadMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_GENRE));
        List<String> genreArray = new ArrayList<>();
        genreArray.addAll(new Gson().fromJson(authors, genreArray.getClass()));
        manga.setGenre(genreArray);
        return manga;
    }

    @Override
    public void addMangakDownload(Manga manga, List<String> chapterDownload) {
        if (manga == null) {
            return;
        }
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        if (isExitDownloadMangak(manga.getId())) {
            String arg = DownloadMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_ID + "=?";
            String[] args = { String.valueOf(manga.getId()) };
            sqLiteDatabase.delete(TABLE_NAME, arg, args);
        }
        sqLiteDatabase.insert(DownloadMangaPersistenceContract.RecentMangaEntry.TABLE_NAME, null,
                getContentValues(manga, chapterDownload));
        close();
    }

    private ContentValues getContentValues(Manga manga, List<String> chapterDownload) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DownloadMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_ID,
                manga.getId());
        contentValues.put(DownloadMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_NAME,
                manga.getName());
        contentValues.put(DownloadMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_AVATAR,
                manga.getAvatar());
        contentValues.put(
                DownloadMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_CHAP_DOWNLOAD,
                new Gson().toJson(chapterDownload));

        contentValues.put(DownloadMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_AUTHOR,
                new Gson().toJson(manga.getAuthor()));

        contentValues.put(DownloadMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_RELEASE,
                manga.getReleased());

        contentValues.put(DownloadMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_STATUS,
                manga.getStatus());

        contentValues.put(DownloadMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_CREATE_AT,
                manga.getCreate_at());

        contentValues.put(DownloadMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_DESCRIBE,
                manga.getDescribe());

        contentValues.put(DownloadMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_VIEW,
                manga.getView());

        contentValues.put(DownloadMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_GENRE,
                new Gson().toJson(manga.getGenre()));

        return contentValues;
    }

    @Override
    public boolean isExitDownloadMangak(int id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String arg = DownloadMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_ID + "=?";
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
    public Observable<Manga> getMangakById(int id) {
        Cursor cursor = null;
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            String arg = DownloadMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_ID + "=?";
            String[] args = { String.valueOf(id) };
            cursor = sqLiteDatabase.query(TABLE_NAME, null, arg, args, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                return Observable.just(getManga(cursor));
            }
            return Observable.just(null);
        } catch (Exception e) {
            e.printStackTrace();
            return Observable.error(new NullPointerException(e.getMessage()));
        } finally {
            close();
            if (cursor != null) cursor.close();
        }
    }
}
