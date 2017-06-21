package toandoan.framgia.com.rxjavaretrofit.screen.filter;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.MenuItem;
import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.BR;
import toandoan.framgia.com.rxjavaretrofit.data.model.FilterModel;

/**
 * Exposes the data to be used in the Filter screen.
 */

public class FilterViewModel extends BaseObservable implements FilterContract.ViewModel {

    private FilterContract.Presenter mPresenter;
    private MenuItem mMenuDone;
    private FilterAdapter mAdapter;

    public FilterViewModel() {
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(FilterContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onItemFilterClick(FilterModel filterModel) {
        filterModel.setSelected(!filterModel.isSelected());
        mMenuDone.setVisible(mAdapter.getSelectedFilter().size() != 0);
    }

    @Override
    public void onDoneClick() {

    }

    @Override
    public void onGetFilterModelSuccess(List<FilterModel> filterModels) {
        setAdapter(new FilterAdapter(this, filterModels));
    }

    public void setMenuDone(MenuItem menuDone) {
        mMenuDone = menuDone;
    }

    @Bindable
    public FilterAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(FilterAdapter adapter) {
        mAdapter = adapter;
        notifyPropertyChanged(BR.adapter);
    }
}
