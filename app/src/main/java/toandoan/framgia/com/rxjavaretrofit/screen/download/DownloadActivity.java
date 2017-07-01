package toandoan.framgia.com.rxjavaretrofit.screen.download;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
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

    public static Intent getIntent(Context context, Manga manga) {
        Intent intent = new Intent(context, DownloadActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_MANGA, manga);
        intent.putExtras(bundle);
        return intent;
    }

    public Manga getMangak() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) return null;
        return (Manga) bundle.getSerializable(EXTRA_MANGA);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Manga mangak = getMangak();
        mViewModel = new DownloadViewModel(mangak);

        DownloadContract.Presenter presenter = new DownloadPresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        ActivityDownloadBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_download);
        binding.setViewModel((DownloadViewModel) mViewModel);

        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setTitle(R.string.title_add_to_download);
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
