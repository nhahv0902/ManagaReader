package toandoan.framgia.com.rxjavaretrofit.screen.mana;

import java.util.List;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.data.source.MangaDataSource;
import toandoan.framgia.com.rxjavaretrofit.data.source.SourcesDataSource;

import static toandoan.framgia.com.rxjavaretrofit.screen.mana.MangaFragment.TypeFragmentManga
        .GENRES;
import static toandoan.framgia.com.rxjavaretrofit.screen.mana.MangaFragment.TypeFragmentManga.OTHER;

/**
 * Listens to user actions from the UI ({@link MangaFragment}), retrieves the data and updates
 * the UI as required.
 */
final class MangaPresenter implements MangaContract.Presenter {
    private static final String TAG = MangaPresenter.class.getName();
    private static final int FIRST_PAGE = 1;

    private final MangaContract.ViewModel mViewModel;
    private MangaDataSource mMangaRepository;
    private SourcesDataSource mSourcesRepository;
    private int mPage = FIRST_PAGE;
    private CompositeSubscription mSubscription;
    private int mTypeFragment;
    private String mType;
    private List<String> mGenres;

    public MangaPresenter(MangaContract.ViewModel viewModel, int typeFragment, String type,
            List<String> genres, MangaDataSource mangaRepository,
            SourcesDataSource sourcesRepository) {
        mViewModel = viewModel;
        mTypeFragment = typeFragment;
        mGenres = genres;
        mType = type;
        mMangaRepository = mangaRepository;
        mSubscription = new CompositeSubscription();
        mSourcesRepository = sourcesRepository;
        getData();
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        mSubscription.clear();
    }

    @Override
    public void getData() {
        String sourceSlug = mSourcesRepository.getCurrentSourceCode();
        if (sourceSlug == null) return;
        Subscription subscription = null;
        switch (mTypeFragment) {
            case GENRES:
                subscription = mMangaRepository.getMangaByGenres(sourceSlug, mGenres, mPage)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(new Action0() {
                            @Override
                            public void call() {
                                mViewModel.showProgressBar();
                            }
                        })
                        .subscribe(new Action1<List<Manga>>() {
                            @Override
                            public void call(List<Manga> mangas) {
                                mViewModel.hideProgressBar();
                                mViewModel.onGetMangaSuccess(mangas);
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                mPage--;
                                mViewModel.hideProgressBar();
                                mViewModel.onGetMangaFailed(throwable.getMessage().toString());
                            }
                        }, new Action0() {
                            @Override
                            public void call() {
                                mViewModel.hideProgressBar();
                            }
                        });
                break;
            default:
            case OTHER:
                subscription = mMangaRepository.getPopularManga(sourceSlug, mType, mPage)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(new Action0() {
                            @Override
                            public void call() {
                                mViewModel.showProgressBar();
                            }
                        })
                        .subscribe(new Action1<List<Manga>>() {
                            @Override
                            public void call(List<Manga> mangas) {
                                mViewModel.hideProgressBar();
                                mViewModel.onGetMangaSuccess(mangas);
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                mPage--;
                                mViewModel.hideProgressBar();
                                mViewModel.onGetMangaFailed(throwable.getMessage().toString());
                            }
                        }, new Action0() {
                            @Override
                            public void call() {
                                mViewModel.hideProgressBar();
                            }
                        });
                break;
        }

        mSubscription.add(subscription);
    }

    @Override
    public void onLoadMoreData() {
        mPage++;
        getData();
    }
}
