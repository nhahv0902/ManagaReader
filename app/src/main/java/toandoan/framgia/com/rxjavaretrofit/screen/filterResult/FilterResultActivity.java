package toandoan.framgia.com.rxjavaretrofit.screen.filterResult;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.MenuItem;
import java.util.ArrayList;
import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.databinding.ActivityFilterResultBinding;
import toandoan.framgia.com.rxjavaretrofit.screen.BaseActivity;
import toandoan.framgia.com.rxjavaretrofit.screen.mana.MangaFragment;
import toandoan.framgia.com.rxjavaretrofit.utils.navigator.Navigator;

/**
 * FilterResult Screen.
 */
public class FilterResultActivity extends BaseActivity {

    private static final String EXTRA_GENRES = "EXTRA_GENRES";

    private FilterResultContract.ViewModel mViewModel;

    public static Intent getInstance(Context context, List<String> genres) {
        return new Intent(context, FilterResultActivity.class).putStringArrayListExtra(EXTRA_GENRES,
                (ArrayList<String>) genres);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new FilterResultViewModel();
        List<String> genres = getIntent().getStringArrayListExtra(EXTRA_GENRES);
        MangaFragment mangaFragment = MangaFragment.newInstance(genres);

        FilterResultContract.Presenter presenter = new FilterResultPresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        ActivityFilterResultBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_filter_result);
        binding.setViewModel((FilterResultViewModel) mViewModel);

        new Navigator(this).replaceFragment(R.id.frame_content, mangaFragment);
        setTitle(R.string.manga_filter_result);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        if (item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
