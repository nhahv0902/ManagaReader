package toandoan.framgia.com.rxjavaretrofit.data.model;

import android.os.Parcel;

/**
 * Created by nhahv0902 on 7/5/17.
 * <></>
 */

public class PercentDownload {
    private String chapter;
    private int percent;

    public PercentDownload() {
    }

    public PercentDownload(String chapter, int percent) {
        this.chapter = chapter;
        this.percent = percent;
    }

    protected PercentDownload(Parcel in) {
        chapter = in.readString();
        percent = in.readInt();
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
}
