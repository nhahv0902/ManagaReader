package toandoan.framgia.com.rxjavaretrofit.screen.mangaDetail;

/**
 * Exposes the data to be used in the MangaDetail screen.
 */

public class MangaDetailViewModel implements MangaDetailContract.ViewModel {

    private MangaDetailContract.Presenter mPresenter;

    public MangaDetailViewModel() {
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
    public void setPresenter(MangaDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
