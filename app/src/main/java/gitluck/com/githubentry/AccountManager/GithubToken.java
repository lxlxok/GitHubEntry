package gitluck.com.githubentry.AccountManager;

import android.util.Base64;
import android.util.Log;

import org.json.JSONObject;

import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import gitluck.com.githubentry.Interface.GitHubClientUsers;
import gitluck.com.githubentry.ServiceGenerator;
import gitluck.com.githubentry.response.Token;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xiao on 3/6/16.
 */


// this class is implement to call the server to return token back

public class GithubToken {
    public static final String TAG = "TAGTAG GithubToken";
    public String githubToken;
    public static final String note = "github entry app" + String.valueOf(new Random().nextInt());

    public String getToken(String userName, String passWord) {
        GitHubClientUsers userService = ServiceGenerator.createService(GitHubClientUsers.class);
        Token token = new Token();
        String base64String = userName+':'+passWord;
        String[] scopes = {"repo", "user", "gist"};
        token.setNote(note);
        token.setScopes(Arrays.asList(scopes));

        Call<Token> call = userService.createAuthorization(token, "Basic " + Base64.encodeToString(base64String.getBytes(), Base64.NO_WRAP));

        try {
            Response<Token> response = call.execute();
            Token result = response.body();
            githubToken = result.getToken();
            Log.i(TAG, "Response code = " + response.code());
            Log.i(TAG, "githubToken = " + githubToken);
        } catch (IOException ex) {
            ex.printStackTrace();
            Log.i(TAG, "IO Exception in call.execute().body()");
        }

        return githubToken;

    }
}
