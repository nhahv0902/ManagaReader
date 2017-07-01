package toandoan.framgia.com.rxjavaretrofit.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.android.databinding.library.baseAdapters.BR;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

/**
 * Created by framgia on 20/06/2017.
 */

public class Chap extends BaseObservable implements Serializable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("vol")
    @Expose
    private String vol;
    @SerializedName("chap")
    @Expose
    private String chap;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("update_at")
    @Expose
    private String updateAt;
    @SerializedName("create_at")
    @Expose
    private String createAt;
    @SerializedName("content")
    @Expose
    private List<String> content;
    private boolean isChecked;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVol() {
        return vol;
    }

    public void setVol(String vol) {
        this.vol = vol;
    }

    public String getChap() {
        return chap;
    }

    public void setChap(String chap) {
        this.chap = chap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {

        this.content = content;
    }

    @Bindable
    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
        notifyPropertyChanged(BR.checked);
    }
}
