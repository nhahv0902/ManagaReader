package toandoan.framgia.com.rxjavaretrofit.screen.mana.allmanga;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.data.source.MangaDataSource;
import toandoan.framgia.com.rxjavaretrofit.data.source.SourcesDataSource;

/**
 * Listens to user actions from the UI ({@link AllMangaFragment}), retrieves the data and updates
 * the UI as required.
 */
final class AllMangaPresenter implements AllMangaContract.Presenter {
    private static final String TAG = AllMangaPresenter.class.getName();

    private final AllMangaContract.ViewModel mViewModel;
    private MangaDataSource mMangaRepository;
    private SourcesDataSource mSourceRepository;
    private CompositeSubscription mSubscription;

    public AllMangaPresenter(AllMangaContract.ViewModel viewModel, MangaDataSource mangaRepository,
            SourcesDataSource sourceRepository) {
        mViewModel = viewModel;
        mMangaRepository = mangaRepository;
        mSourceRepository = sourceRepository;
        mSubscription = new CompositeSubscription();
        getAllMangas();
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        mSubscription.clear();
    }

    @Override
    public void getAllMangas() {
        String source = mSourceRepository.getCurrentSourceCode();
        if (source == null) return;
        Subscription subscription = mMangaRepository.getAllMangas(source)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mViewModel.showProgressBar();
                    }
                })
                .subscribe(new Action1<List<Manga>>() {
                    @Override
                    public void call(List<Manga> mangas) {
                        if (mangas == null) return;
                        optimizeResponse(mangas);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mViewModel.hideProgressBar();
                        mViewModel.onGetMangaFailed(throwable.getMessage().toString());
                    }
                });

        mSubscription.add(subscription);
    }

    private void optimizeResponse(List<Manga> mangas) {
        Subscription subscription = Observable.just(optimizeResponseData(mangas))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<List<AllMangaAdapter.MangaModel>>() {
                    @Override
                    public void call(List<AllMangaAdapter.MangaModel> mangaModels) {
                        mViewModel.onGetMangaSucess(mangaModels);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mViewModel.hideProgressBar();
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        mViewModel.hideProgressBar();
                    }
                });
        mSubscription.add(subscription);
    }

    private List<AllMangaAdapter.MangaModel> optimizeResponseData(List<Manga> mangas) {
        optimizeData(mangas);
        return initModelFromData(mangas);
    }

    private void optimizeData(List<Manga> mangas) {
        for (Manga manga : mangas) {
            if (manga == null || TextUtils.isEmpty(manga.getName())) continue;
            while (manga.getName().startsWith("\"")
                    || manga.getName().startsWith("+")
                    || manga.getName().startsWith("-")
                    || manga.getName().startsWith(" ")
                    || manga.getName().startsWith(")")
                    || manga.getName().startsWith("(")
                    || manga.getName().startsWith("\'")
                    || manga.getName().startsWith("/")
                    || manga.getName().startsWith("#")
                    || manga.getName().startsWith("$")
                    || manga.getName().startsWith("&")
                    || manga.getName().startsWith(":")
                    || manga.getName().startsWith("\\")
                    || manga.getName().startsWith(".")) {
                manga.setName(manga.getName().substring(1).trim());
            }
        }

        if (mangas != null && mangas.size() > 0) {
            Collections.sort(mangas, new Comparator<Manga>() {
                @Override
                public int compare(final Manga object1, final Manga object2) {
                    if (object1 == null
                            || object2 == null
                            || TextUtils.isEmpty(object1.getName())
                            || TextUtils.isEmpty(object2.getName())) {
                        return -1;
                    }
                    String firstAlpha = String.valueOf(object1.getName().substring(0, 1));
                    String secondAlpha = String.valueOf(object2.getName().substring(0, 1));
                    return firstAlpha.compareTo(secondAlpha );
                }
            });
        }
    }

    public boolean isAlpha(String name) {
        return name.matches("[a-zA-Z]+");
    }

    private List<AllMangaAdapter.MangaModel> initModelFromData(List<Manga> mangas) {
        List<AllMangaAdapter.MangaModel> models = new ArrayList<>();
        char alpha = 0;
        AllMangaAdapter.MangaModel mangaModel = null;
        for (Manga manga : mangas) {
            char firstChar = manga.getName().charAt(0);
            if (!isAlpha(String.valueOf(firstChar))) {
                if (mangaModel == null) {
                    mangaModel = new AllMangaAdapter.MangaModel();
                }
                mangaModel.setFirstChar("#");
            } else {
                if (firstChar != alpha) {
                    if (mangaModel != null) {
                        models.add(mangaModel);
                    }

                    mangaModel = new AllMangaAdapter.MangaModel();
                    alpha = manga.getName().charAt(0);
                    mangaModel.setFirstChar(String.valueOf(alpha));
                }
            }

            if (mangaModel != null) {
                mangaModel.getMangas().add(manga);
            }
        }
        models.add(mangaModel);
        return models;
    }
}
