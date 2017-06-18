package toandoan.framgia.com.rxjavaretrofit.screen.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.databinding.ActivityHomeBinding;
import toandoan.framgia.com.rxjavaretrofit.screen.BaseActivity;
import toandoan.framgia.com.rxjavaretrofit.utils.Utils;

/**
 * Home Screen.
 */
public class HomeActivity extends BaseActivity {

    private HomeContract.ViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new HomeViewModel(this);

        HomeContract.Presenter presenter = new HomePresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        ActivityHomeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        binding.setViewModel((HomeViewModel) mViewModel);
        Utils.disableShiftMode(binding.navigation);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    protected void onStop() {
        mViewModel.onStop();
        super.onStop();
    }
}
