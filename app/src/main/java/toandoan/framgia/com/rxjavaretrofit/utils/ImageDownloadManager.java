package toandoan.framgia.com.rxjavaretrofit.utils;

/**
 * Created by toand on 6/21/2017.
 */

import android.content.Context;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BaseTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import java.io.File;
import java.util.List;

/**
 * Helper to download Images. This takes set of Urls to download and a folder oath to cave the
 * files. Calls  onSuccess only if all images are downloaded. Else calls
 * onFailure()
 *
 * @author: bedprakash on 11/1/17.
 */

public class ImageDownloadManager {
    private Context mContext;
    private static ImageDownloadManager sInstance;

    public static ImageDownloadManager getInstance(Context context) {
        if (sInstance == null) sInstance = new ImageDownloadManager(context);
        return sInstance;
    }

    public ImageDownloadManager(Context context) {
        mContext = context;
    }

    private void download(List<String> urls) {

    }

    private void download(String url) {
        Glide.with(mContext).load(url)
                .downloadOnly(new BaseTarget<File>() {
                    @Override
                    public void onResourceReady(File resource,
                            GlideAnimation<? super File> glideAnimation) {

                    }

                    @Override
                    public void getSize(SizeReadyCallback cb) {

                    }
                });
    }

    public interface CallBack {
        void onDownloaded(String url);

        void onDownloadFailure(String url);

        void onDownloadCompeleted();
    }
}

