package gitluck.com.githubentry;

import gitluck.com.githubentry.response.User;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

import gitluck.com.githubentry.response.User;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * Created by xiao on 3/3/16.
 */

/*
some part of this class is refer to the code from blog of introduction of retrofit
https://futurestud.io/blog/android-basic-authentication-with-retrofit
 */

public class ServiceGenerator {
    public static final String BASE_URL = "https://api.github.com/";

    private static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);

  //  private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null, null);
    }

    public static <S> S createService(Class<S> serviceClass, String user, String password) {
        if (user != null && password != null) {

        }
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);

    }

}
