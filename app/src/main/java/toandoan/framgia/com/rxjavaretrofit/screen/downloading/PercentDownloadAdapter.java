package toandoan.framgia.com.rxjavaretrofit.screen.downloading;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.data.model.PercentDownload;
import toandoan.framgia.com.rxjavaretrofit.databinding.ItemPercentDownloadBinding;

/**
 * Created by nhahv0902 on 7/5/17.
 * <></>
 */

public class PercentDownloadAdapter
        extends RecyclerView.Adapter<PercentDownloadAdapter.PercentDownloadHolder> {

    private List<PercentDownload> mPercentDownloads = new ArrayList<>();
    private LayoutInflater mInflater;

    public PercentDownloadAdapter(List<PercentDownload> percentDownloads) {
        mPercentDownloads = percentDownloads;
    }

    @Override
    public PercentDownloadHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        ItemPercentDownloadBinding binding =
                DataBindingUtil.inflate(mInflater, R.layout.item_percent_download, parent, false);
        return new PercentDownloadHolder(binding);
    }

    @Override
    public void onBindViewHolder(PercentDownloadHolder holder, int position) {
        PercentDownload message = mPercentDownloads.get(position);
        if (message != null) holder.bind(message, position);
    }

    @Override
    public int getItemCount() {
        return mPercentDownloads == null ? 0 : mPercentDownloads.size();
    }

    public class PercentDownloadHolder extends RecyclerView.ViewHolder {

        private ItemPercentDownloadBinding mBinding;

        public PercentDownloadHolder(ItemPercentDownloadBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(PercentDownload message, int position) {
            mBinding.setChapDownload(message);
            mBinding.executePendingBindings();
        }
    }
}
