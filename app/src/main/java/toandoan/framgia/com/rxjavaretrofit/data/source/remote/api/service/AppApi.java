package toandoan.framgia.com.rxjavaretrofit.data.source.remote.api.service;

import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import toandoan.framgia.com.rxjavaretrofit.data.model.Chap;
import toandoan.framgia.com.rxjavaretrofit.data.model.Manga;
import toandoan.framgia.com.rxjavaretrofit.data.model.Response;
import toandoan.framgia.com.rxjavaretrofit.data.model.Source;

/**
 * Created by le.quang.dao on 10/03/2017.
 */

public interface AppApi {
    @GET("getstoryoption")
    Observable<Response<List<Manga>>> getManga(@Query("version_app") String version,
            @Query("source") String source, @Query("limit") int limit,
            @Query("option") String option, @Query("page") int page);

    @GET("api/sources")
    Observable<Response<List<Source>>> getSource(@Query("version_app") String version);

    @GET("getallstory")
    Observable<Response<List<Manga>>> getAllMangas(@Query("version_app") String version,
            @Query("source") String source);

    @GET("getmangainfo")
    Observable<Response<Manga>> getMangaById(@Query("story_id") int storyId);

    @GET("getchapterinfo")
    Observable<Response<Chap>> getChapById(@Query("chapter_id") String chaperId);
}
