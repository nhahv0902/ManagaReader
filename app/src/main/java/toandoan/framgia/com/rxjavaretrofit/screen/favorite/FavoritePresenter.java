package toandoan.framgia.com.rxjavaretrofit.screen.favorite;

import java.util.List;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.data.source.FavoriteDataSource;

/**
 * Listens to user actions from the UI ({@link FavoriteFragment}), retrieves the data and updates
 * the UI as required.
 */
final class FavoritePresenter implements FavoriteContract.Presenter {
    private static final String TAG = FavoritePresenter.class.getName();
    private FavoriteDataSource mRepository;

    private final FavoriteContract.ViewModel mViewModel;
    private CompositeSubscription mSubscription;

    public FavoritePresenter(FavoriteContract.ViewModel viewModel, FavoriteDataSource repository) {
        mViewModel = viewModel;
        mRepository = repository;
        mSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {
        getFavoritesManga();
    }

    @Override
    public void onStop() {
        mSubscription.clear();
    }

    @Override
    public void getFavoritesManga() {
        Subscription subscription = mRepository.getAllFavoriteManga()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Manga>>() {
                    @Override
                    public void call(List<Manga> mangas) {
                        mViewModel.onGetFavoriteSuccess(mangas);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
        mSubscription.add(subscription);
    }

    @Override
    public void deleteAllFavorite() {
        mRepository.removeAllFavoriteManga();
    }

    @Override
    public void removeFavorite(int mangaId) {
        mRepository.removeFavoriteMangaById(mangaId);
    }
}
