package gitluck.com.githubentry;

import android.accounts.Account;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;

import gitluck.com.githubentry.AccountManager.GithubToken;
import gitluck.com.githubentry.AccountManager.StasticAccount;
import gitluck.com.githubentry.Interface.GitHubClientUsers;
import gitluck.com.githubentry.response.Token;
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
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
//

public class AuthActivity extends AccountAuthenticatorActivity {

    public static final String TAG = "TAGTAG AuthActivity";
    public AccountManager accountManager;
    public String authTokenType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        accountManager = AccountManager.get(getBaseContext());

        String accountName = getIntent().getStringExtra("ACCOUNT_NAME");



        authTokenType = getIntent().getStringExtra("AUTH_TYPE");
        if (authTokenType == null) {
            authTokenType = StasticAccount.AUTHTOKEN_TYPE_FULL_ACCESS;
        }

        // for debug
        if (accountName != null) {
            ((TextView) findViewById(R.id.name)).setText(accountName);
        }

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // The sign up activity returned that the user has successfully created an account
        if (resultCode == RESULT_OK) {
            logInClosed(data);
        } else
            super.onActivityResult(requestCode, resultCode, data);
    }


    public void signIn(View v) {
        EditText viewUser = (EditText) findViewById(R.id.name);
        EditText viewPassword = (EditText) findViewById(R.id.password);

        logIn();
    }


    public void logIn() {
        EditText viewUser = (EditText) findViewById(R.id.name);
        EditText viewPassword = (EditText) findViewById(R.id.password);

        final String userName = viewUser.getText().toString();
        final String passWord = viewPassword.getText().toString();
        final String accountType = getIntent().getStringExtra("ACCOUNT_TYPE");

        new AsyncTask<String, Void, Intent>() {

            @Override
            protected  Intent doInBackground(String... params) {
                Log.i(TAG, "logIn doInBackground");

                String token = null;
                Bundle bundle = new Bundle();
                try {
                    GithubToken githubToken = new GithubToken();
                    token = githubToken.getToken(userName, passWord);

                    bundle.putString(AccountManager.KEY_AUTHTOKEN, token);
                    bundle.putString(AccountManager.KEY_ACCOUNT_NAME, userName);
                    bundle.putString(AccountManager.KEY_ACCOUNT_TYPE, accountType);
                    bundle.putString("USER_PASS", passWord);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    bundle.putString("ERR_MSG", ex.getMessage());
                }

                Intent intent = new Intent();
                intent.putExtras(bundle);
                return intent;
            }

            @Override
            protected void onPostExecute(Intent intent) {
                if (intent.hasExtra("ERR_MSG")) {
                    Log.i(TAG, "ERR_MSG onPostExecute");
                } else {
                    logInClosed(intent);
                }
            }
        }.execute();
    }

    public void logInClosed(Intent intent) {
        Log.i(TAG, "logInClosed");

        String userName = intent.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
        String passWord = intent.getStringExtra("USER_PASS");
        Account account = new Account(userName, intent.getStringExtra(AccountManager.KEY_ACCOUNT_TYPE));

        if (getIntent().getBooleanExtra("IS_ADDING_ACCOUNT", false)) {
            String token = intent.getStringExtra(AccountManager.KEY_AUTHTOKEN);
            String tokenType = authTokenType;

            Log.i(TAG,"IS_ADDING_ACCOUNT fase and token = " + token);

            accountManager.addAccountExplicitly(account, passWord, null);
            accountManager.setAuthToken(account, tokenType, token);
        } else {
            accountManager.setPassword(account, passWord);

            Log.i(TAG, "IS_ADDING_ACCOUNT true and setPassword");
        }
        setAccountAuthenticatorResult(intent.getExtras());
        setResult(RESULT_OK, intent);

        finish();
    }



}