package toandoan.framgia.com.rxjavaretrofit.screen.downloading;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.MenuItem;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.databinding.ActivityDownloadingBinding;
import toandoan.framgia.com.rxjavaretrofit.screen.BaseActivity;

/**
 * Downloading Screen.
 */
public class DownloadingActivity extends BaseActivity {

    private DownloadingContract.ViewModel mViewModel;

    public static Intent getIntent(Context context) {
        return new Intent(context, DownloadingActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new DownloadingViewModel();

        DownloadingContract.Presenter presenter = new DownloadingPresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        ActivityDownloadingBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_downloading);
        binding.setViewModel((DownloadingViewModel) mViewModel);
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setTitle(R.string.title_download);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
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
