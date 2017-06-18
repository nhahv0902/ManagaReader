package toandoan.framgia.com.rxjavaretrofit.data.source.remote.api.service;

import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.data.model.Response;

/**
 * Created by le.quang.dao on 10/03/2017.
 */

public interface AppApi {
    @GET("getstoryoption")
    Observable<Response<List<Manga>>> getManga(@Query("version_app") String version,
            @Query("source") String source, @Query("limit") int limit,
            @Query("option") String option, @Query("page") int page);
}
