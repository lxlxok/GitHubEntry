package gitluck.com.githubentry;

import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import gitluck.com.githubentry.AccountManager.StasticAccount;

public class TestTokenActivity extends AppCompatActivity {
    public AccountManager accountManager;
    public static final String TAG = "TAGTAGTestTokenActivity";
    public static String authToken = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_token);
        accountManager = accountManager.get(this);
    }

    public void clickAddAccount(View v) {
        addAccount(StasticAccount.ACCOUNT_TYPE, StasticAccount.AUTHTOKEN_TYPE_FULL_ACCESS);
    }

    public void clickShowAccount(View v) {
        getTokenFromAccount(StasticAccount.ACCOUNT_TYPE, StasticAccount.AUTHTOKEN_TYPE_FULL_ACCESS);
    }

    public void addAccount(String accountType, String authTokenType) {
        final AccountManagerFuture<Bundle> future = accountManager.addAccount(accountType, authTokenType, null, null, this, new AccountManagerCallback<Bundle>() {
            @Override
            public void run(AccountManagerFuture<Bundle> future) {
                try {
                    future.getResult();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }, null);
    }

    public void getTokenFromAccount(String accountType, String authTokenType) {
        final AccountManagerFuture<Bundle> future = accountManager.getAuthTokenByFeatures(accountType, authTokenType, null, this, null, null, new AccountManagerCallback<Bundle>() {
            @Override
            public void run(AccountManagerFuture<Bundle> future) {
                Bundle bundle = null;
                try {
                    bundle = future.getResult();
                    final String token = bundle.getString(AccountManager.KEY_AUTHTOKEN);
                    if (token != null) {
                        Log.i(TAG,"token" + token);
                        authToken = token;
                    } else {
                        Log.i(TAG, "token = null");
                    }

                } catch (Exception ex) {

                }
            }
        },null);
    }





}
