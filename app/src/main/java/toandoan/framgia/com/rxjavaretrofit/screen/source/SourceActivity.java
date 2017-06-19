package toandoan.framgia.com.rxjavaretrofit.screen.source;

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

    private SourceContract.ViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new SourceViewModel(new Navigator(this));

        SourceContract.Presenter presenter = new SourcePresenter(mViewModel,
                new SourcesRepository(new SourcesRemoteDataSource(AppServiceClient.getInstance()),
                        new SourceLocalDataSource(new SharedPrefsImpl(this))));
        mViewModel.setPresenter(presenter);

        ActivitySourceBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_source);

        RecyclerView recyclerView = binding.recyclerSource;
        recyclerView.setLayoutManager(new StickyHeaderLayoutManager());
        SourceAdapter adapter = new SourceAdapter();
        recyclerView.setAdapter(adapter);
        ((SourceViewModel) mViewModel).setAdapter(adapter);

        binding.setViewModel((SourceViewModel) mViewModel);
        setTitle(R.string.title_source);
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
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_done:
                mViewModel.onDoneClick();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}