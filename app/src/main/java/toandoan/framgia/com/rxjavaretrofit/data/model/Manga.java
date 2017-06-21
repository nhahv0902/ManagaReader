package toandoan.framgia.com.rxjavaretrofit.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.BR;

/**
 * Created by toand on 6/19/2017.
 */

public class Manga extends BaseObservable implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("author")
    @Expose
    private List<String> author;
    @SerializedName("artist")
    @Expose
    private List<Object> artist;
    @SerializedName("update_at")
    @Expose
    private String updateAt;
    @SerializedName("released")
    @Expose
    private String released;
    @SerializedName("avatar")
    @Expose
    private String avatar;

    @SerializedName("other_name")
    @Expose
    private List<Object> otherName = null;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("create_at")
    @Expose
    private String create_at;
    @SerializedName("describe")
    @Expose
    private String describe;
    @SerializedName("view")
    @Expose
    private long view;
    @SerializedName("lastest_chap")
    @Expose
    private LastestChap lastestChap;
    @SerializedName("orther_name")
    @Expose
    private List<String> ortherName;
    @SerializedName("genre")
    @Expose
    private List<String> genre;
    @SerializedName("chaps")
    @Expose
    private List<Chap> chaps;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public List<Object> getArtist() {
        return artist;
    }

    public void setArtist(List<Object> artist) {
        this.artist = artist;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    @Bindable
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
        notifyPropertyChanged(BR.avatar);
    }

    public List<Object> getOtherName() {
        return otherName;
    }

    public void setOtherName(List<Object> otherName) {
        this.otherName = otherName;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public long getView() {
        return view;
    }

    public void setView(long view) {
        this.view = view;
    }

    public LastestChap getLastestChap() {
        return lastestChap;
    }

    public void setLastestChap(LastestChap lastestChap) {
        this.lastestChap = lastestChap;
    }

    public List<String> getOrtherName() {
        return ortherName;
    }

    public void setOrtherName(List<String> ortherName) {
        this.ortherName = ortherName;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public List<Chap> getChaps() {
        return chaps;
    }

    public void setChaps(List<Chap> chaps) {
        this.chaps = chaps;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getAuthorStr() {
        String str = "";
        if (author != null) {
            for (String s : author) {
                str += s + ", ";
            }
        }
        if (str.length() > 0) {
            str = str.substring(0, str.length() - 2);
        }
        return str;
    }
}
