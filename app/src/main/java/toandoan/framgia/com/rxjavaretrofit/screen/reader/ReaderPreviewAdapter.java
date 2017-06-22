package toandoan.framgia.com.rxjavaretrofit.screen.reader;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.databinding.ItemSmallPreviewBinding;

/**
 * Created by toand on 6/23/2017.
 */

public class ReaderPreviewAdapter extends RecyclerView.Adapter<ReaderPreviewAdapter.ViewHolder> {
    private List<String> mUrls;
    private Context mContext;
    private LayoutInflater mInflater;
    private ReaderViewModel mViewModel;

    public ReaderPreviewAdapter(ReaderViewModel viewModel, List<String> urls) {
        mViewModel = viewModel;
        mUrls = urls;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) mContext = parent.getContext();
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        ItemSmallPreviewBinding binding =
                DataBindingUtil.inflate(mInflater, R.layout.item_small_preview, parent, false);
        return new ReaderPreviewAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(mUrls.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mUrls != null ? mUrls.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ItemSmallPreviewBinding mBinding;
        private ImageView mPhotoView;
        private ProgressBar mProgressBar;
        private TextView mTextPos;

        public ViewHolder(ItemSmallPreviewBinding binding) {
            super(binding.getRoot());
            mPhotoView = binding.photoView;
            mProgressBar = binding.progressBar;
            mTextPos = binding.textPos;
            binding.getRoot().setOnClickListener(this);
        }

        private void bindData(String url, int position) {
            mProgressBar.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(url).listener(new RequestListener<String, GlideDrawable>() {
                @Override
                public boolean onException(Exception e, String model, Target<GlideDrawable> target,
                        boolean isFirstResource) {
                    mProgressBar.setVisibility(View.GONE);
                    return false;
                }

                @Override
                public boolean onResourceReady(GlideDrawable resource, String model,
                        Target<GlideDrawable> target, boolean isFromMemoryCache,
                        boolean isFirstResource) {
                    mProgressBar.setVisibility(View.GONE);
                    return false;
                }
            }).into(mPhotoView);

            mTextPos.setText(String.valueOf(position + 1));
        }

        @Override
        public void onClick(View v) {
            mViewModel.onPreviewItemClick(getAdapterPosition());
        }
    }
}
