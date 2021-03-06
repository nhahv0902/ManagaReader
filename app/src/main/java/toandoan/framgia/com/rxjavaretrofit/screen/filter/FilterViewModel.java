package toandoan.framgia.com.rxjavaretrofit.screen.filter;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.MenuItem;
import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.BR;
import toandoan.framgia.com.rxjavaretrofit.data.model.FilterModel;
import toandoan.framgia.com.rxjavaretrofit.screen.filterResult.FilterResultActivity;
import toandoan.framgia.com.rxjavaretrofit.utils.navigator.Navigator;

/**
 * Exposes the data to be used in the Filter screen.
 */

public class FilterViewModel extends BaseObservable implements FilterContract.ViewModel {

    private FilterContract.Presenter mPresenter;
    private MenuItem mMenuDone;
    private FilterAdapter mAdapter;
    private Navigator mNavigator;

    public FilterViewModel(Navigator navigator) {
        mNavigator = navigator;
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
        List<String> genres = mAdapter.getSelectedFilter();
        if (genres == null || genres.size() == 0) return;
        mNavigator.startActivity(FilterResultActivity.getInstance(mNavigator.getContext(), genres));
        mNavigator.finishActivity();
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
