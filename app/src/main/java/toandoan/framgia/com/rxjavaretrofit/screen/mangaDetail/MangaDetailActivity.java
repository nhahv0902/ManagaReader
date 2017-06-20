package toandoan.framgia.com.rxjavaretrofit.screen.mangaDetail;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
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

    private MangaDetailContract.ViewModel mViewModel;
    private Manga mManga;

    public static Intent getInstance(Context context, Manga manga) {
        return new Intent(context, MangaDetailActivity.class).putExtra(EXTRA_MANGA_ID, manga);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDataIntent();

        mViewModel = new MangaDetailViewModel(this, new Navigator(this));

        MangaDetailContract.Presenter presenter = new MangaDetailPresenter(mViewModel,
                new MangaDataRepository(
                        new ManagaRemoteDataSource(AppServiceClient.getInstance())));
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
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
