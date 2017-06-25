package toandoan.framgia.com.rxjavaretrofit.screen.recentManga;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.databinding.FragmentRecentMangaBinding;
import toandoan.framgia.com.rxjavaretrofit.screen.BaseFragment;

/**
 * RecentManga Screen.
 */
public class RecentMangaFragment extends BaseFragment {

    private RecentMangaContract.ViewModel mViewModel;

    public static RecentMangaFragment newInstance() {
        return new RecentMangaFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new RecentMangaViewModel();

        RecentMangaContract.Presenter presenter = new RecentMangaPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        FragmentRecentMangaBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_recent_manga, container, false);
        binding.setViewModel((RecentMangaViewModel) mViewModel);
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
