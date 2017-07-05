package toandoan.framgia.com.rxjavaretrofit.screen.downloadmangak;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.databinding.ItemMangakDownloadBinding;

/**
 * Created by toand on 6/19/2017.
 */

public class DownloadMangakAdapter extends RecyclerView.Adapter<DownloadMangakAdapter.ViewHolder> {
    private List<Manga> mMangas = new ArrayList<>();
    private LayoutInflater mInflater;
    private DownloadMangakViewModel mViewModel;

    public DownloadMangakAdapter(DownloadMangakViewModel viewModel) {
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
        ItemMangakDownloadBinding item =
                DataBindingUtil.inflate(mInflater, R.layout.item_mangak_download, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(DownloadMangakAdapter.ViewHolder holder, int position) {
        holder.bindData(mMangas.get(position));
    }

    @Override
    public int getItemCount() {
        return mMangas != null ? mMangas.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemMangakDownloadBinding mBinding;

        public ViewHolder(ItemMangakDownloadBinding item) {
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
