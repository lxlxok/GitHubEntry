package gitluck.com.githubentry.Interface;

import java.util.List;

import gitluck.com.githubentry.response.Commit;
import gitluck.com.githubentry.response.Content;
import gitluck.com.githubentry.response.Issue;
import gitluck.com.githubentry.response.Repository;
import gitluck.com.githubentry.response.Token;
import gitluck.com.githubentry.response.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by xiao on 3/3/16.
 */


// this is the interface of http client to call the server

public interface GitHubClientUsers {

    // user api
    @GET("users/{user}")
    Call<User> getUser(@Header("authorizations") String authorizations, @Path("user") String user);

    @GET("/user")
    Call<User> authrizedUser(@Header("authorization") String authorization);

    @GET("/users/{user}/following?per_page=20")
    Call<List<User>> follwering(@Header("authorizations") String authorizations, @Path("user") String user,@Query("page") String page);

    @GET("/users/{user}/followers?per_page=20")
    Call<List<User>> follower(@Header("authorizations") String authorizations, @Path("user") String user,@Query("page") String page);

    @GET("/users/{user}/repos?sort=pushed&per_page=20")
    Call<List<Repository>> userRepos(@Header("authorizations") String authorizations,@Path("user") String user,@Query("page") String page);


    // token api
    @GET("authorizations")
    Call<Token> listAuthorizations(@Header("authorizations") String authorizations);

    @POST("authorizations")
    Call<Token> createAuthorization(@Body Token token, @Header("authorization") String authorization);


    // issue
    @GET("/repos/{owner}/{repo}/issues?&per_page=20")
    Call<List<Issue>> listIssue(@Header("authorizations") String authorizations, @Path("owner") String owner, @Path("repo") String repo,@Query("page") String page);


    //commit
    @GET("/repos/{owner}/{repo}/commits?&per_page=20")
    Call<List<Commit>> listCommit(@Header("authorizations") String authorizations, @Path("owner") String owner, @Path("repo") String repo,@Query("page") String page);


    //content
    @GET("/repos/{owner}/{repo}/contents/{path}?&per_page=20")
    Call<List<Content>> listContent(@Header("authorizations") String authorizations, @Path("owner") String owner, @Path("repo") String repo, @Path("path") String path, @Query("page") String page);






}
