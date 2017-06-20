package toandoan.framgia.com.rxjavaretrofit.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * Created by framgia on 20/06/2017.
 */

public class Chap implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("vol")
    @Expose
    private String vol;
    @SerializedName("chap")
    @Expose
    private Integer chap;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVol() {
        return vol;
    }

    public void setVol(String vol) {
        this.vol = vol;
    }

    public Integer getChap() {
        return chap;
    }

    public void setChap(Integer chap) {
        this.chap = chap;
    }
}
