package toandoan.framgia.com.rxjavaretrofit.screen.mangaDetail.mangaoverview;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.databinding.ItemGenreBinding;

/**
 * Created by framgia on 21/06/2017.
 */

public class MangaGenreAdapter extends RecyclerView.Adapter<MangaGenreAdapter.ViewHolder> {
    private List<String> mGenres;
    private LayoutInflater mInflater;
    private MangaOverviewViewModel mViewModel;

    public MangaGenreAdapter(MangaOverviewViewModel viewModel, List<String> genres) {
        mViewModel = viewModel;
        mGenres = genres;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        ItemGenreBinding binding =
                DataBindingUtil.inflate(mInflater, R.layout.item_genre, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(mGenres.get(position));
    }

    @Override
    public int getItemCount() {
        return mGenres != null ? mGenres.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemGenreBinding mBinding;

        public ViewHolder(ItemGenreBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bindData(String str) {
            mBinding.setItem(str);
            mBinding.setViewModel(mViewModel);
            mBinding.executePendingBindings();
        }
    }
}
