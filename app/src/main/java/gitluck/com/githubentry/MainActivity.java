package gitluck.com.githubentry;

import android.accounts.AccountManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        login();
    }

    // start login activity
    protected void login() {
        //Intent intent = new Intent(this, AuthActivity.class);
        //startActivity(intent);

        Intent intent = new Intent(this, TestTokenActivity.class);
        startActivity(intent);
        finish();
    }

}
