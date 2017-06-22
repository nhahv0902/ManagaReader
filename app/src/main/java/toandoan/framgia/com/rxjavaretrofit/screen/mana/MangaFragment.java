package toandoan.framgia.com.rxjavaretrofit.screen.mana;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.data.source.SourcesRepository;
import toandoan.framgia.com.rxjavaretrofit.data.source.local.SourceLocalDataSource;
import toandoan.framgia.com.rxjavaretrofit.data.source.local.sharedprf.SharedPrefsImpl;
import toandoan.framgia.com.rxjavaretrofit.data.source.remote.ManagaRemoteDataSource;
import toandoan.framgia.com.rxjavaretrofit.data.source.remote.api.service.AppServiceClient;
import toandoan.framgia.com.rxjavaretrofit.databinding.FragmentMangaBinding;
import toandoan.framgia.com.rxjavaretrofit.screen.BaseFragment;
import toandoan.framgia.com.rxjavaretrofit.utils.navigator.Navigator;

import static toandoan.framgia.com.rxjavaretrofit.screen.mana.MangaFragment.TypeFragmentManga
        .GENRES;
import static toandoan.framgia.com.rxjavaretrofit.screen.mana.MangaFragment.TypeFragmentManga.OTHER;

/**
 * Mana Screen.
 */
public class MangaFragment extends BaseFragment {

    public static final String BUND_MANGA_TYPE = "BUND_MANGA_TYPE";
    public static final String BUND_FRAGMENT_TYPE = "BUND_FRAGMENT_TYPE";
    public static final String BUND_MANGA_GENRE = "BUND_MANGA_GENRE";
    private MangaContract.ViewModel mViewModel;
    private int mTypeFragment;
    private String mType;
    private List<String> mGenres;

    public static MangaFragment newInstance(String mangaType) {
        MangaFragment mangaFragment = new MangaFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BUND_FRAGMENT_TYPE, OTHER);
        bundle.putString(BUND_MANGA_TYPE, mangaType);
        mangaFragment.setArguments(bundle);
        return mangaFragment;
    }

    public static MangaFragment newInstance(List<String> genres) {
        MangaFragment mangaFragment = new MangaFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BUND_FRAGMENT_TYPE, GENRES);
        bundle.putStringArrayList(BUND_MANGA_GENRE, (ArrayList<String>) genres);
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
        genIntentData();

        FragmentMangaBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_manga, container, false);
        binding.setViewModel((MangaViewModel) mViewModel);

        MangaContract.Presenter presenter =
                new MangaPresenter(mViewModel, mTypeFragment, mType, mGenres,
                        new ManagaRemoteDataSource(AppServiceClient.getInstance()),
                        new SourcesRepository(null,
                                new SourceLocalDataSource(new SharedPrefsImpl(getContext()))));
        mViewModel.setPresenter(presenter);
        return binding.getRoot();
    }

    private void genIntentData() {
        Bundle bundle = this.getArguments();

        mTypeFragment = bundle.getInt(BUND_FRAGMENT_TYPE);

        if (mTypeFragment == GENRES) {
            mGenres = bundle.getStringArrayList(BUND_MANGA_GENRE);
        } else {
            mType = bundle.getString(BUND_MANGA_TYPE);
        }
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

    @IntDef({ GENRES, OTHER })
    @interface TypeFragmentManga {
        int GENRES = 0;
        int OTHER = 1;
    }
}
