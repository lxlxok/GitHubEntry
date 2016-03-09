package gitluck.com.githubentry.GithubApi;

import android.content.Context;
import android.util.Log;

import gitluck.com.githubentry.Interface.GitHubClientUsers;
import gitluck.com.githubentry.ServiceGenerator;
import gitluck.com.githubentry.response.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xiao on 3/7/16.
 */
public class GithubUser {
    public Context context;
    public GithubAccount githubAccount;
    public GitHubClientUsers userService;

    public static final String TAG = "TAGTAG GithubUser";

    public GithubUser(Context context) {
        this.context = context;
        this.githubAccount = GithubAccount.getInstance(context);
        this.userService = ServiceGenerator.createService(GitHubClientUsers.class);

    }

    public void authUser() {
        Call<User> call = userService.authrizedUser();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response) {
                if (response.code() == 401) {
                    Log.i(TAG, "response code is" + response.code());
                    authUser();
                } else {
                    Log.i(TAG, "response code is" + response.code());
                    Log.i(TAG, "authUser" + response.body().getName());
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

}
