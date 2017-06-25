package toandoan.framgia.com.rxjavaretrofit.screen.recentManga;

/**
 * Listens to user actions from the UI ({@link RecentMangaFragment}), retrieves the data and updates
 * the UI as required.
 */
final class RecentMangaPresenter implements RecentMangaContract.Presenter {
    private static final String TAG = RecentMangaPresenter.class.getName();

    private final RecentMangaContract.ViewModel mViewModel;

    public RecentMangaPresenter(RecentMangaContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
