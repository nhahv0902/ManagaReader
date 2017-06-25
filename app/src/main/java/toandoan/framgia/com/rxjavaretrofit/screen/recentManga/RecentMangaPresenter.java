package toandoan.framgia.com.rxjavaretrofit.screen.recentManga;

import java.util.List;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.data.source.RecentMangaDataSource;

/**
 * Listens to user actions from the UI ({@link RecentMangaFragment}), retrieves the data and updates
 * the UI as required.
 */
final class RecentMangaPresenter implements RecentMangaContract.Presenter {
    private static final String TAG = RecentMangaPresenter.class.getName();

    private final RecentMangaContract.ViewModel mViewModel;
    private RecentMangaDataSource mRepository;
    private CompositeSubscription mSubscription;

    public RecentMangaPresenter(RecentMangaContract.ViewModel viewModel,
            RecentMangaDataSource repository) {
        mViewModel = viewModel;
        mRepository = repository;
        mSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {
        getAllRecentMangas();
    }

    @Override
    public void onStop() {
        mSubscription.clear();
    }

    @Override
    public void getAllRecentMangas() {
        Subscription subscription = mRepository.getAllRecentManga()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<List<Manga>>() {
                    @Override
                    public void call(List<Manga> mangas) {
                        mViewModel.onGetRecentMangaSuccess(mangas);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                    }
                });
        mSubscription.add(subscription);
    }

    @Override
    public void deleteManga(Manga manga) {
        mRepository.removeRecentMangaById(manga.getId());
    }

    @Override
    public void deleteAllManga() {
        mRepository.removeAllRecentManga();
    }
}
