package toandoan.framgia.com.rxjavaretrofit.screen.favorite;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.data.source.FavoriteRepository;
import toandoan.framgia.com.rxjavaretrofit.databinding.FragmentFavoriteBinding;
import toandoan.framgia.com.rxjavaretrofit.screen.BaseFragment;
import toandoan.framgia.com.rxjavaretrofit.utils.navigator.Navigator;

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
        mViewModel = new FavoriteViewModel(new Navigator(this));

        FavoriteContract.Presenter presenter =
                new FavoritePresenter(mViewModel, new FavoriteRepository(getContext()));
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        FragmentFavoriteBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false);
        binding.setViewModel((FavoriteViewModel) mViewModel);
        loadAds(binding.getRoot());
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

    public void removeAllFavorite() {
        if (((FavoriteViewModel) mViewModel).getAdapter().getItemCount() == 0) return;
        ((FavoriteViewModel) mViewModel).getAdapter().clearData();
        final boolean[] isDelete = new boolean[1];
        isDelete[0] = true;
        Snackbar snack = Snackbar.make(getActivity().findViewById(android.R.id.content),
                R.string.title_delete_favorite_done, Snackbar.LENGTH_LONG)
                .setAction(R.string.action_undo, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewModel.onUndoDeleteClick();
                        isDelete[0] = false;
                    }
                })
                .setDuration(2000);

        snack.getView().addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (isDelete[0]) {
                            mViewModel.onDeleteAllFavoriteManga();
                        }
                    }
                }, 2000);
            }
        });

        snack.show();
    }
}
