package toandoan.framgia.com.rxjavaretrofit.screen.mana.allmanga;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.zakariya.stickyheaders.StickyHeaderLayoutManager;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.data.source.MangaDataRepository;
import toandoan.framgia.com.rxjavaretrofit.data.source.SourcesRepository;
import toandoan.framgia.com.rxjavaretrofit.data.source.local.SourceLocalDataSource;
import toandoan.framgia.com.rxjavaretrofit.data.source.local.sharedprf.SharedPrefsImpl;
import toandoan.framgia.com.rxjavaretrofit.data.source.remote.ManagaRemoteDataSource;
import toandoan.framgia.com.rxjavaretrofit.data.source.remote.api.service.AppServiceClient;
import toandoan.framgia.com.rxjavaretrofit.databinding.FragmentAllMangaBinding;
import toandoan.framgia.com.rxjavaretrofit.screen.BaseFragment;
import toandoan.framgia.com.rxjavaretrofit.utils.navigator.Navigator;

/**
 * AllManga Screen.
 */
public class AllMangaFragment extends BaseFragment {

    private AllMangaContract.ViewModel mViewModel;

    public static AllMangaFragment newInstance() {
        return new AllMangaFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        mViewModel = new AllMangaViewModel(new Navigator(this));

        AllMangaAdapter adapter = new AllMangaAdapter(mViewModel);

        AllMangaContract.Presenter presenter = new AllMangaPresenter(mViewModel,
                new MangaDataRepository(new ManagaRemoteDataSource(AppServiceClient.getInstance())),
                new SourcesRepository(null,
                        new SourceLocalDataSource(new SharedPrefsImpl(getContext()))));
        mViewModel.setPresenter(presenter);
        ((AllMangaViewModel)mViewModel).setAdapter(adapter);

        FragmentAllMangaBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_all_manga, container, false);
        binding.setViewModel((AllMangaViewModel) mViewModel);

        binding.recyclerManga.setLayoutManager(new StickyHeaderLayoutManager());
        binding.recyclerManga.setAdapter(adapter);
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
