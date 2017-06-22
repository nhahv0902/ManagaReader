package toandoan.framgia.com.rxjavaretrofit.screen.mana.allmanga;

import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.screen.BasePresenter;
import toandoan.framgia.com.rxjavaretrofit.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface AllMangaContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void showProgressBar();

        void hideProgressBar();

        void onGetMangaSucess(List<AllMangaAdapter.MangaModel> mangas);

        void onGetMangaFailed(String msg);

        void onItemClick(Manga source);

        void searchMangaByName(String key);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getAllMangas();
        
        void searchMangaByName(String key);
    }
}
