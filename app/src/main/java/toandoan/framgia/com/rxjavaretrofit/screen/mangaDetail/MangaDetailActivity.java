package toandoan.framgia.com.rxjavaretrofit.screen.mangaDetail;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.data.model.Chap;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.data.source.DownloadRepository;
import toandoan.framgia.com.rxjavaretrofit.data.source.FavoriteRepository;
import toandoan.framgia.com.rxjavaretrofit.data.source.MangaDataRepository;
import toandoan.framgia.com.rxjavaretrofit.data.source.remote.ManagaRemoteDataSource;
import toandoan.framgia.com.rxjavaretrofit.data.source.remote.api.service.AppServiceClient;
import toandoan.framgia.com.rxjavaretrofit.databinding.ActivityMangaDetailBinding;
import toandoan.framgia.com.rxjavaretrofit.screen.BaseActivity;
import toandoan.framgia.com.rxjavaretrofit.utils.navigator.Navigator;

/**
 * MangaDetail Screen.
 */
public class MangaDetailActivity extends BaseActivity {

    public static final String EXTRA_MANGA_ID = "EXTRA_MANGA_ID";
    public static final String EXTRA_MANGA_CHAP = "EXTRA_MANGA_CHAP";

    private MangaDetailContract.ViewModel mViewModel;
    private Manga mManga;
    private Chap mCurrentChap;

    public static Intent getInstance(Context context, Manga manga, Chap currentChap) {
        return new Intent(context, MangaDetailActivity.class).putExtra(EXTRA_MANGA_ID, manga)
                .putExtra(EXTRA_MANGA_CHAP, currentChap);
    }

    public static Intent getInstance(Context context, Manga manga) {
        return new Intent(context, MangaDetailActivity.class).putExtra(EXTRA_MANGA_ID, manga);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDataIntent();

        mViewModel = new MangaDetailViewModel(this, new Navigator(this), mCurrentChap);

        MangaDetailContract.Presenter presenter = new MangaDetailPresenter(mViewModel,
                new MangaDataRepository(new ManagaRemoteDataSource(AppServiceClient.getInstance())),
                new FavoriteRepository(this), new DownloadRepository(this));
        mViewModel.setPresenter(presenter);
        presenter.getMangaDetail(mManga.getId());

        ActivityMangaDetailBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_manga_detail);
        binding.setViewModel((MangaDetailViewModel) mViewModel);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(mManga.getName());
    }

    private void getDataIntent() {
        if (getIntent() == null || getIntent().getExtras() == null) return;
        mManga = (Manga) getIntent().getExtras().getSerializable(EXTRA_MANGA_ID);
        mCurrentChap = (Chap) getIntent().getExtras().getSerializable(EXTRA_MANGA_CHAP);
        if (mManga == null) finish();
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
        getMenuInflater().inflate(R.menu.detail_manga_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.menu_download:
                if (mViewModel != null) {
                    mViewModel.onStartDownload();
                }
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
