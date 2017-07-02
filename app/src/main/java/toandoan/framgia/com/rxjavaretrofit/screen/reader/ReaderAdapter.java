package toandoan.framgia.com.rxjavaretrofit.screen.reader;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.databinding.ItemReaderBinding;
import toandoan.framgia.com.rxjavaretrofit.utils.widget.zoomimage.OnDoubleTapListener;
import toandoan.framgia.com.rxjavaretrofit.utils.widget.zoomimage.PhotoView;

/**
 * Created by framgia on 21/06/2017.
 */

public class ReaderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> mUrls;
    private String mNextChap;
    private Context mContext;
    private LayoutInflater mInflater;
    private ReaderViewModel mViewModel;

    public ReaderAdapter(ReaderViewModel viewModel, List<String> urls, String nextChap) {
        mUrls = urls;
        mViewModel = viewModel;
        mNextChap = nextChap;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) mContext = parent.getContext();
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());

        ItemReaderBinding itemReaderBinding =
                DataBindingUtil.inflate(mInflater, R.layout.item_reader, parent, false);
        return new ItemViewHolder(itemReaderBinding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ItemViewHolder) holder).bindData(mUrls.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mUrls != null ? mUrls.size() : 0;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private PhotoView mPhotoView;
        private ProgressBar mProgressBar;
        private ItemReaderBinding mBinding;

        public ItemViewHolder(ItemReaderBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mPhotoView = binding.photoView;
            mProgressBar = binding.progressBar;
            mPhotoView.setCustomTapListener(new OnDoubleTapListener() {
                @Override
                public boolean onSingleTapConfirmed(MotionEvent e) {
                    mViewModel.onItemImageReaderClick();
                    return false;
                }

                @Override
                public boolean onDoubleTap(MotionEvent ev) {
                    return false;
                }

                @Override
                public boolean onDoubleTapEvent(MotionEvent e) {
                    return false;
                }
            });
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
            }).diskCacheStrategy(DiskCacheStrategy.ALL).thumbnail(1f).into(mPhotoView);
            mBinding.setViewModel(mViewModel);
            mBinding.setNextChap(mNextChap);
            mBinding.setIsShowLoadMore(
                    mNextChap != null && position == getItemCount() - 1 ? View.VISIBLE : View.GONE);
            mBinding.executePendingBindings();
        }
    }
}
