package toandoan.framgia.com.rxjavaretrofit.screen.mangaDetail;

/**
 * Listens to user actions from the UI ({@link MangaDetailActivity}), retrieves the data and updates
 * the UI as required.
 */
final class MangaDetailPresenter implements MangaDetailContract.Presenter {
    private static final String TAG = MangaDetailPresenter.class.getName();

    private final MangaDetailContract.ViewModel mViewModel;

    public MangaDetailPresenter(MangaDetailContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
