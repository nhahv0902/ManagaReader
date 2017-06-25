package toandoan.framgia.com.rxjavaretrofit.data.source.local.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by framgia on 18/04/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MangaReader.db";

    private static final String TEXT_TYPE = " TEXT";

    private static final String BOOLEAN_TYPE = " INTEGER";

    private static final String COMMA_SEP = ",";

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

    private static final String SQL_DELETE_RECENT_MANGA =
            "DROP TABLE IF EXISTS " + RecentMangaPersistenceContract.RecentMangaEntry.TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_RECENT_MANGA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_RECENT_MANGA);
        sqLiteDatabase.execSQL(SQL_CREATE_RECENT_MANGA);
    }
}
