package toandoan.framgia.com.rxjavaretrofit.screen.download;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.data.source.DownloadRepository;
import toandoan.framgia.com.rxjavaretrofit.data.source.MangaDataRepository;
import toandoan.framgia.com.rxjavaretrofit.data.source.remote.ManagaRemoteDataSource;
import toandoan.framgia.com.rxjavaretrofit.data.source.remote.api.service.AppServiceClient;
import toandoan.framgia.com.rxjavaretrofit.databinding.ActivityDownloadBinding;
import toandoan.framgia.com.rxjavaretrofit.screen.BaseActivity;

import static toandoan.framgia.com.rxjavaretrofit.screen.mangaDetail.mangachapter
        .MangaChapterFragment.EXTRA_MANGA;

/**
 * Download Screen.
 */
public class DownloadActivity extends BaseActivity {

    private DownloadContract.ViewModel mViewModel;
    private boolean mIsSelectAll;

    public static Intent getIntent(Context context, int idMangak) {
        Intent intent = new Intent(context, DownloadActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_MANGA, idMangak);
        intent.putExtras(bundle);
        return intent;
    }

    public int getMangak() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) return 0;
        return bundle.getInt(EXTRA_MANGA);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new DownloadViewModel(this, getMangak());

        DownloadContract.Presenter presenter = new DownloadPresenter(mViewModel,
                new MangaDataRepository(new ManagaRemoteDataSource(AppServiceClient.getInstance())),
                new DownloadRepository(this), getMangak());
        mViewModel.setPresenter(presenter);

        ActivityDownloadBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_download);
        binding.setViewModel((DownloadViewModel) mViewModel);

        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setTitle(R.string.title_add_to_download);
        loadAds();
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
        getMenuInflater().inflate(R.menu.download_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_select_all:
                if (mViewModel != null) {
                    if (mIsSelectAll) {
                        mViewModel.onUnSelectAll();
                        item.setTitle(R.string.action_select_all);
                    } else {
                        mViewModel.onSelectAll();
                        item.setTitle(R.string.action_un_select_all);
                    }
                    mIsSelectAll = !mIsSelectAll;
                }
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
