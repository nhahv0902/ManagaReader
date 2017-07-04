package toandoan.framgia.com.rxjavaretrofit.screen.downloadmangak;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.data.source.DownloadRepository;
import toandoan.framgia.com.rxjavaretrofit.databinding.FragmentDownloadMangakBinding;
import toandoan.framgia.com.rxjavaretrofit.screen.BaseFragment;
import toandoan.framgia.com.rxjavaretrofit.utils.navigator.Navigator;

/**
 * DownloadMangak Screen.
 */
public class DownloadMangakFragment extends BaseFragment {

    private DownloadMangakContract.ViewModel mViewModel;

    public static DownloadMangakFragment newInstance() {
        return new DownloadMangakFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new DownloadMangakViewModel(new Navigator(this));

        DownloadMangakContract.Presenter presenter =
                new DownloadMangakPresenter(mViewModel, new DownloadRepository(getContext()));
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        FragmentDownloadMangakBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_download_mangak, container,
                        false);
        binding.setViewModel((DownloadMangakViewModel) mViewModel);
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

    public void removeAllMangakDownload() {
        if (((DownloadMangakViewModel) mViewModel).getAdapter().getItemCount() == 0) return;
        ((DownloadMangakViewModel) mViewModel).getAdapter().clearData();
        final boolean[] isDelete = new boolean[1];
        isDelete[0] = true;
        Snackbar snack = Snackbar.make(getActivity().findViewById(android.R.id.content),
                R.string.title_delete_mangak_download_done, Snackbar.LENGTH_LONG)
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
                            mViewModel.onDeleteAllMangakDownloaded();
                        }
                    }
                }, 2000);
            }
        });

        snack.show();
    }
}
