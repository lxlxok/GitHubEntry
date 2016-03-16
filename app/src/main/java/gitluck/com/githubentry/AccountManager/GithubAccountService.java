package gitluck.com.githubentry.AccountManager;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by xiao on 3/6/16.
 */
public class GithubAccountService extends Service{

    public Authenticator authenticator;


    @Override
    public void onCreate() {
        super.onCreate();
        authenticator = new Authenticator(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("TAGTAG", "onBind");
        return authenticator.getIBinder();
    }
}
