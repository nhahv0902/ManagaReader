package toandoan.framgia.com.rxjavaretrofit.data.source.local.sqlite;

import android.provider.BaseColumns;

/**
 * Created by toand on 6/25/2017.
 */

public class RecentMangaPersistenceContract {
    private RecentMangaPersistenceContract() {

    }

    public static abstract class RecentMangaEntry implements BaseColumns {
        public static final String TABLE_NAME = "recent_manga_table";
        public static final String COLUMN_MANGA_NAME = "column_manga_name";
        public static final String COLUMN_MANGA_ID = "column_manga_id";
        public static final String COLUMN_MANGA_AVATAR = "column_manga_avatar";
        public static final String COLUMN_MANGA_MODIFIED_DATE = "column_manga_modified_date";
        public static final String COLUMN_MANGA_LASTED_CHAP_ID = "column_manga_lasted_chap_id";
        public static final String COLUMN_MANGA_LASTED_CHAP_NAME = "column_manga_lasted_chap_name";
    }
}
