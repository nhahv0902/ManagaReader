package toandoan.framgia.com.rxjavaretrofit.screen.mangaDetail;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.data.source.DownloadDataSource;
import toandoan.framgia.com.rxjavaretrofit.data.source.FavoriteDataSource;
import toandoan.framgia.com.rxjavaretrofit.data.source.MangaDataSource;

/**
 * Listens to user actions from the UI ({@link MangaDetailActivity}), retrieves the data and updates
 * the UI as required.
 */
final class MangaDetailPresenter implements MangaDetailContract.Presenter {
    private static final String TAG = MangaDetailPresenter.class.getName();

    private final MangaDetailContract.ViewModel mViewModel;
    private MangaDataSource mMangaRepository;
    private CompositeSubscription mSubscription;
    private FavoriteDataSource mFavoriteRepository;
    private DownloadDataSource mDownloadRepository;

    public MangaDetailPresenter(MangaDetailContract.ViewModel viewModel,
            MangaDataSource mangaRepository, FavoriteDataSource favoriteRepository,
            DownloadDataSource downloadRepository) {
        mViewModel = viewModel;
        mMangaRepository = mangaRepository;
        mFavoriteRepository = favoriteRepository;
        mDownloadRepository = downloadRepository;
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
    public void getMangaDetail(final int id) {
        Subscription subscription = mMangaRepository.getMangaById(id)
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
                    public void call(Manga manga) {
                        manga.setFavorite(mFavoriteRepository.isExitFavoriteManga(id));
                        mViewModel.onGetMangaSuccess(manga);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mViewModel.onGetMangaFailed(throwable.getMessage());
                        mViewModel.hideProgress();
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
    public void getMangakDownloadById(int id) {
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
                    public void call(Manga manga) {
                        mViewModel.onGetMangaSuccess(manga);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mViewModel.onGetMangaFailed(throwable.getMessage());
                        mViewModel.hideProgress();
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
    public void onFavoriteClick(Manga manga) {
        manga.setFavorite(!manga.isFavorite());
        if (manga.isFavorite()) {
            mFavoriteRepository.addFavoriteManga(manga);
        } else {
            mFavoriteRepository.removeFavoriteMangaById(manga.getId());
        }
    }
}
