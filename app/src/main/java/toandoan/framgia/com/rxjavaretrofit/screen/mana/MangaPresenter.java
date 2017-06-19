package toandoan.framgia.com.rxjavaretrofit.screen.mana;

import android.util.Log;
import java.util.List;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.data.source.MangaDataSource;
import toandoan.framgia.com.rxjavaretrofit.utils.Constant;

/**
 * Listens to user actions from the UI ({@link MangaFragment}), retrieves the data and updates
 * the UI as required.
 */
final class MangaPresenter implements MangaContract.Presenter {
    private static final String TAG = MangaPresenter.class.getName();
    private static final int FIRST_PAGE = 1;

    private final MangaContract.ViewModel mViewModel;
    private MangaDataSource mMangaRepository;
    private int mPage = FIRST_PAGE;
    private CompositeSubscription mSubscription;

    public MangaPresenter(MangaContract.ViewModel viewModel, MangaDataSource mangaRepository) {
        mViewModel = viewModel;
        mMangaRepository = mangaRepository;
        mSubscription = new CompositeSubscription();
        getData(Constant.MangaType.POPULAR, mPage);
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        mSubscription.clear();
    }

    @Override
    public void getData(String option, int page) {
        Subscription subscription = mMangaRepository.getPopularManga(option, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Manga>>() {
                    @Override
                    public void call(List<Manga> mangas) {
                        mViewModel.onGetMangaSuccess(mangas);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mViewModel.onGetMangaFailed(throwable.getMessage().toString());
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        Log.d(TAG, "call: finnish");
                    }
                });

        mSubscription.add(subscription);
    }
}
