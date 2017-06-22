package toandoan.framgia.com.rxjavaretrofit.screen.mana.allmanga;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import org.zakariya.stickyheaders.SectioningAdapter;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;

/**
 * Created by framgia on 19/06/2017.
 */

public class AllMangaAdapter extends SectioningAdapter {
    private List<MangaModel> mModels = new ArrayList<>();
    private LayoutInflater mInflater;
    private AllMangaContract.ViewModel mViewModel;

    public AllMangaAdapter(AllMangaContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    public void updateData(List<MangaModel> mangas) {
        if (mangas == null) return;
        mModels.clear();
        mModels.addAll(mangas);
        notifyAllSectionsDataSetChanged();
    }

    public void clear() {
        mModels.clear();
        notifyAllSectionsDataSetChanged();
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
        MangaModel sourceModel = mModels.get(sectionIndex);
        Manga manga = sourceModel.getMangas().get(itemIndex);
        ((ItemViewHolder) viewHolder).bindData(manga);
    }

    @Override
    public void onBindHeaderViewHolder(SectioningAdapter.HeaderViewHolder viewHolder,
            int sectionIndex, int headerUserType) {
        MangaModel sourceModel = mModels.get(sectionIndex);
        ((HeaderViewHolder) viewHolder).bindData(sourceModel.getFirstChar());
    }

    @Override
    public int getNumberOfSections() {
        return mModels != null ? mModels.size() : 0;
    }

    @Override
    public int getNumberOfItemsInSection(int sectionIndex) {
        return mModels.get(sectionIndex).getMangas().size();
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

        public ItemViewHolder(View view) {
            super(view);
            mTextSourceName = (TextView) view.findViewById(R.id.text_source_name);
            view.setOnClickListener(this);
        }

        public void bindData(Manga manga) {
            if (manga == null) return;
            String name = manga.getName();
            if (TextUtils.isEmpty(name)) return;
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            mTextSourceName.setText(name);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            int section = getSectionForAdapterPosition(adapterPosition);
            int item = getPositionOfItemInSection(section, adapterPosition);
            Manga source = mModels.get(section).getMangas().get(item);
            mViewModel.onItemClick(source);
        }
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

    public static class MangaModel {
        private String mFirstChar;
        private List<Manga> mMangas = new ArrayList<>();

        public String getFirstChar() {
            return mFirstChar;
        }

        public void setFirstChar(String firstChar) {
            mFirstChar = firstChar;
        }

        public List<Manga> getMangas() {
            return mMangas;
        }

        public void setMangas(List<Manga> mangas) {
            mMangas = mangas;
        }
    }
}
