package toandoan.framgia.com.rxjavaretrofit.data.source;

import java.util.List;
import rx.Observable;
import toandoan.framgia.com.rxjavaretrofit.data.model.Source;

/**
 * Created by framgia on 19/06/2017.
 */

public class SourcesRepository implements SourcesDataSource {
    private SourcesDataSource mRemoteDataSource;
    private SourcesDataSource mLocalDataSource;

    public SourcesRepository(SourcesDataSource remoteDataSource,
            SourcesDataSource localDataSource) {
        mRemoteDataSource = remoteDataSource;
        mLocalDataSource = localDataSource;
    }

    @Override
    public Observable<List<Source>> getSources() {
        return mRemoteDataSource.getSources();
    }

    @Override
    public Observable<Source> getSelectedSources() {
        return mLocalDataSource.getSelectedSources();
    }

    @Override
    public Observable<Boolean> saveCurrentSource(Source source) {
        return mLocalDataSource.saveCurrentSource(source);
    }
}
