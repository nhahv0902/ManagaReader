package toandoan.framgia.com.rxjavaretrofit.screen.recentManga;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
}
