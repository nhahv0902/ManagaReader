package toandoan.framgia.com.rxjavaretrofit.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.IntDef;
import toandoan.framgia.com.rxjavaretrofit.BR;

import static toandoan.framgia.com.rxjavaretrofit.data.model.Setting.ReadingDirection.LEFT_TO_RIGHT;
import static toandoan.framgia.com.rxjavaretrofit.data.model.Setting.ReadingDirection.RIGHT_TO_LEFT;
import static toandoan.framgia.com.rxjavaretrofit.data.model.Setting.ReadingMode.HORIZONTAL;
import static toandoan.framgia.com.rxjavaretrofit.data.model.Setting.ReadingMode.VERTICAL;

/**
 * Created by toand on 6/25/2017.
 */

public class Setting extends BaseObservable {
    private float mBrightNess;
    @ReadingMode
    private int mReadingMode;
    @ReadingDirection
    private int mReadingDirection;

    public Setting() {
        mBrightNess = 0.5f;
        mReadingMode = VERTICAL;
        mReadingDirection = LEFT_TO_RIGHT;
    }

    @Bindable
    public float getBrightNess() {
        return mBrightNess;
    }

    public void setBrightNess(float brightNess) {
        mBrightNess = brightNess;
        notifyPropertyChanged(BR.brightNess);
    }

    public int getReadingMode() {
        return mReadingMode;
    }

    public void setReadingMode(int readingMode) {
        mReadingMode = readingMode;
    }

    public int getReadingDirection() {
        return mReadingDirection;
    }

    public void setReadingDirection(int readingDirection) {
        mReadingDirection = readingDirection;
    }

    @IntDef({ VERTICAL, HORIZONTAL })
    @interface ReadingMode {
        int VERTICAL = 0;
        int HORIZONTAL = 1;
    }

    @IntDef({ LEFT_TO_RIGHT, RIGHT_TO_LEFT })
    @interface ReadingDirection {
        int LEFT_TO_RIGHT = 0;
        int RIGHT_TO_LEFT = 1;
    }
}
