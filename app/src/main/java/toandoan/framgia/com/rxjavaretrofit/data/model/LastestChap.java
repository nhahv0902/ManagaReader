package toandoan.framgia.com.rxjavaretrofit.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * Created by toand on 6/19/2017.
 */

public class LastestChap implements Serializable {
    @SerializedName("chap")
    @Expose
    private String chap;
    @SerializedName("id")
    @Expose
    private int id;

    public String getChap() {
        return chap;
    }

    public void setChap(String chap) {
        this.chap = chap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
