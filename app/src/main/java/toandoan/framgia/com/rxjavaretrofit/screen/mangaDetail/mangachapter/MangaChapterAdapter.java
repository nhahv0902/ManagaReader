package toandoan.framgia.com.rxjavaretrofit.screen.mangaDetail.mangachapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.data.model.Chap;
import toandoan.framgia.com.rxjavaretrofit.databinding.ItemMangaChaperBinding;

/**
 * Created by toand on 6/21/2017.
 */

public class MangaChapterAdapter extends RecyclerView.Adapter<MangaChapterAdapter.ViewHolder> {
    private List<Chap> mChaps = new ArrayList<>();
    private LayoutInflater mInflater;
    private MangaChapterViewModel mViewModel;

    public MangaChapterAdapter(MangaChapterViewModel viewModel, List<Chap> chaps) {
        mViewModel = viewModel;
        mChaps = chaps;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        ItemMangaChaperBinding binding =
                DataBindingUtil.inflate(mInflater, R.layout.item_manga_chaper, parent, false);
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
        private ItemMangaChaperBinding mBinding;

        public ViewHolder(ItemMangaChaperBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bindData(Chap chap) {
            mBinding.setItem(chap);
            mBinding.setViewModel(mViewModel);
            mBinding.executePendingBindings();
        }
    }
}
