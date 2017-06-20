package toandoan.framgia.com.rxjavaretrofit.screen.mangaDetail.mangaoverview;

/**
 * Exposes the data to be used in the MangaOverview screen.
 */

public class MangaOverviewViewModel implements MangaOverviewContract.ViewModel {

    private MangaOverviewContract.Presenter mPresenter;

    public MangaOverviewViewModel() {
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
    public void setPresenter(MangaOverviewContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
