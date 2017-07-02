package toandoan.framgia.com.rxjavaretrofit.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import toandoan.framgia.com.rxjavaretrofit.data.model.Chap;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.data.source.DownloadDataSource;
import toandoan.framgia.com.rxjavaretrofit.data.source.local.sqlite.ChapterMangaPersistenceContract;
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
        Type type = new TypeToken<ArrayList<Chap>>() {
        }.getType();
        ArrayList<Chap> finalOutputString = new Gson().fromJson(chapters, type);
        manga.setChaps(finalOutputString);

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

        String genre = cursor.getString(cursor.getColumnIndex(
                DownloadMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_GENRE));
        List<String> genreArray = new ArrayList<>();
        genreArray.addAll(new Gson().fromJson(genre, genreArray.getClass()));
        manga.setGenre(genreArray);
        return manga;
    }

    @Override
    public void addMangakDownload(Manga manga, List<Chap> chapterDownload) {
        if (manga == null) {
            return;
        }
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        if (isExitDownloadMangak(manga.getId())) {
            String arg = DownloadMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_ID + "=?";
            String[] args = { String.valueOf(manga.getId()) };
            sqLiteDatabase.update(TABLE_NAME, getContentValues(manga, chapterDownload), arg, args);
        } else {
            sqLiteDatabase.insert(DownloadMangaPersistenceContract.RecentMangaEntry.TABLE_NAME,
                    null, getContentValues(manga, chapterDownload));
        }
        close();
    }

    private ContentValues getContentValues(Manga manga, List<Chap> chapterDownload) {
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

    @Override
    public void addChapter(Chap chap) {
        if (chap == null) {
            return;
        }
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        if (isExitChapter(chap.getId())) {
            String arg = ChapterMangaPersistenceContract.RecentMangaEntry.COLUMN_ID + "=?";
            String[] args = { String.valueOf(chap.getId()) };
            sqLiteDatabase.update(TABLE_NAME, getContentValuesChapter(chap), arg, args);
        } else {
            sqLiteDatabase.insert(ChapterMangaPersistenceContract.RecentMangaEntry.TABLE_NAME, null,
                    getContentValuesChapter(chap));
        }
        close();
    }

    private ContentValues getContentValuesChapter(Chap chap) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ChapterMangaPersistenceContract.RecentMangaEntry.COLUMN_ID, chap.getId());
        contentValues.put(ChapterMangaPersistenceContract.RecentMangaEntry.COLUMN_CHAPTER,
                chap.getChap());
        contentValues.put(ChapterMangaPersistenceContract.RecentMangaEntry.COLUMN_NAME,
                chap.getName());
        contentValues.put(ChapterMangaPersistenceContract.RecentMangaEntry.COLUMN_CONTENT,
                new Gson().toJson(chap.getContent()));
        return contentValues;
    }

    private boolean isExitChapter(String id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String arg = ChapterMangaPersistenceContract.RecentMangaEntry.COLUMN_ID + "=?";
        String[] args = { String.valueOf(id) };
        Cursor cursor =
                sqLiteDatabase.query(ChapterMangaPersistenceContract.RecentMangaEntry.TABLE_NAME,
                        null, arg, args, null, null, null);
        if (cursor != null && cursor.moveToNext()) {
            cursor.close();
            return true;
        }
        if (cursor != null) cursor.close();
        return false;
    }

    @Override
    public Observable<Chap> getChapterById(int id) {
        Cursor cursor = null;
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            String arg = ChapterMangaPersistenceContract.RecentMangaEntry.COLUMN_ID + "=?";
            String[] args = { String.valueOf(id) };
            cursor = sqLiteDatabase.query(
                    ChapterMangaPersistenceContract.RecentMangaEntry.TABLE_NAME, null, arg, args,
                    null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                return Observable.just(getChapter(cursor));
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

    private Chap getChapter(Cursor cursor) {
        if (cursor == null) return null;
        Chap chapter = new Chap();
        chapter.setId(String.valueOf(cursor.getInt(cursor.getColumnIndex(
                ChapterMangaPersistenceContract.RecentMangaEntry.COLUMN_ID))));

        chapter.setName(cursor.getString(cursor.getColumnIndex(
                ChapterMangaPersistenceContract.RecentMangaEntry.COLUMN_NAME)));
        chapter.setChap(cursor.getString(cursor.getColumnIndex(
                ChapterMangaPersistenceContract.RecentMangaEntry.COLUMN_CHAPTER)));
        String contents = cursor.getString(cursor.getColumnIndex(
                ChapterMangaPersistenceContract.RecentMangaEntry.COLUMN_CONTENT));
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        ArrayList<String> array = new Gson().fromJson(contents, type);
        chapter.setContent(array);
        return chapter;
    }
}
