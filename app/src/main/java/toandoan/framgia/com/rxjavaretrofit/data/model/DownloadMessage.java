package toandoan.framgia.com.rxjavaretrofit.data.model;

/**
 * Created by nhahv0902 on 7/2/2017.
 * <></>
 */

public class DownloadMessage {
    private int id;
    private String name;
    private String author;
    private String chapter;
    private int percent;

    public DownloadMessage() {
    }

    public DownloadMessage(int id, String name, String author, String chapter, int percent) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.chapter = chapter;
        this.percent = percent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    @Override
    public String toString() {
        return "DownloadMessage{"
                + "id="
                + id
                + ", name='"
                + name
                + '\''
                + ", author='"
                + author
                + '\''
                + ", chapter='"
                + chapter
                + '\''
                + ", percent="
                + percent
                + '}';
    }
}
