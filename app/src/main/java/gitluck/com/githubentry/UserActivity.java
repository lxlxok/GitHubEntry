package gitluck.com.githubentry;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import gitluck.com.githubentry.Interface.GitHubClientUsers;
import gitluck.com.githubentry.response.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {
    public String token;
    public static final String TAG = "TAGTAG UserActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i(TAG,"create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        token = settings.getString("token", "");
        Log.i(TAG, "user token =" + token);
        testUser();

    }


    public void testUser() {
        GitHubClientUsers userService = ServiceGenerator.createService(GitHubClientUsers.class);
        Call<User> call = userService.authrizedUser("token "+token);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response) {
                if (response.isSuccess()) {
                    Log.i(TAG, "response code is" + response.code());
                    Log.i(TAG,"response body is" + response.body().getFollowers());
                } else {

                    Log.i(TAG, "response failed");
                    Log.i(TAG, "response code is" + response.code());

                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }


    public void requestServer() {
        GitHubClientUsers userService = ServiceGenerator.createService(GitHubClientUsers.class);
        Call<User> call = userService.getUser("lxlxok");
        /*
        try {
            Response<User> response = call.execute();
            //Log.i(TAG, "Body is = " + serveruser.getName());
        } catch (IOException ex) {
            Log.i(TAG, "error IOException");
            ex.printStackTrace();
        }
        */
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response) {
                if (response.isSuccess()) {
                    Log.i(TAG, "Body is" + response.body().getName());
                } else {

                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });


    }


    /*
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"resume" + token);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"destroy");

    }

    */

}
