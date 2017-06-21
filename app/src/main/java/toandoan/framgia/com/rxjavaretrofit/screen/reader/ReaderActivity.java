package toandoan.framgia.com.rxjavaretrofit.screen.reader;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.data.model.Chap;
import toandoan.framgia.com.rxjavaretrofit.data.source.MangaDataRepository;
import toandoan.framgia.com.rxjavaretrofit.data.source.remote.ManagaRemoteDataSource;
import toandoan.framgia.com.rxjavaretrofit.data.source.remote.api.service.AppServiceClient;
import toandoan.framgia.com.rxjavaretrofit.databinding.ActivityReaderBinding;
import toandoan.framgia.com.rxjavaretrofit.screen.BaseActivity;
import toandoan.framgia.com.rxjavaretrofit.screen.mangaDetail.mangachapter.MangaChapterViewModel;
import toandoan.framgia.com.rxjavaretrofit.utils.navigator.Navigator;

/**
 * Reader Screen.
 */
public class ReaderActivity extends BaseActivity {
    private static final String EXTRA_CHAP = "EXTRA_CHAP";
    private ReaderContract.ViewModel mViewModel;

    private Chap mChap;

    public static Intent getInstance(Context context, Chap chap) {
        return new Intent(context, ReaderActivity.class).putExtra(EXTRA_CHAP, chap);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDataIntent();

        mViewModel = new ReaderViewModel(new Navigator(this));

        ReaderContract.Presenter presenter = new ReaderPresenter(mViewModel, mChap,
                new MangaDataRepository(
                        new ManagaRemoteDataSource(AppServiceClient.getInstance())));
        mViewModel.setPresenter(presenter);

        ActivityReaderBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_reader);
        binding.setViewModel((ReaderViewModel) mViewModel);
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
    }
}
