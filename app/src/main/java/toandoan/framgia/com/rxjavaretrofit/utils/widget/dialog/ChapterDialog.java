package toandoan.framgia.com.rxjavaretrofit.utils.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.data.model.Chap;
import toandoan.framgia.com.rxjavaretrofit.databinding.DialogChapterBinding;
import toandoan.framgia.com.rxjavaretrofit.screen.mangaDetail.mangachapter.MangaChapterAdapter;
import toandoan.framgia.com.rxjavaretrofit.screen.mangaDetail.mangachapter.OnChapterClickListtenner;

/**
 * Created by toand on 6/25/2017.
 */

public class ChapterDialog extends Dialog {
    private List<Chap> mChaps;
    private int mCurrentChapPos;
    private OnChapterClickListtenner mListtenner;

    public void initData(List<Chap> chaps, int currentChapPos,
            OnChapterClickListtenner listtenner) {
        mChaps = chaps;
        mCurrentChapPos = currentChapPos;
        mListtenner = listtenner;
        inits();
    }

    public ChapterDialog(@NonNull Context context) {
        super(context);
    }

    public ChapterDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected ChapterDialog(@NonNull Context context, boolean cancelable,
            @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    private void inits() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        DialogChapterBinding binding = DialogChapterBinding.inflate(getLayoutInflater());
        binding.setAdapter(new MangaChapterAdapter(mChaps, mListtenner));

        binding.buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        binding.getRoot()
                .setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
        binding.executePendingBindings();
        setContentView(binding.getRoot());
    }
}
