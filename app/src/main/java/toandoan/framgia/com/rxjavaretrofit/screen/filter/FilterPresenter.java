package toandoan.framgia.com.rxjavaretrofit.screen.filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import toandoan.framgia.com.rxjavaretrofit.data.model.FilterModel;

/**
 * Listens to user actions from the UI ({@link FilterActivity}), retrieves the data and updates
 * the UI as required.
 */
final class FilterPresenter implements FilterContract.Presenter {
    private static final String TAG = FilterPresenter.class.getName();

    private final static String FILTER_VALUES =
            "Completed, Action, Adventure, Adult, Comedy, Cooking, Drama, Fantasy, Josei, Martial"
                    + " Arts, Mature, Mecha, Mystery, Music, One Shot, Psychological, Romance, "
                    + "Sci-Fi, Seinen, Shoujo, School Life, Shounen, Slice Of Life, Sports, "
                    + "Supernatural, Echo, Shounen Ai, Shoujo Ai, Smut, Tragedy, Webtoon, Yaoi, "
                    + "Yuri, 4 Koma, Doujunshi";

    private final FilterContract.ViewModel mViewModel;

    public FilterPresenter(FilterContract.ViewModel viewModel) {
        mViewModel = viewModel;
        getFilterData();
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void getFilterData() {
        List<FilterModel> filterModels = new ArrayList<>();
        String[] filters = FILTER_VALUES.split(",");
        for (String s : filters) {
            s = s.trim();
            filterModels.add(new FilterModel(s.trim()));
        }
        Collections.sort(filterModels, new Comparator<FilterModel>() {
            @Override
            public int compare(FilterModel o1, FilterModel o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        mViewModel.onGetFilterModelSuccess(filterModels);
    }
}
