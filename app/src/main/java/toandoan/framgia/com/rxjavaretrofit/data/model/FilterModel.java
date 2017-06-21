package toandoan.framgia.com.rxjavaretrofit.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import toandoan.framgia.com.rxjavaretrofit.BR;

/**
 * Created by toand on 6/21/2017.
 */

public class FilterModel extends BaseObservable {
    private String mName;
    private boolean mIsSelected;

    public FilterModel(String name) {
        mName = name;
    }

    @Bindable
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public boolean isSelected() {
        return mIsSelected;
    }

    public void setSelected(boolean selected) {
        mIsSelected = selected;
        notifyPropertyChanged(BR.selected);
    }
}
