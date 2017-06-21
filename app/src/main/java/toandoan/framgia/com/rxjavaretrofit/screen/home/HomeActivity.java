package toandoan.framgia.com.rxjavaretrofit.screen.home;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.IntentCompat;
import android.view.Menu;
import android.view.MenuItem;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.databinding.ActivityHomeBinding;
import toandoan.framgia.com.rxjavaretrofit.screen.BaseActivity;
import toandoan.framgia.com.rxjavaretrofit.utils.Utils;

/**
 * Home Screen.
 */
public class HomeActivity extends BaseActivity {

    private HomeContract.ViewModel mViewModel;

    public static Intent getInstance(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_manga_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                break;
            case R.id.menu_filter:
                mViewModel.onFilterClick();
                break;
            case R.id.menu_source:
                mViewModel.onMenuSourceClick();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
