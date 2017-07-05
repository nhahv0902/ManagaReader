package toandoan.framgia.com.rxjavaretrofit.screen.downloading;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.data.model.MessageDownloading;
import toandoan.framgia.com.rxjavaretrofit.databinding.ItemDownloadingBinding;

/**
 * Created by nhahv0902 on 7/5/17.
 * <></>
 */

public class DownloadingAdapter extends RecyclerView.Adapter<DownloadingAdapter.DownloadingHolder> {

    private List<MessageDownloading> mMessageDownloadings = new ArrayList<>();
    private LayoutInflater mInflater;
    private DownloadingViewModel mViewModel;

    public DownloadingAdapter(List<MessageDownloading> messageDownloadings,
            DownloadingViewModel viewModel) {
        mMessageDownloadings = messageDownloadings;
        mViewModel = viewModel;
    }

    @Override
    public DownloadingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        ItemDownloadingBinding binding =
                DataBindingUtil.inflate(mInflater, R.layout.item_downloading, parent, false);
        binding.setViewModel(mViewModel);
        return new DownloadingHolder(binding);
    }

    @Override
    public void onBindViewHolder(DownloadingHolder holder, int position) {
        MessageDownloading message = mMessageDownloadings.get(position);
        if (message != null) holder.bind(message, position);
    }

    @Override
    public int getItemCount() {
        return mMessageDownloadings == null ? 0 : mMessageDownloadings.size();
    }

    public class DownloadingHolder extends RecyclerView.ViewHolder {

        private ItemDownloadingBinding mBinding;

        public DownloadingHolder(ItemDownloadingBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(MessageDownloading message, int position) {
            mBinding.setMessage(message);
            mBinding.setPos(position);
            mBinding.executePendingBindings();
        }
    }
}
