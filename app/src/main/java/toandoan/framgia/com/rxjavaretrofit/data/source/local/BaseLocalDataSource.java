package toandoan.framgia.com.rxjavaretrofit.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import toandoan.framgia.com.rxjavaretrofit.data.source.local.sqlite.DatabaseHelper;

/**
 * Created by framgia on 18/04/2017.
 */

public abstract class BaseLocalDataSource extends DatabaseHelper {

    public BaseLocalDataSource(Context context) {
        super(context);
    }

}
