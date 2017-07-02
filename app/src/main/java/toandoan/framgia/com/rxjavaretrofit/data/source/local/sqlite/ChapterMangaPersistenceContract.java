package toandoan.framgia.com.rxjavaretrofit.data.source.local.sqlite;

import android.provider.BaseColumns;

/**
 * Created by toand on 6/25/2017.
 */

public class ChapterMangaPersistenceContract {
    public static abstract class RecentMangaEntry implements BaseColumns {
        public static final String TABLE_NAME = "table_chapter";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME= "name";
        public static final String COLUMN_CHAPTER = "chapter";
        public static final String COLUMN_CONTENT = "content";
    }
}
