package toandoan.framgia.com.rxjavaretrofit.screen.download;

import java.util.List;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import toandoan.framgia.com.rxjavaretrofit.data.model.Chap;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.data.source.DownloadDataSource;
import toandoan.framgia.com.rxjavaretrofit.data.source.MangaDataSource;

/**
 * Listens to user actions from the UI ({@link DownloadActivity}), retrieves the data and updates
 * the UI as required.
 */
final class DownloadPresenter implements DownloadContract.Presenter {
    private static final String TAG = DownloadPresenter.class.getName();

    private final DownloadContract.ViewModel mViewModel;
    private final int mMangakId;
    private MangaDataSource mRepository;
    private DownloadDataSource mDownloadRepository;
    private CompositeSubscription mSubscription;

    public DownloadPresenter(DownloadContract.ViewModel viewModel, MangaDataSource repository,
            DownloadDataSource downloadRepository, int mangakId) {
        mViewModel = viewModel;
        mRepository = repository;
        mDownloadRepository = downloadRepository;
        mSubscription = new CompositeSubscription();
        mMangakId = mangakId;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        mSubscription.clear();
    }

    @Override
    public void getChap(Chap chap, final boolean isItemEnd) {
        Subscription subscription = mRepository.getChapById(chap.getId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mViewModel.showProgress();
                    }
                })
                .subscribe(new Action1<Chap>() {
                    @Override
                    public void call(Chap chap) {
                        mViewModel.getChapterSuccess(chap, isItemEnd);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mViewModel.hideProgress();
                        mViewModel.getChapterFailed(throwable.getMessage());
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        mViewModel.hideProgress();
                    }
                });
        mSubscription.add(subscription);
    }

    @Override
    public void getMangakById(int id) {
        Subscription subscription = mRepository.getMangaById(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mViewModel.showProgress();
                    }
                })
                .subscribe(new Action1<Manga>() {
                    @Override
                    public void call(Manga mangak) {
                        mViewModel.onGetMangakSuccess(mangak);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mViewModel.hideProgress();
                        mViewModel.getChapterFailed(throwable.getMessage());
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        mViewModel.hideProgress();
                    }
                });
        mSubscription.add(subscription);
    }

    @Override
    public void getMangakByIdOfLocal(int id) {
        Subscription subscription = mDownloadRepository.getMangakById(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mViewModel.showProgress();
                    }
                })
                .subscribe(new Action1<Manga>() {
                    @Override
                    public void call(Manga mangak) {
                        mViewModel.onGetMangakOfLocalSuccess(mangak);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mViewModel.hideProgress();
                        mViewModel.getChapterFailed(throwable.getMessage());
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        mViewModel.hideProgress();
                    }
                });
        mSubscription.add(subscription);
    }

    @Override
    public void addMangakDownload(Manga manga, List<Chap> chapters) {
        mDownloadRepository.addMangakDownload(manga, chapters);
    }

    @Override
    public void addChapterToDB(Chap chap) {
        mDownloadRepository.addChapter(chap);
    }
}
