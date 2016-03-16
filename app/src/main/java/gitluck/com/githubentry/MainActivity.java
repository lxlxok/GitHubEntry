package gitluck.com.githubentry;

import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.xml.sax.helpers.LocatorImpl;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

import gitluck.com.githubentry.AccountManager.StasticAccount;


public class MainActivity extends AppCompatActivity {


    public class MainHandler extends android.os.Handler {
        public MainHandler() {

        }

        public MainHandler(Looper L) {
            super(L);
        }

        @Override
        public void handleMessage(Message msg) {
            switch(msg.what) {
                case 88:
                    if (MainActivity.authToken != null) {
                        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(MainActivity.this.getApplicationContext());
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("token", authToken);
                        editor.commit();

                        Intent intent = new Intent(MainActivity.this, UserActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    break;
            }
        }

    }


    public AccountManager accountManager;
    public static final String TAG = "TAGTAG MainActivity";
    public static String authToken = null;
    public  MainHandler mainHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG,"create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainHandler = new MainHandler();

        accountManager = accountManager.get(this);
        getTokenFromAccount(StasticAccount.ACCOUNT_TYPE, StasticAccount.AUTHTOKEN_TYPE_FULL_ACCESS);

    }


    public void test(View view) {
       // TextView view = (TextView)findViewById(R.id.token);
       // view.setText("token = " + authToken);
        Log.i(TAG, "testToken = " + authToken);
        if (authToken != null) {
            SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("token", authToken);
            editor.commit();
        } else {
            getTokenFromAccount(StasticAccount.ACCOUNT_TYPE, StasticAccount.AUTHTOKEN_TYPE_FULL_ACCESS);
        }


    }



    // start login activity
    protected void login() {
        Intent intent = new Intent(this, TestTokenActivity.class);
        startActivity(intent);
        finish();
    }

    // start logout activity
    protected void logout() {
        Log.e("TAG", "LOGOUT_LOGOUT");
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
                        Log.i(TAG, "token" + token);
                        MainActivity.authToken = token;

                        // send message to MainActivity to notify that token have been got
                        Message msg = new Message();
                        msg.what = 88;
                        MainActivity.this.mainHandler.sendMessage(msg);

                    } else {
                        Log.i(TAG, "token = null");
                    }

                } catch (Exception ex) {

                }
            }
        },null);
    }

/*

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG,"resume");

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
