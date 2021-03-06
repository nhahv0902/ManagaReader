package toandoan.framgia.com.rxjavaretrofit.screen.mangaDetail.mangaoverview;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.databinding.FragmentMangaOverviewBinding;
import toandoan.framgia.com.rxjavaretrofit.screen.BaseFragment;
import toandoan.framgia.com.rxjavaretrofit.utils.navigator.Navigator;

/**
 * MangaOverview Screen.
 */
public class MangaOverviewFragment extends BaseFragment {
    public static final String EXTRA_MANGA = "EXTRA_MANGA";
    private MangaOverviewContract.ViewModel mViewModel;

    public static MangaOverviewFragment newInstance(Manga manga) {
        MangaOverviewFragment fragment = new MangaOverviewFragment();
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

        mViewModel = new MangaOverviewViewModel(new Navigator(this), manga);

        MangaOverviewContract.Presenter presenter = new MangaOverviewPresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        FragmentMangaOverviewBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_manga_overview, container,
                        false);

        binding.setViewModel((MangaOverviewViewModel) mViewModel);
        binding.setManga(manga);
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
