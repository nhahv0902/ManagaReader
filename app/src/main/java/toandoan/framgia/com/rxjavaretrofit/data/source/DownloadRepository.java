package toandoan.framgia.com.rxjavaretrofit.data.source;

import android.content.Context;
import java.util.List;
import rx.Observable;
import toandoan.framgia.com.rxjavaretrofit.data.model.Chap;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.data.source.local.DownloadLocalDataSource;

/**
 * Created by toand on 6/29/2017.
 */

public class DownloadRepository implements DownloadDataSource {
    private DownloadLocalDataSource mLocalDataSource;

    public DownloadRepository(Context context) {
        mLocalDataSource = new DownloadLocalDataSource(context);
    }
    @Override
    public Observable<List<Manga>> getAllMangakDownload() {
        return mLocalDataSource.getAllMangakDownload();
    }

    @Override
    public void addMangakDownload(Manga manga, List<Chap> chapterDownload) {
        mLocalDataSource.addMangakDownload(manga, chapterDownload);
    }

    @Override
    public boolean isExitDownloadMangak(int id) {
        return mLocalDataSource.isExitDownloadMangak(id);
    }

    @Override
    public void addChapter(Chap chap) {
        mLocalDataSource.addChapter(chap);
    }

    @Override
    public Observable<Chap> getChapterById(int id) {
        return mLocalDataSource.getChapterById(id);
    }

    @Override
    public Observable<Manga> getMangakById(int id) {
        return mLocalDataSource.getMangakById(id);
    }

    @Override
    public void deleteAllMangakDownloaded() {
        mLocalDataSource.deleteAllMangakDownloaded();
    }

    @Override
    public void deleteMangak(int id) {
        mLocalDataSource.deleteMangak(id);
    }
}
