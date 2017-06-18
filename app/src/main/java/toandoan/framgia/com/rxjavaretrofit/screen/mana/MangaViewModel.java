package toandoan.framgia.com.rxjavaretrofit.screen.mana;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.BR;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.utils.navigator.Navigator;

/**
 * Exposes the data to be used in the Mana screen.
 */

public class MangaViewModel extends BaseObservable implements MangaContract.ViewModel {

    private MangaContract.Presenter mPresenter;
    private MangaAdapter mAdapter;
    private Navigator mNavigator;

    public MangaViewModel(Navigator navigator) {
        mAdapter = new MangaAdapter();
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
    public void setPresenter(MangaContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Bindable
    public MangaAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(MangaAdapter adapter) {
        mAdapter = adapter;
        notifyPropertyChanged(BR.adapter);
    }

    @Override
    public void onGetMangaSuccess(List<Manga> mangas) {
        mAdapter.updateManga(mangas);
    }

    @Override
    public void onGetMangaFailed(String msg) {
        mNavigator.showToast(msg);
    }
}
