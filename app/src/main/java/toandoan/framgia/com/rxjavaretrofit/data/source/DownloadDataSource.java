package toandoan.framgia.com.rxjavaretrofit.data.source;

import java.util.List;
import rx.Observable;
import toandoan.framgia.com.rxjavaretrofit.data.model.Chap;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;

/**
 * Created by nhahv0902 on 7/2/2017.
 * <></>
 */

public interface DownloadDataSource {
    Observable<List<Manga>> getAllMangakDownload();

    void addMangakDownload(Manga manga, List<Chap> chapterDownload);

    Observable<Manga> getMangakById(int id);

    boolean isExitDownloadMangak(int id);

    void addChapter(Chap chap);

    Observable<Chap> getChapterById(int id);

    void deleteAllMangakDownloaded();

    void deleteMangak(int id);
}
