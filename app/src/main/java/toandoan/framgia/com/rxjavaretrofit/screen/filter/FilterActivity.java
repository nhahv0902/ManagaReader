package toandoan.framgia.com.rxjavaretrofit.screen.filter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.databinding.ActivityFilterBinding;
import toandoan.framgia.com.rxjavaretrofit.screen.BaseActivity;

/**
 * Filter Screen.
 */
public class FilterActivity extends BaseActivity {

    private FilterContract.ViewModel mViewModel;

    public static Intent getInstance(Context context) {
        return new Intent(context, FilterActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new FilterViewModel();

        FilterContract.Presenter presenter = new FilterPresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        ActivityFilterBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_filter);
        binding.setViewModel((FilterViewModel) mViewModel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.filter_menu, menu);
        MenuItem menuDone = menu.findItem(R.id.menu_done);
        ((FilterViewModel) mViewModel).setMenuDone(menuDone);
        menuDone.setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_done:
                mViewModel.onDoneClick();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
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
