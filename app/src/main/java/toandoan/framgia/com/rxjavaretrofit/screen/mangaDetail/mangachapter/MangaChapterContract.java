package toandoan.framgia.com.rxjavaretrofit.screen.mangaDetail.mangachapter;

import toandoan.framgia.com.rxjavaretrofit.data.model.Chap;
import toandoan.framgia.com.rxjavaretrofit.screen.BasePresenter;
import toandoan.framgia.com.rxjavaretrofit.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface MangaChapterContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onChapterItemClick(Chap chap, int pos);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
