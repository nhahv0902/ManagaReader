package toandoan.framgia.com.rxjavaretrofit.screen.download;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import toandoan.framgia.com.rxjavaretrofit.data.model.Chap;
import toandoan.framgia.com.rxjavaretrofit.data.source.MangaDataSource;

/**
 * Listens to user actions from the UI ({@link DownloadActivity}), retrieves the data and updates
 * the UI as required.
 */
final class DownloadPresenter implements DownloadContract.Presenter {
    private static final String TAG = DownloadPresenter.class.getName();

    private final DownloadContract.ViewModel mViewModel;
    private MangaDataSource mRepository;
    private CompositeSubscription mSubscription;

    public DownloadPresenter(DownloadContract.ViewModel viewModel, MangaDataSource repository) {
        mViewModel = viewModel;
        mRepository = repository;
        mSubscription = new CompositeSubscription();
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
}
