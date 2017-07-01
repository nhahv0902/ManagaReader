package toandoan.framgia.com.rxjavaretrofit.screen.download;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.data.model.Chap;
import toandoan.framgia.com.rxjavaretrofit.databinding.ItemDownloadMangakBinding;
import toandoan.framgia.com.rxjavaretrofit.screen.mangaDetail.mangachapter.OnChapterClickListtenner;

public class DownloadMangakAdapter extends RecyclerView.Adapter<DownloadMangakAdapter.ViewHolder> {
    private List<Chap> mChaps = new ArrayList<>();
    private LayoutInflater mInflater;
    private OnChapterClickListtenner mListtenner;

    public DownloadMangakAdapter(List<Chap> chaps, OnChapterClickListtenner listtenner) {
        mChaps = chaps;
        mListtenner = listtenner;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        ItemDownloadMangakBinding binding =
                DataBindingUtil.inflate(mInflater, R.layout.item_download_mangak, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(mChaps.get(position));
    }

    @Override
    public int getItemCount() {
        return mChaps != null ? mChaps.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemDownloadMangakBinding mBinding;

        public ViewHolder(ItemDownloadMangakBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bindData(Chap chap) {
            mBinding.setItem(chap);
            mBinding.setPosition(getAdapterPosition());
            mBinding.setListenner(mListtenner);
            mBinding.executePendingBindings();
        }
    }
}