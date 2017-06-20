package toandoan.framgia.com.rxjavaretrofit.screen.mangaDetail.mangachapter;

/**
 * Listens to user actions from the UI ({@link MangaChapterFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class MangaChapterPresenter implements MangaChapterContract.Presenter {
    private static final String TAG = MangaChapterPresenter.class.getName();

    private final MangaChapterContract.ViewModel mViewModel;

    public MangaChapterPresenter(MangaChapterContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
