package toandoan.framgia.com.rxjavaretrofit.screen.filter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.data.model.FilterModel;
import toandoan.framgia.com.rxjavaretrofit.databinding.ItemFilterBinding;

/**
 * Created by toand on 6/21/2017.
 */

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.ViewHolder> {
    private List<FilterModel> mModels;
    private LayoutInflater mInflater;
    private FilterViewModel mViewModel;

    public FilterAdapter(FilterViewModel viewModel, List<FilterModel> models) {
        mModels = models;
        mViewModel = viewModel;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        ItemFilterBinding binding =
                DataBindingUtil.inflate(mInflater, R.layout.item_filter, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(mModels.get(position));
    }

    public List<String> getSelectedFilter() {
        List<String> strings = new ArrayList<>();
        for (FilterModel model : mModels) {
            if (model.isSelected()) {
                strings.add(model.getName());
            }
        }
        return strings;
    }

    @Override
    public int getItemCount() {
        return mModels != null ? mModels.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemFilterBinding mBinding;

        public ViewHolder(ItemFilterBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bindData(FilterModel filterModel) {
            mBinding.setFilter(filterModel);
            mBinding.setViewModel(mViewModel);
            mBinding.executePendingBindings();
        }
    }
}
