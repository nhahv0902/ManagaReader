package toandoan.framgia.com.rxjavaretrofit.screen.mana;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.databinding.ItemMangaBinding;

/**
 * Created by toand on 6/19/2017.
 */

public class MangaAdapter extends RecyclerView.Adapter<MangaAdapter.ViewHolder> {
    private List<Manga> mMangas = new ArrayList<>();
    private LayoutInflater mInflater;
    private MangaViewModel mViewModel;

    public void updateManga(List<Manga> updatedManga) {
        if (updatedManga == null) return;
        mMangas.addAll(updatedManga);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        ItemMangaBinding item =
                DataBindingUtil.inflate(mInflater, R.layout.item_manga, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(MangaAdapter.ViewHolder holder, int position) {
        holder.bindData(mMangas.get(position));
    }

    @Override
    public int getItemCount() {
        return mMangas != null ? mMangas.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemMangaBinding mBinding;

        public ViewHolder(ItemMangaBinding item) {
            super(item.getRoot());
            mBinding = item;
        }

        public void bindData(Manga manga) {
            if (manga == null) return;
            mBinding.setManga(manga);
            mBinding.setViewModel(mViewModel);
            mBinding.executePendingBindings();
        }
    }
}
