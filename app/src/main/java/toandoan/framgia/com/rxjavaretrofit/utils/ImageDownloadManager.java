package toandoan.framgia.com.rxjavaretrofit.utils;

/**
 * Created by toand on 6/21/2017.
 */

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StringLoader;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BaseTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import toandoan.framgia.com.rxjavaretrofit.R;

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
    private static final String FORWARD_SLASH = "/";
    private static final String EG_FILE_SUFFIX = ".jpg";

    public static ImageDownloadManager getInstance(Context context) {
        if (sInstance == null) sInstance = new ImageDownloadManager(context);
        return sInstance;
    }

    private ImageDownloadManager(Context context) {
        mContext = context;
    }

    public void downloads(String managakName, String chapId, List<String> urls,
            final PercentCallback callBack) {
        int index = 0;
        final int size = urls.size();

        for (String item : urls) {
            index++;
            final int finalIndex = index;
            final int maxIndex = index;
            download(managakName, chapId, item, new ImageDownloadManager.DownloadCallBack() {
                @Override
                public void onDownloaded(String url) {
                    Log.d("TAG", "success file = " + url);
                    callBack.onPercent(percent(finalIndex, size));
                    if (maxIndex == size) {
                        callBack.onDownloaded();
                        Log.d("TAG", "success");
                    }
                }

                @Override
                public void onDownloadFailure(String url) {
                    Log.d("TAG", "error file = " + url);
                }

                @Override
                public void onDownloadCompleted() {

                }
            });
        }
    }

    private int percent(int index, int size) {
        return 100 * index / size;
    }

    public void download(final String managakName, final String chapId, final String url,
            final DownloadCallBack callBack) {
        Glide.with(mContext)
                .load(url)
                .thumbnail(1f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model,
                            Target<GlideDrawable> target, boolean isFirstResource) {
                        Log.d("TAG", "Load false");
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model,
                            Target<GlideDrawable> target, boolean isFromMemoryCache,
                            boolean isFirstResource) {
                        Log.d("TAG", "Load true");
                        callBack.onDownloaded(url);

                        return false;
                    }
                }).preload();
    }

    private File getAlbumDir(final String manakName, final String chapId) {
        File storageDir = null;
        if (TextUtils.equals(Environment.MEDIA_MOUNTED, Environment.getExternalStorageState())) {
            storageDir = new File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                    "managakReader/" + manakName + FORWARD_SLASH + chapId);

            if (!storageDir.mkdirs()) {
                if (!storageDir.exists()) {
                    Log.d("CameraSample", "failed to create directory");
                    return null;
                }
            }
        } else {
            Log.d(mContext.getString(R.string.app_name),
                    "External storage is not mounted READ/WRITE.");
        }
        return storageDir;
    }

    private void writeFile(File readFile, File writeFile, DownloadCallBack callBack) {
        try {
            InputStream inputStream = new FileInputStream(readFile);
            FileOutputStream fileOutput = new FileOutputStream(writeFile);
            byte[] buffer = new byte[1024];
            int bufferLength = 0;
            while ((bufferLength = inputStream.read(buffer)) > 0) {
                fileOutput.write(buffer, 0, bufferLength);
            }
            fileOutput.close();
            inputStream.close();
            callBack.onDownloaded(writeFile.getPath());
        } catch (IOException e) {
            e.printStackTrace();
            callBack.onDownloadFailure(writeFile.getPath());
        }
    }

    public interface DownloadCallBack {
        void onDownloaded(String url);

        void onDownloadFailure(String url);

        void onDownloadCompleted();
    }

    public interface PercentCallback {
        void onPercent(int percent);

        void onDownloaded();
    }
}

