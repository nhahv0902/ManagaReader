package toandoan.framgia.com.rxjavaretrofit.screen.recentManga;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.utils.widget.itemtouch.Extension;
import toandoan.framgia.com.rxjavaretrofit.utils.widget.itemtouch.ItemTouchHelperExtension;

/**
 * Created by toand on 6/25/2017.
 */

public class RecentMangaAdapter extends RecyclerView.Adapter<RecentMangaAdapter.ViewHolder> {
    private List<Manga> mDatas;
    private Context mContext;
    private ItemTouchHelperExtension mItemTouchHelperExtension;

    public RecentMangaAdapter(Context context) {
        mDatas = new ArrayList<>();
        mContext = context;
    }

    public void setDatas(List<Manga> datas) {
        mDatas.clear();
        mDatas.addAll(datas);
    }

    public void updateData(List<Manga> datas) {
        setDatas(datas);
        notifyDataSetChanged();
    }

    public void setItemTouchHelperExtension(ItemTouchHelperExtension itemTouchHelperExtension) {
        mItemTouchHelperExtension = itemTouchHelperExtension;
    }

    private LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(mContext);
    }

    @Override
    public RecentMangaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = getLayoutInflater().inflate(R.layout.item_recent_manga, parent, false);
        return new ViewHolder(view);
    }

    public void move(int from, int to) {
        Manga prev = mDatas.remove(from);
        mDatas.add(to > from ? to - 1 : to, prev);
        notifyItemMoved(from, to);
    }

    @Override
    public void onBindViewHolder(final RecentMangaAdapter.ViewHolder holder, int position) {
        holder.bind(null);
        holder.mActionViewDelete.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View view) {
                                                            doDelete(holder.getAdapterPosition());
                                                        }
                                                    }

        );
    }

    private void doDelete(int adapterPosition) {
        mDatas.remove(adapterPosition);
        notifyItemRemoved(adapterPosition);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements Extension {
        View mViewContent;
        View mActionContainer;
        View mActionViewDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            mViewContent = itemView.findViewById(R.id.view_content);
            mActionContainer = itemView.findViewById(R.id.view_list_repo_action_container);
            mActionViewDelete = itemView.findViewById(R.id.view_list_repo_action_delete);
        }

        public void bind(Manga testModel) {

        }

        @Override
        public float getActionWidth() {
            return mActionContainer.getWidth();
        }
    }
}