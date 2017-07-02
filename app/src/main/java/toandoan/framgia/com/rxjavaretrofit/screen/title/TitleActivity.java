package toandoan.framgia.com.rxjavaretrofit.screen.title;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.MenuItem;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.databinding.ActivityTitleBinding;
import toandoan.framgia.com.rxjavaretrofit.screen.BaseActivity;

/**
 * Title Screen.
 */
public class TitleActivity extends BaseActivity {
    private final static String EXTRA_TITLE = "EXTRA_TITLE";
    private final static String EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION";

    public static Intent getInstance(Context context, int title, int description) {
        return new Intent(context, TitleActivity.class).putExtra(EXTRA_TITLE, title)
                .putExtra(EXTRA_DESCRIPTION, description);
    }

    private TitleContract.ViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new TitleViewModel(getDataIntent());

        TitleContract.Presenter presenter = new TitlePresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        ActivityTitleBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_title);
        binding.setViewModel((TitleViewModel) mViewModel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private String getDataIntent() {
        if (getIntent() == null || getIntent().getExtras() == null) return null;
        int title = getIntent().getExtras().getInt(EXTRA_TITLE);
        setTitle(title);
        int description = getIntent().getExtras().getInt(EXTRA_DESCRIPTION);
        return getString(description);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
