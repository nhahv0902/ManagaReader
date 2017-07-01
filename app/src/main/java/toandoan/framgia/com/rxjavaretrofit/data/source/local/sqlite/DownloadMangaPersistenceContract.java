package toandoan.framgia.com.rxjavaretrofit.data.source.local.sqlite;

import android.provider.BaseColumns;

/**
 * Created by toand on 6/25/2017.
 */

public class DownloadMangaPersistenceContract {
    private DownloadMangaPersistenceContract() {

    }

    public static abstract class RecentMangaEntry implements BaseColumns {
        public static final String TABLE_NAME = "download_manga_table";
        public static final String COLUMN_MANGA_CHAP_DOWNLOAD = "column_manga_chap_download";
        public static final String COLUMN_MANGA_ID = "column_manga_id";
        public static final String COLUMN_MANGA_NAME = "column_manga_name";
        public static final String COLUMN_MANGA_AUTHOR = "author";
        public static final String COLUMN_MANGA_UPDATE_AT = "update_at";
        public static final String COLUMN_MANGA_RELEASE = "release";
        public static final String COLUMN_MANGA_AVATAR = "column_manga_avatar";
        public static final String COLUMN_MANGA_STATUS = "status";
        public static final String COLUMN_MANGA_CREATE_AT = "create_at";
        public static final String COLUMN_MANGA_DESCRIBE = "describe";
        public static final String COLUMN_MANGA_VIEW = "view";
        public static final String COLUMN_MANGA_GENRE = "genre";
    }
}
