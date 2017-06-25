package toandoan.framgia.com.rxjavaretrofit.screen.recentManga;

/**
 * Exposes the data to be used in the RecentManga screen.
 */

public class RecentMangaViewModel implements RecentMangaContract.ViewModel {

    private RecentMangaContract.Presenter mPresenter;

    public RecentMangaViewModel() {
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(RecentMangaContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
