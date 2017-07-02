package toandoan.framgia.com.rxjavaretrofit.data.source.local.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by framgia on 18/04/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "MangaReader.db";

    private static final String TEXT_TYPE = " TEXT";

    private static final String BOOLEAN_TYPE = " INTEGER";

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_FAVORITE_MANGA = "CREATE TABLE "
            + FavoriteMangaPersistenceContract.RecentMangaEntry.TABLE_NAME
            + " ("
            + FavoriteMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_ID
            + TEXT_TYPE
            + " PRIMARY KEY,"
            + FavoriteMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_NAME
            + TEXT_TYPE
            + COMMA_SEP
            + FavoriteMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_AVATAR
            + TEXT_TYPE
            + " )";
    private static final String SQL_CREATE_DOWNLOAD_MANGA = "CREATE TABLE "
            + DownloadMangaPersistenceContract.RecentMangaEntry.TABLE_NAME
            + " ("
            + DownloadMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_ID
            + TEXT_TYPE
            + " PRIMARY KEY,"
            + DownloadMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_NAME
            + TEXT_TYPE
            + COMMA_SEP
            + DownloadMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_AVATAR
            + TEXT_TYPE
            + COMMA_SEP
            + DownloadMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_CHAP_DOWNLOAD
            + TEXT_TYPE
            + COMMA_SEP
            + DownloadMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_AUTHOR
            + TEXT_TYPE
            + COMMA_SEP
            + DownloadMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_UPDATE_AT
            + TEXT_TYPE
            + COMMA_SEP
            + DownloadMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_RELEASE
            + TEXT_TYPE
            + COMMA_SEP
            + DownloadMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_STATUS
            + TEXT_TYPE
            + COMMA_SEP
            + DownloadMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_CREATE_AT
            + TEXT_TYPE
            + COMMA_SEP
            + DownloadMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_DESCRIBE
            + TEXT_TYPE
            + COMMA_SEP
            + DownloadMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_VIEW
            + TEXT_TYPE
            + COMMA_SEP
            + DownloadMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_GENRE
            + TEXT_TYPE
            + " )";
    private static final String SQL_CREATE_RECENT_MANGA = "CREATE TABLE "
            + RecentMangaPersistenceContract.RecentMangaEntry.TABLE_NAME
            + " ("
            + RecentMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_ID
            + TEXT_TYPE
            + " PRIMARY KEY,"
            + RecentMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_NAME
            + TEXT_TYPE
            + COMMA_SEP
            + RecentMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_AVATAR
            + TEXT_TYPE
            + COMMA_SEP
            + RecentMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_MODIFIED_DATE
            + TEXT_TYPE
            + COMMA_SEP
            + RecentMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_LASTED_CHAP_NAME
            + TEXT_TYPE
            + COMMA_SEP
            + RecentMangaPersistenceContract.RecentMangaEntry.COLUMN_MANGA_LASTED_CHAP_ID
            + TEXT_TYPE
            + " )";
    private static final String SQL_CREATE_CHAPTER = "CREATE TABLE "
            + ChapterMangaPersistenceContract.RecentMangaEntry.TABLE_NAME
            + " ("
            + ChapterMangaPersistenceContract.RecentMangaEntry.COLUMN_ID
            + TEXT_TYPE
            + " PRIMARY KEY,"
            + ChapterMangaPersistenceContract.RecentMangaEntry.COLUMN_CHAPTER
            + TEXT_TYPE
            + COMMA_SEP
            + ChapterMangaPersistenceContract.RecentMangaEntry.COLUMN_NAME
            + TEXT_TYPE
            + COMMA_SEP
            + ChapterMangaPersistenceContract.RecentMangaEntry.COLUMN_CONTENT
            + TEXT_TYPE
            + " )";

    private static final String SQL_DELETE_RECENT_MANGA =
            "DROP TABLE IF EXISTS " + RecentMangaPersistenceContract.RecentMangaEntry.TABLE_NAME;
    private static final String SQL_DELETE_FAVORITE_MANGA =
            "DROP TABLE IF EXISTS " + FavoriteMangaPersistenceContract.RecentMangaEntry.TABLE_NAME;
    private static final String SQL_DELETE_DOWNLOAD_MANGA =
            "DROP TABLE IF EXISTS " + DownloadMangaPersistenceContract.RecentMangaEntry.TABLE_NAME;
    private static final String SQL_DELETE_CHAPTER =
            "DROP TABLE IF EXISTS " + ChapterMangaPersistenceContract.RecentMangaEntry.TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_RECENT_MANGA);
        sqLiteDatabase.execSQL(SQL_CREATE_FAVORITE_MANGA);
        sqLiteDatabase.execSQL(SQL_CREATE_DOWNLOAD_MANGA);
        sqLiteDatabase.execSQL(SQL_CREATE_CHAPTER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_RECENT_MANGA);
        sqLiteDatabase.execSQL(SQL_DELETE_FAVORITE_MANGA);
        sqLiteDatabase.execSQL(SQL_DELETE_DOWNLOAD_MANGA);
        sqLiteDatabase.execSQL(SQL_DELETE_CHAPTER);

        sqLiteDatabase.execSQL(SQL_CREATE_RECENT_MANGA);
        sqLiteDatabase.execSQL(SQL_CREATE_FAVORITE_MANGA);
        sqLiteDatabase.execSQL(SQL_CREATE_DOWNLOAD_MANGA);
        sqLiteDatabase.execSQL(SQL_CREATE_CHAPTER);
    }
}
