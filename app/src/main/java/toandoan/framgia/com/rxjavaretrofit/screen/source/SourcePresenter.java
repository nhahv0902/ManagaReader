package toandoan.framgia.com.rxjavaretrofit.screen.source;

import java.util.List;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import toandoan.framgia.com.rxjavaretrofit.data.model.Source;
import toandoan.framgia.com.rxjavaretrofit.data.source.SourcesDataSource;

/**
 * Listens to user actions from the UI ({@link SourceActivity}), retrieves the data and updates
 * the UI as required.
 */
final class SourcePresenter implements SourceContract.Presenter {
    private static final String TAG = SourcePresenter.class.getName();

    private SourceContract.ViewModel mViewModel;
    private SourcesDataSource mSourcesDataRepository;
    private CompositeSubscription mSubscription;
    private Source mCurrentSource;
    private List<Source> mSources;

    public SourcePresenter(SourceContract.ViewModel viewModel,
            SourcesDataSource sourcesDataSource) {
        mViewModel = viewModel;
        mSourcesDataRepository = sourcesDataSource;
        mSubscription = new CompositeSubscription();
        getCurrentCourse();
        getSource();
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        mSubscription.clear();
    }

    @Override
    public void getSource() {
        Subscription subscription = mSourcesDataRepository.getSources()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mViewModel.showProgress();
                    }
                })
                .subscribe(new Action1<List<Source>>() {
                    @Override
                    public void call(List<Source> sources) {
                        mSources = sources;
                        optimizeSourse();
                        mViewModel.hideProgress();
                        mViewModel.onGetSourceSuccess(sources);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mViewModel.hideProgress();
                        mViewModel.onGetSourceFailed(throwable.getMessage());
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
    public void saveCurrentSource(Source source) {
        Subscription subscription = mSourcesDataRepository.saveCurrentSource(source)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        mViewModel.onSaveSourceSucess();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mViewModel.onSaveSourceFailed();
                    }
                });
        mSubscription.add(subscription);
    }

    private void getCurrentCourse() {
        Subscription subscription = mSourcesDataRepository.getSelectedSources()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<Source>() {
                    @Override
                    public void call(Source source) {
                        mCurrentSource = source;
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
        mSubscription.add(subscription);
    }

    private void optimizeSourse() {
        if (mSources == null || mCurrentSource == null) return;
        for (Source source : mSources) {
            if (source.getId() == mCurrentSource.getId()) {
                source.setSelected(true);
                return;
            }
        }
    }
}
