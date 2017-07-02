package toandoan.framgia.com.rxjavaretrofit.screen.title;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import toandoan.framgia.com.rxjavaretrofit.BR;

/**
 * Exposes the data to be used in the Title screen.
 */

public class TitleViewModel extends BaseObservable implements TitleContract.ViewModel {

    private TitleContract.Presenter mPresenter;
    private String mDescription;

    public TitleViewModel(String description) {
        mDescription = description;
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
    public void setPresenter(TitleContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Bindable
    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
        notifyPropertyChanged(BR.description);
    }
}
