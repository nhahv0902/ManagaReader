package toandoan.framgia.com.rxjavaretrofit.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.BR;

/**
 * Created by toand on 6/19/2017.
 */

public class Manga extends BaseObservable {
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
    @SerializedName("genre")
    @Expose
    private List<String> genre = null;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("view")
    @Expose
    private long view;
    @SerializedName("lastest_chap")
    @Expose
    private LastestChap lastestChap;

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

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
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
}
