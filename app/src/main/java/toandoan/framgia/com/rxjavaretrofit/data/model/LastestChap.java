package toandoan.framgia.com.rxjavaretrofit.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by toand on 6/19/2017.
 */

public class LastestChap {
    @SerializedName("chap")
    @Expose
    private int chap;
    @SerializedName("id")
    @Expose
    private int id;

    public int getChap() {
        return chap;
    }

    public void setChap(int chap) {
        this.chap = chap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
