package toandoan.framgia.com.rxjavaretrofit.data.model;

import android.support.annotation.IntDef;
import java.util.ArrayList;
import java.util.List;

import static toandoan.framgia.com.rxjavaretrofit.data.model.MessageDownloading.StatusDownload.*;

/**
 * Created by nhahv0902 on 7/5/17.
 * <></>
 */

public class MessageDownloading {
    private String mangakName;
    private String avatar;
    private int percent;
    private List<String> chapter = new ArrayList<>();
    @MessageDownloading.StatusDownload
    private int status;

    public MessageDownloading() {
    }

    public MessageDownloading(String mangakName, String avatar, int percent, List<String> chapter,
            int status) {
        this.mangakName = mangakName;
        this.avatar = avatar;
        this.percent = percent;
        this.chapter = chapter;
        this.status = status;
    }

    public String getMangakName() {
        return mangakName;
    }

    public void setMangakName(String mangakName) {
        this.mangakName = mangakName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<String> getChapter() {
        return chapter;
    }

    public void setChapter(List<String> chapter) {
        this.chapter = chapter;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public int getStatus() {
        return status;
    }

    public String getStatusString() {
        switch (status) {
            case DOWNLOADING:
                return "Downloading";
            case WAITING:
                return "Waiting download";
            default:
                return "Waiting download";
        }
    }

    public boolean getStatusBoolean() {
        switch (status) {
            case DOWNLOADING:
                return true;
            case WAITING:
                return false;
            default:
                return false;
        }
    }

    public String chapterString() {
        StringBuilder build = new StringBuilder();
        int size = chapter.size();
        for (int i = 0; i < size; i++) {
            if (i != size - 1) {
                build.append(chapter.get(i)).append(", ");
            } else {
                build.append(chapter.get(i));
            }
        }
        return build.toString();
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @IntDef({ DOWNLOADING, WAITING })
    public @interface StatusDownload {
        int DOWNLOADING = 1;
        int WAITING = 2;
    }
}
