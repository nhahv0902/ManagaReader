package toandoan.framgia.com.rxjavaretrofit.screen.recentManga;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.data.source.local.RecentMangaRepository;
import toandoan.framgia.com.rxjavaretrofit.databinding.FragmentRecentMangaBinding;
import toandoan.framgia.com.rxjavaretrofit.screen.BaseFragment;
import toandoan.framgia.com.rxjavaretrofit.utils.widget.itemtouch.ItemTouchHelperExtension;

/**
 * RecentManga Screen.
 */
public class RecentMangaFragment extends BaseFragment {

    private RecentMangaContract.ViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private RecentMangaAdapter mAdapter;
    public ItemTouchHelperExtension mItemTouchHelper;
    public ItemTouchHelperExtension.Callback mCallback;

    public static RecentMangaFragment newInstance() {
        return new RecentMangaFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new RecentMangaViewModel(this);

        RecentMangaContract.Presenter presenter =
                new RecentMangaPresenter(mViewModel, new RecentMangaRepository(getContext()));
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        FragmentRecentMangaBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_recent_manga, container, false);
        binding.setViewModel((RecentMangaViewModel) mViewModel);

        mRecyclerView = binding.recyclerRecent;

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new RecentMangaAdapter((RecentMangaViewModel) mViewModel);
        mRecyclerView.setAdapter(mAdapter);
        mCallback = new ItemTouchHelperCallback();
        mItemTouchHelper = new ItemTouchHelperExtension(mCallback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
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

    public void onGetRecentMangaSuccess(List<Manga> mangas) {
        mAdapter.updateData(mangas);
    }

    public void onDeleteMangaClick(int pos) {
        mAdapter.deleteManga(pos);
    }

    public void deleteAllRecentManga() {
        mAdapter.clearData();
        Snackbar snack = Snackbar.make(getActivity().findViewById(android.R.id.content),
                R.string.title_delete_done, Snackbar.LENGTH_LONG)
                .setAction(R.string.action_undo, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewModel.onUndoDeleteClick();
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
                        mViewModel.onDeleteAllManga();
                    }
                }, 2000);
            }
        });

        snack.show();
    }
}
