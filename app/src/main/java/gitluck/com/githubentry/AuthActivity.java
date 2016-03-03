package gitluck.com.githubentry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import gitluck.com.githubentry.Interface.GitHubClientUsers;
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

public class AuthActivity extends AppCompatActivity {

    public static final String TAG = "AuthActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

    }

    public void signIn(View v) {
        EditText viewUser = (EditText) findViewById(R.id.name);
        EditText ViewPassword = (EditText) findViewById(R.id.password);

        Log.i(TAG, "viewUser = " + viewUser.getText().toString());
        Log.i(TAG, "ViewPassword = " + ViewPassword.getText().toString());
        requestServer();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void requestServer() {
        GitHubClientUsers userService = ServiceGenerator.createService(GitHubClientUsers.class);
        Call<User> call = userService.getUser("lxlxok");
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response) {
                if (response.isSuccess()) {
                    Log.i(TAG, "Body is" + response.body());
                } else {

                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });


        /*
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
        UserApi service = retrofit.create(UserApi.class);
        Call<User> queryResponseCall = service.getUser("lxlxok");

        queryResponseCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response) {
                Log.i(TAG, "Body is" + response.body());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }


    public interface UserApi{
        @GET("users/{user}")
        Call<User> getUser(@Path("user") String user);
    }

    */

    }
}
