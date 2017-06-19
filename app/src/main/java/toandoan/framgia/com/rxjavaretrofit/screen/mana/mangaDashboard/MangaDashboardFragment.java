package toandoan.framgia.com.rxjavaretrofit.screen.mana.mangaDashboard;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.databinding.FragmentMangaDashboardBinding;
import toandoan.framgia.com.rxjavaretrofit.screen.BaseFragment;

/**
 * MangaDashboard Screen.
 */
public class MangaDashboardFragment extends BaseFragment {

    private MangaDashboardContract.ViewModel mViewModel;

    public static MangaDashboardFragment newInstance() {
        return new MangaDashboardFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new MangaDashboardViewModel(this);

        MangaDashboardContract.Presenter presenter = new MangaDashboardPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        FragmentMangaDashboardBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_manga_dashboard, container,
                        false);
        binding.setViewModel((MangaDashboardViewModel) mViewModel);
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
