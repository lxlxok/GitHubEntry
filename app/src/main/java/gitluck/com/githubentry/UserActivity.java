package gitluck.com.githubentry;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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

    }

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

}
