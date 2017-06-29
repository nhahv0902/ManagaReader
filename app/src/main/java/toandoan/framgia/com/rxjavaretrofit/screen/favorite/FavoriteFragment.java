package toandoan.framgia.com.rxjavaretrofit.screen.favorite;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.databinding.FragmentFavoriteBinding;
import toandoan.framgia.com.rxjavaretrofit.screen.BaseFragment;

/**
 * Favorite Screen.
 */
public class FavoriteFragment extends BaseFragment {

    private FavoriteContract.ViewModel mViewModel;

    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new FavoriteViewModel();

        FavoriteContract.Presenter presenter = new FavoritePresenter(mViewModel);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        FragmentFavoriteBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false);
        binding.setViewModel((FavoriteViewModel) mViewModel);
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
