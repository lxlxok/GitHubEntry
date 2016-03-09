package gitluck.com.githubentry.Interface;

import gitluck.com.githubentry.response.Token;
import gitluck.com.githubentry.response.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;



/**
 * Created by xiao on 3/3/16.
 */


// this is the interface of http client to call the server

public interface GitHubClientUsers {

    // user api
    @GET("users/{user}")
    Call<User> getUser(@Path("user") String user);

    @GET("/user")
    Call<User> authrizedUser(@Header("authorization") String authorization);

    // token api
    @GET("authorizations")
    Call<Token> listAuthorizations(@Header("authorizations") String authorizations);

    @POST("authorizations")
    Call<Token> createAuthorization(@Body Token token, @Header("authorization") String authorization);

}
