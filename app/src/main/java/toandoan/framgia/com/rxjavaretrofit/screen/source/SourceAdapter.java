package toandoan.framgia.com.rxjavaretrofit.screen.source;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import org.zakariya.stickyheaders.SectioningAdapter;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.data.model.Source;

/**
 * Created by framgia on 19/06/2017.
 */

public class SourceAdapter extends SectioningAdapter {
    private List<SourceAdapterModel> mModels = new ArrayList<>();
    private LayoutInflater mInflater;

    public void updateData(List<Source> sources) {
        if (sources == null) return;
        for (Source source : sources) {
            if (isExitNationSource(source)) continue;
            SourceAdapterModel sourceAdapterModel = new SourceAdapterModel();
            sourceAdapterModel.setHeaderTitle(source.getNation());
            sourceAdapterModel.getSources().add(source);
            mModels.add(sourceAdapterModel);
        }

        notifyAllSectionsDataSetChanged();
    }

    public Source getSelectedItem() {
        for (SourceAdapterModel sourceAdapterModel : mModels) {
            for (Source source : sourceAdapterModel.getSources()) {
                if (source.isSelected()) {
                    return source;
                }
            }
        }
        return null;
    }

    public boolean isExitNationSource(Source source) {
        for (SourceAdapterModel sourceAdapterModel : mModels) {
            if (sourceAdapterModel.getHeaderTitle().equals(source.getNation())) {
                sourceAdapterModel.getSources().add(source);
                return true;
            }
        }
        return false;
    }

    @Override
    public SectioningAdapter.ItemViewHolder onCreateItemViewHolder(ViewGroup parent,
            int itemUserType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        View v = mInflater.inflate(R.layout.item_source, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public SectioningAdapter.HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent,
            int headerUserType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        View v = mInflater.inflate(R.layout.item_source_header, parent, false);
        return new HeaderViewHolder(v);
    }

    @Override
    public void onBindItemViewHolder(SectioningAdapter.ItemViewHolder viewHolder, int sectionIndex,
            int itemIndex, int itemUserType) {
        SourceAdapterModel sourceModel = mModels.get(sectionIndex);
        Source source = sourceModel.getSources().get(itemIndex);
        ((ItemViewHolder) viewHolder).bindData(source);
    }

    @Override
    public void onBindHeaderViewHolder(SectioningAdapter.HeaderViewHolder viewHolder,
            int sectionIndex, int headerUserType) {
        SourceAdapterModel sourceModel = mModels.get(sectionIndex);
        ((HeaderViewHolder) viewHolder).bindData(sourceModel.getHeaderTitle());
    }

    @Override
    public int getNumberOfSections() {
        return mModels != null ? mModels.size() : 0;
    }

    @Override
    public int getNumberOfItemsInSection(int sectionIndex) {
        return mModels.get(sectionIndex).getSources().size();
    }

    @Override
    public boolean doesSectionHaveHeader(int sectionIndex) {
        return true;
    }

    @Override
    public boolean doesSectionHaveFooter(int sectionIndex) {
        return false;
    }

    public class ItemViewHolder extends SectioningAdapter.ItemViewHolder
            implements View.OnClickListener {
        private TextView mTextSourceName;
        private ImageView mImageSelected;

        public ItemViewHolder(View view) {
            super(view);
            mTextSourceName = (TextView) view.findViewById(R.id.text_source_name);
            mImageSelected = (ImageView) view.findViewById(R.id.image_selected);
            view.setOnClickListener(this);
        }

        public void bindData(Source source) {
            if (source == null) return;
            String name = source.getName();
            if (TextUtils.isEmpty(name)) return;
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            mTextSourceName.setText(name);
            mImageSelected.setVisibility(source.isSelected() ? View.VISIBLE : View.GONE);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            int section = getSectionForAdapterPosition(adapterPosition);
            int item = getPositionOfItemInSection(section, adapterPosition);
            invisibleOtherCheck();
            Source source = mModels.get(section).getSources().get(item);
            source.setSelected(true);
            notifyAllSectionsDataSetChanged();
        }
    }

    private void invisibleOtherCheck() {
        for (SourceAdapterModel sourceAdapterModel : mModels) {
            for (Source source : sourceAdapterModel.getSources()) {
                if (source.isSelected()) {
                    source.setSelected(false);
                    break;
                }
            }
        }
        notifyAllSectionsDataSetChanged();
    }

    public class HeaderViewHolder extends SectioningAdapter.HeaderViewHolder {
        private TextView mTextHeader;

        public HeaderViewHolder(View v) {
            super(v);
            mTextHeader = (TextView) v.findViewById(R.id.text_header);
        }

        public void bindData(String header) {
            if (header == null) return;
            mTextHeader.setText(header);
        }
    }

    public class SourceAdapterModel {
        private String mHeaderTitle;
        private List<Source> mSources = new ArrayList<>();

        public String getHeaderTitle() {
            return mHeaderTitle;
        }

        public void setHeaderTitle(String headerTitle) {
            mHeaderTitle = headerTitle;
        }

        public List<Source> getSources() {
            return mSources;
        }

        public void setSources(List<Source> sources) {
            mSources = sources;
        }
    }
}
