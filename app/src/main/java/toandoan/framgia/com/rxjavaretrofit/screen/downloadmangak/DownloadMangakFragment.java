package toandoan.framgia.com.rxjavaretrofit.screen.downloadmangak;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.data.source.DownloadRepository;
import toandoan.framgia.com.rxjavaretrofit.databinding.FragmentDownloadMangakBinding;
import toandoan.framgia.com.rxjavaretrofit.screen.BaseFragment;
import toandoan.framgia.com.rxjavaretrofit.utils.navigator.Navigator;

/**
 * DownloadMangak Screen.
 */
public class DownloadMangakFragment extends BaseFragment {

    private DownloadMangakContract.ViewModel mViewModel;

    public static DownloadMangakFragment newInstance() {
        return new DownloadMangakFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new DownloadMangakViewModel(new Navigator(this));

        DownloadMangakContract.Presenter presenter =
                new DownloadMangakPresenter(mViewModel, new DownloadRepository(getContext()));
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        FragmentDownloadMangakBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_download_mangak, container,
                        false);
        binding.setViewModel((DownloadMangakViewModel) mViewModel);
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
