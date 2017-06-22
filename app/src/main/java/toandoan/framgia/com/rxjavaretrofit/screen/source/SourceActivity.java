package toandoan.framgia.com.rxjavaretrofit.screen.source;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import org.zakariya.stickyheaders.StickyHeaderLayoutManager;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.data.source.SourcesRepository;
import toandoan.framgia.com.rxjavaretrofit.data.source.local.SourceLocalDataSource;
import toandoan.framgia.com.rxjavaretrofit.data.source.local.sharedprf.SharedPrefsImpl;
import toandoan.framgia.com.rxjavaretrofit.data.source.remote.SourcesRemoteDataSource;
import toandoan.framgia.com.rxjavaretrofit.data.source.remote.api.service.AppServiceClient;
import toandoan.framgia.com.rxjavaretrofit.databinding.ActivitySourceBinding;
import toandoan.framgia.com.rxjavaretrofit.screen.BaseActivity;
import toandoan.framgia.com.rxjavaretrofit.utils.navigator.Navigator;

/**
 * Source Screen.
 */
public class SourceActivity extends BaseActivity {

    public static final String EXTRA_IS_SHOW_FROM_MAIN = "EXTRA_IS_SHOW_FROM_MAIN";
    private SourceContract.ViewModel mViewModel;
    private boolean mIsShowFromMain;

    public static Intent getInstance(Context context, boolean isShowFromMain) {
        return new Intent(context, SourceActivity.class).putExtra(EXTRA_IS_SHOW_FROM_MAIN,
                isShowFromMain);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentData();

        mViewModel = new SourceViewModel(new Navigator(this), mIsShowFromMain);

        SourceContract.Presenter presenter = new SourcePresenter(mViewModel,
                new SourcesRepository(new SourcesRemoteDataSource(AppServiceClient.getInstance()),
                        new SourceLocalDataSource(new SharedPrefsImpl(this))));
        mViewModel.setPresenter(presenter);

        ActivitySourceBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_source);

        RecyclerView recyclerView = binding.recyclerSource;
        recyclerView.setLayoutManager(new StickyHeaderLayoutManager());
        SourceAdapter adapter = new SourceAdapter((SourceViewModel) mViewModel);
        recyclerView.setAdapter(adapter);
        ((SourceViewModel) mViewModel).setAdapter(adapter);

        binding.setViewModel((SourceViewModel) mViewModel);
        setTitle(R.string.title_source);

        getSupportActionBar().setDisplayHomeAsUpEnabled(mIsShowFromMain);
    }

    private void getIntentData() {
        if (getIntent() == null || getIntent().getExtras() == null) return;
        mIsShowFromMain = getIntent().getExtras().getBoolean(EXTRA_IS_SHOW_FROM_MAIN);
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
        getMenuInflater().inflate(R.menu.source_menu, menu);
        MenuItem menuDone = menu.findItem(R.id.menu_done);
        menuDone.setVisible(false);
        ((SourceViewModel) mViewModel).setMenuDone(menuDone);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_done:
                mViewModel.onDoneClick();
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
