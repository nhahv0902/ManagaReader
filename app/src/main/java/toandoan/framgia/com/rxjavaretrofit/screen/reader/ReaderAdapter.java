package toandoan.framgia.com.rxjavaretrofit.screen.reader;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.databinding.ItemReaderBinding;

/**
 * Created by framgia on 21/06/2017.
 */

public class ReaderAdapter extends RecyclerView.Adapter<ReaderAdapter.ViewHolder> {
    private List<String> mUrls;
    private LayoutInflater mInflater;

    public ReaderAdapter(List<String> urls) {
        mUrls = urls;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        ItemReaderBinding binding =
                DataBindingUtil.inflate(mInflater, R.layout.item_reader, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(mUrls.get(position));
    }

    @Override
    public int getItemCount() {
        return mUrls != null ? mUrls.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemReaderBinding mBinding;

        public ViewHolder(ItemReaderBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        private void bindData(String url) {
            mBinding.setUrl(url);
            mBinding.executePendingBindings();
        }
    }
}
