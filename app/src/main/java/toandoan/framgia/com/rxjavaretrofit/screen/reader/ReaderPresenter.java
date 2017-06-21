package toandoan.framgia.com.rxjavaretrofit.screen.reader;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BaseTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import java.io.File;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import toandoan.framgia.com.rxjavaretrofit.AppApplication;
import toandoan.framgia.com.rxjavaretrofit.data.model.Chap;
import toandoan.framgia.com.rxjavaretrofit.data.source.MangaDataSource;

/**
 * Listens to user actions from the UI ({@link ReaderActivity}), retrieves the data and updates
 * the UI as required.
 */
final class ReaderPresenter implements ReaderContract.Presenter {
    private static final String TAG = ReaderPresenter.class.getName();

    private final ReaderContract.ViewModel mViewModel;
    private Chap mChap;
    private CompositeSubscription mSubscription;
    private MangaDataSource mRepository;

    public ReaderPresenter(ReaderContract.ViewModel viewModel, Chap chap,
            MangaDataSource repository) {
        mViewModel = viewModel;
        mChap = chap;
        mRepository = repository;
        mSubscription = new CompositeSubscription();
        getChap(mChap.getId());
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        mSubscription.clear();
    }

    @Override
    public void getChap(String chapId) {
        Subscription subscription = mRepository.getChapById(chapId)
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
                        downloadChaper(chap);
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
                    }
                });
        mSubscription.add(subscription);
    }

    int count;
    @Override
    public void downloadChaper(final Chap chap) {
       count = chap.getContent().size();

        for (String url : chap.getContent()) {
            Glide.with(AppApplication.getContext()).load(url)
                    .downloadOnly(new BaseTarget<File>() {
                @Override
                public void onResourceReady(File resource,
                        GlideAnimation<? super File> glideAnimation) {
                    count--;
                    if (count == 0) {
                        mViewModel.getChapterSuccess(chap);
                        mViewModel.hideProgress();
                    }
                }

                @Override
                public void getSize(SizeReadyCallback cb) {

                }
            });
        }
    }
}
