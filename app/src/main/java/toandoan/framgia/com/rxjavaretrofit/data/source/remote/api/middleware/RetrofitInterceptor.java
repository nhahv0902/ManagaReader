package toandoan.framgia.com.rxjavaretrofit.data.source.remote.api.middleware;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import toandoan.framgia.com.rxjavaretrofit.data.model.User;

/**
 * Created by Sun on 4/16/2017.
 */

public class RetrofitInterceptor implements Interceptor {

    public RetrofitInterceptor(){
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = initializeHeader(chain);
        Request request = builder.build();
        Response response = chain.proceed(request);
        return response;
    }

    private Request.Builder initializeHeader(Chain chain) {
        Request originRequest = chain.request();
        return originRequest.newBuilder()
                .header("Accept", "application/json")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Cache-Control", "no-store")
                .method(originRequest.method(), originRequest.body());
    }
}
