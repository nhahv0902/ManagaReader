package toandoan.framgia.com.rxjavaretrofit.screen.mangaDetail.mangaoverview;

/**
 * Listens to user actions from the UI ({@link MangaOverviewFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class MangaOverviewPresenter implements MangaOverviewContract.Presenter {
    private static final String TAG = MangaOverviewPresenter.class.getName();

    private final MangaOverviewContract.ViewModel mViewModel;

    public MangaOverviewPresenter(MangaOverviewContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
