package toandoan.framgia.com.rxjavaretrofit.screen.favorite;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.databinding.ItemFavoriteBinding;

/**
 * Created by toand on 6/19/2017.
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    private List<Manga> mMangas = new ArrayList<>();
    private LayoutInflater mInflater;
    private FavoriteViewModel mViewModel;

    public FavoriteAdapter(FavoriteViewModel viewModel) {
        mViewModel = viewModel;
    }

    public void updateManga(List<Manga> updatedManga) {
        if (updatedManga == null) return;
        mMangas.clear();
        mMangas.addAll(updatedManga);
        notifyDataSetChanged();
    }

    public void clearData() {
        mMangas.clear();
        notifyDataSetChanged();
    }

    public void removeItem(int pos) {
        mMangas.remove(pos);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        ItemFavoriteBinding item =
                DataBindingUtil.inflate(mInflater, R.layout.item_favorite, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(FavoriteAdapter.ViewHolder holder, int position) {
        holder.bindData(mMangas.get(position));
    }

    @Override
    public int getItemCount() {
        return mMangas != null ? mMangas.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemFavoriteBinding mBinding;

        public ViewHolder(ItemFavoriteBinding item) {
            super(item.getRoot());
            mBinding = item;
        }

        public void bindData(Manga manga) {
            if (manga == null) return;
            mBinding.setManga(manga);
            mBinding.setViewModel(mViewModel);
            mBinding.setPos(getAdapterPosition());
            mBinding.executePendingBindings();
        }
    }
}
