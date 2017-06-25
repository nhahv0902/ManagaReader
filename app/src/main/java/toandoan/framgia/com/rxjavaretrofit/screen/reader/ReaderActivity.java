package toandoan.framgia.com.rxjavaretrofit.screen.reader;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.data.model.Chap;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.data.source.MangaDataRepository;
import toandoan.framgia.com.rxjavaretrofit.data.source.local.RecentMangaRepository;
import toandoan.framgia.com.rxjavaretrofit.data.source.remote.ManagaRemoteDataSource;
import toandoan.framgia.com.rxjavaretrofit.data.source.remote.api.service.AppServiceClient;
import toandoan.framgia.com.rxjavaretrofit.databinding.ActivityReaderBinding;
import toandoan.framgia.com.rxjavaretrofit.screen.BaseActivity;
import toandoan.framgia.com.rxjavaretrofit.utils.navigator.Navigator;

/**
 * Reader Screen.
 */
public class ReaderActivity extends BaseActivity {
    private static final String EXTRA_CHAP = "EXTRA_CHAP";
    private static final String EXTRA_MANGA = "EXTRA_MANGA";
    private static final String EXTRA_CHAP_POS = "EXTRA_CHAP_POS";
    private ReaderContract.ViewModel mViewModel;

    private Chap mChap;
    private Manga mManga;
    private int mChapPos;

    public static Intent getInstance(Context context, Manga manga, Chap chap, int chapPosition) {
        return new Intent(context, ReaderActivity.class).putExtra(EXTRA_CHAP, chap)
                .putExtra(EXTRA_MANGA, manga)
                .putExtra(EXTRA_CHAP_POS, chapPosition);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDataIntent();

        mViewModel = new ReaderViewModel(new Navigator(this), mManga, mChapPos);

        ReaderContract.Presenter presenter = new ReaderPresenter(mViewModel, mManga, mChap,
                new MangaDataRepository(new ManagaRemoteDataSource(AppServiceClient.getInstance())),
                new RecentMangaRepository(this));

        mViewModel.setPresenter(presenter);

        ActivityReaderBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_reader);
        binding.setViewModel((ReaderViewModel) mViewModel);

        getSupportActionBar().hide();
        ((ReaderViewModel) mViewModel).setRecyclerReader(binding.recyclerReader);
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

    private void getDataIntent() {
        if (getIntent() == null || getIntent().getExtras() == null) return;
        mChap = (Chap) getIntent().getExtras().getSerializable(EXTRA_CHAP);
        mManga = (Manga) getIntent().getExtras().getSerializable(EXTRA_MANGA);
        mChapPos = getIntent().getExtras().getInt(EXTRA_CHAP_POS);
    }
}
