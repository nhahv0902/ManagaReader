package toandoan.framgia.com.rxjavaretrofit.screen.downloadmangak;

import java.util.List;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.data.source.DownloadDataSource;

/**
 * Listens to user actions from the UI ({@link DownloadMangakFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class DownloadMangakPresenter implements DownloadMangakContract.Presenter {
    private static final String TAG = DownloadMangakPresenter.class.getName();

    private final DownloadMangakContract.ViewModel mViewModel;
    private final DownloadDataSource mRepository;
    private CompositeSubscription mSubscription;

    public DownloadMangakPresenter(DownloadMangakContract.ViewModel viewModel,
            DownloadDataSource repository) {
        mViewModel = viewModel;
        mRepository = repository;
        mSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {
        getAllMangakDownload();
    }

    @Override
    public void onStop() {
        mSubscription.clear();
    }

    @Override
    public void getAllMangakDownload() {
        Subscription subscription = mRepository.getAllMangakDownload()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Manga>>() {
                    @Override
                    public void call(List<Manga> mangas) {
                        mViewModel.onGetMangakDownloadSuccess(mangas);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
        mSubscription.add(subscription);
    }
}
