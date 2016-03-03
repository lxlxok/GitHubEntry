package gitluck.com.githubentry.Interface;

import gitluck.com.githubentry.response.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by xiao on 3/3/16.
 */
public interface GitHubClientUsers {
    @GET("users/{user}")
    Call<User> getUser(@Path("user") String user);
}
