package toandoan.framgia.com.rxjavaretrofit.screen.mana;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.data.source.SourcesRepository;
import toandoan.framgia.com.rxjavaretrofit.data.source.local.SourceLocalDataSource;
import toandoan.framgia.com.rxjavaretrofit.data.source.local.sharedprf.SharedPrefsImpl;
import toandoan.framgia.com.rxjavaretrofit.data.source.remote.ManagaRemoteDataSource;
import toandoan.framgia.com.rxjavaretrofit.data.source.remote.api.service.AppServiceClient;
import toandoan.framgia.com.rxjavaretrofit.databinding.FragmentMangaBinding;
import toandoan.framgia.com.rxjavaretrofit.screen.BaseFragment;
import toandoan.framgia.com.rxjavaretrofit.utils.navigator.Navigator;

/**
 * Mana Screen.
 */
public class MangaFragment extends BaseFragment {

    public static final String BUND_MANGA_TYPE = "BUND_MANGA_TYPE";
    private MangaContract.ViewModel mViewModel;

    public static MangaFragment newInstance(String type) {
        MangaFragment mangaFragment = new MangaFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUND_MANGA_TYPE, type);
        mangaFragment.setArguments(bundle);
        return mangaFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        mViewModel = new MangaViewModel(new Navigator(this));
        Bundle bundle = this.getArguments();
        String type = bundle.getString(BUND_MANGA_TYPE);

        FragmentMangaBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_manga, container, false);
        binding.setViewModel((MangaViewModel) mViewModel);

        MangaContract.Presenter presenter = new MangaPresenter(mViewModel, type,
                new ManagaRemoteDataSource(AppServiceClient.getInstance()),
                new SourcesRepository(null,
                        new SourceLocalDataSource(new SharedPrefsImpl(getContext()))));
        mViewModel.setPresenter(presenter);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    public void onStop() {
        mViewModel.onStop();
        super.onStop();
    }
}
