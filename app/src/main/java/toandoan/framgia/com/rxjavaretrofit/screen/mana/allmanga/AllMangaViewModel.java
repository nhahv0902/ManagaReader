package toandoan.framgia.com.rxjavaretrofit.screen.mana.allmanga;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.BR;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.utils.navigator.Navigator;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Exposes the data to be used in the AllManga screen.
 */

public class AllMangaViewModel extends BaseObservable implements AllMangaContract.ViewModel {

    private AllMangaContract.Presenter mPresenter;
    private AllMangaAdapter mAdapter;
    private Navigator mNavigator;
    private int mProgressVisible = VISIBLE;

    public AllMangaViewModel(Navigator navigator) {
        mNavigator = navigator;
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
    public void setPresenter(AllMangaContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showProgressBar() {
        setProgressVisible(VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        setProgressVisible(GONE);
    }

    @Override
    public void onGetMangaSucess(List<AllMangaAdapter.MangaModel> mangas) {
        mAdapter.updateData(mangas);
    }

    @Override
    public void onGetMangaFailed(String msg) {
        mNavigator.showToast(msg);
    }

    @Override
    public void onItemClick(Manga source) {
        // TODO: 20/06/2017 open manga detail
    }

    public AllMangaAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(AllMangaAdapter adapter) {
        mAdapter = adapter;
    }

    @Bindable
    public int getProgressVisible() {
        return mProgressVisible;
    }

    public void setProgressVisible(int progressVisible) {
        mProgressVisible = progressVisible;
        notifyPropertyChanged(BR.progressVisible);
    }


}
