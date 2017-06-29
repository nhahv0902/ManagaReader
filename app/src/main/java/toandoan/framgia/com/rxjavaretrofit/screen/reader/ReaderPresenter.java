package toandoan.framgia.com.rxjavaretrofit.screen.reader;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import toandoan.framgia.com.rxjavaretrofit.data.model.Chap;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.data.model.Setting;
import toandoan.framgia.com.rxjavaretrofit.data.source.MangaDataSource;
import toandoan.framgia.com.rxjavaretrofit.data.source.RecentMangaDataSource;
import toandoan.framgia.com.rxjavaretrofit.data.source.SettingDataSource;

/**
 * Listens to user actions from the UI ({@link ReaderActivity}), retrieves the data and updates
 * the UI as required.
 */
final class ReaderPresenter implements ReaderContract.Presenter {
    private static final String TAG = ReaderPresenter.class.getName();

    private final ReaderContract.ViewModel mViewModel;
    private Chap mChap;
    private Manga mManga;
    private CompositeSubscription mSubscription;
    private MangaDataSource mRepository;
    private RecentMangaDataSource mRecentMangaRepository;
    private SettingDataSource mSettingRepository;

    public ReaderPresenter(ReaderContract.ViewModel viewModel, Manga manga, Chap chap,
            MangaDataSource repository, RecentMangaDataSource recentMangaRepository,
            SettingDataSource settingRepository) {
        mViewModel = viewModel;
        mManga = manga;
        mChap = chap;
        mRepository = repository;
        mRecentMangaRepository = recentMangaRepository;
        mSettingRepository = settingRepository;
        mSubscription = new CompositeSubscription();
        getChap(mChap);
        getSetting();
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        mSubscription.clear();
    }

    @Override
    public void getChap(Chap chap) {
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
                        mViewModel.getChapterSuccess(chap);
                        addRecentMangaChap(chap);
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
    public void getSetting() {
        Subscription subscription = mSettingRepository.getSettings()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<Setting>() {
                    @Override
                    public void call(Setting setting) {
                        mViewModel.onGetSettingSuccess(setting);
                    }
                });
        mSubscription.add(subscription);
    }

    @Override
    public void saveSetting(Setting setting) {
        mSettingRepository.saveSetting(setting);
    }

    private void addRecentMangaChap(Chap chap) {
        mManga.setLastModifiedData(System.currentTimeMillis());
        mManga.setLastLocalChap(chap);
        mRecentMangaRepository.addRecentManga(mManga);
    }
}
