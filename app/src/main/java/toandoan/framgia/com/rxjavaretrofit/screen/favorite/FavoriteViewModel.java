package toandoan.framgia.com.rxjavaretrofit.screen.favorite;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.BR;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.screen.mangaDetail.MangaDetailActivity;
import toandoan.framgia.com.rxjavaretrofit.utils.navigator.Navigator;

/**
 * Exposes the data to be used in the Favorite screen.
 */
public class FavoriteViewModel extends BaseObservable implements FavoriteContract.ViewModel {

    private FavoriteContract.Presenter mPresenter;
    private FavoriteAdapter mAdapter;
    private Navigator mNavigator;

    public FavoriteViewModel(Navigator navigator) {
        mNavigator = navigator;
        mAdapter = new FavoriteAdapter(this);
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
    public void setPresenter(FavoriteContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onRemoveFavorite(Manga manga, int pos) {
        mAdapter.removeItem(pos);
        mPresenter.removeFavorite(manga.getId());
    }

    @Override
    public void onItemClick(Manga manga) {
        mNavigator.startActivity(MangaDetailActivity.getInstance(mNavigator.getContext(), manga));
    }

    @Override
    public void onGetFavoriteSuccess(List<Manga> mangas) {
        if (mangas == null) return;
        mAdapter.updateManga(mangas);
    }

    @Override
    public void onUndoDeleteClick() {
        mPresenter.getFavoritesManga();
    }

    @Override
    public void onDeleteAllFavoriteManga() {
        mPresenter.deleteAllFavorite();
    }

    @Bindable
    public FavoriteAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(FavoriteAdapter adapter) {
        mAdapter = adapter;
        notifyPropertyChanged(BR.adapter);
    }
}
