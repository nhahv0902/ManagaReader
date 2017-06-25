package toandoan.framgia.com.rxjavaretrofit.screen.recentManga;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.databinding.ItemRecentMangaBinding;
import toandoan.framgia.com.rxjavaretrofit.utils.widget.itemtouch.Extension;

/**
 * Created by toand on 6/25/2017.
 */

public class RecentMangaAdapter extends RecyclerView.Adapter<RecentMangaAdapter.ViewHolder> {
    private List<Manga> mDatas;
    private RecentMangaViewModel mViewModel;

    public RecentMangaAdapter(RecentMangaViewModel viewModel) {
        mDatas = new ArrayList<>();
        mViewModel = viewModel;
    }

    public void setDatas(List<Manga> datas) {
        mDatas.clear();
        mDatas.addAll(datas);
    }
    public void clearData(){
        mDatas.clear();
        notifyDataSetChanged();
    }

    public void updateData(List<Manga> datas) {
        setDatas(datas);
        notifyDataSetChanged();
    }

    private LayoutInflater getLayoutInflater(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext());
    }

    @Override
    public RecentMangaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemRecentMangaBinding binding =
                DataBindingUtil.inflate(getLayoutInflater(parent), R.layout.item_recent_manga,
                        parent, false);
        return new ViewHolder(binding);
    }

    public void move(int from, int to) {
        Manga prev = mDatas.remove(from);
        mDatas.add(to > from ? to - 1 : to, prev);
        notifyItemMoved(from, to);
    }

    @Override
    public void onBindViewHolder(final RecentMangaAdapter.ViewHolder holder, int position) {
        holder.bindData(mDatas.get(position));
    }

    public void deleteManga(int adapterPosition) {
        mDatas.remove(adapterPosition);
        notifyItemRemoved(adapterPosition);
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements Extension {
        private View mActionContainer;
        public View mViewContent;
        private ItemRecentMangaBinding mBinding;

        public ViewHolder(ItemRecentMangaBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mActionContainer = binding.actionContainer;
            mViewContent = binding.layoutContent.viewContent;
        }

        public void bindData(Manga manga) {
            if (manga == null) return;
            mBinding.setManga(manga);
            mBinding.setPos(getAdapterPosition());
            mBinding.setViewModel(mViewModel);
            mBinding.executePendingBindings();
        }

        @Override
        public float getActionWidth() {
            return mActionContainer.getWidth();
        }
    }
}