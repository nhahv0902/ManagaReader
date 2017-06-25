package toandoan.framgia.com.rxjavaretrofit.screen.mangaDetail.mangachapter;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.data.model.Chap;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.databinding.FragmentMangaChapterBinding;
import toandoan.framgia.com.rxjavaretrofit.screen.BaseFragment;
import toandoan.framgia.com.rxjavaretrofit.screen.reader.ReaderActivity;
import toandoan.framgia.com.rxjavaretrofit.utils.navigator.Navigator;

/**
 * MangaChapter Screen.
 */
public class MangaChapterFragment extends BaseFragment {

    private MangaChapterContract.ViewModel mViewModel;

    public static final String EXTRA_MANGA = "EXTRA_MANGA";
    public static final String EXTRA_CHAP = "EXTRA_CHAP";

    public static MangaChapterFragment newInstance(Manga manga, Chap currentChap) {
        MangaChapterFragment fragment = new MangaChapterFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_MANGA, manga);
        bundle.putSerializable(EXTRA_CHAP, currentChap);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static MangaChapterFragment newInstance(Manga manga) {
        MangaChapterFragment fragment = new MangaChapterFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_MANGA, manga);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        Manga manga = (Manga) bundle.getSerializable(EXTRA_MANGA);
        Chap currentChap = (Chap) bundle.getSerializable(EXTRA_CHAP);

        FragmentMangaChapterBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_manga_chapter, container,
                        false);
        binding.setViewModel((MangaChapterViewModel) mViewModel);

        mViewModel = new MangaChapterViewModel(new Navigator(this), manga);

        MangaChapterContract.Presenter presenter = new MangaChapterPresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        RecyclerView recyclerView = binding.recyclerChapter;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(
                new MangaChapterAdapter((MangaChapterViewModel) mViewModel, manga.getChaps()));

        if (currentChap != null) {
            for (int i = 0; i < manga.getChaps().size(); i++) {
                if (manga.getChaps().get(i).getId().equals(currentChap.getId())) {
                    new Navigator(this).startActivity(
                            ReaderActivity.getInstance(getContext(), manga, currentChap, i));
                    break;
                }
            }
        }
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    public void onStop() {
        mViewModel.onStop();
        super.onStop();
    }
}
