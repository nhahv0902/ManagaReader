package toandoan.framgia.com.rxjavaretrofit.data.source;

import java.util.List;
import rx.Observable;
import toandoan.framgia.com.rxjavaretrofit.data.model.Source;

/**
 * Created by framgia on 19/06/2017.
 */

public interface SourcesDataSource {
    Observable<List<Source>> getSources();

    Observable<Source> getSelectedSources();

    Observable<Boolean> saveCurrentSource(Source source);

    String getCurrentSourceCode();
}
