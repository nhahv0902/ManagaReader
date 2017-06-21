package toandoan.framgia.com.rxjavaretrofit.screen.filter;

import android.view.MenuItem;
import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.data.model.FilterModel;
import toandoan.framgia.com.rxjavaretrofit.screen.BasePresenter;
import toandoan.framgia.com.rxjavaretrofit.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface FilterContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onItemFilterClick(FilterModel filterModel);

        void onDoneClick();

        void onGetFilterModelSuccess(List<FilterModel> filterModels);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getFilterData();
    }
}
