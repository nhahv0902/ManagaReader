package toandoan.framgia.com.rxjavaretrofit.screen.settings;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.databinding.FragmentSettingsBinding;
import toandoan.framgia.com.rxjavaretrofit.screen.BaseFragment;

/**
 * Settings Screen.
 */
public class SettingsFragment extends BaseFragment {

    private SettingsContract.ViewModel mViewModel;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new SettingsViewModel();

        SettingsContract.Presenter presenter = new SettingsPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        FragmentSettingsBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false);
        binding.setViewModel((SettingsViewModel) mViewModel);
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
