package gitluck.com.githubentry.AccountManager;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

/**
 * Created by xiao on 3/7/16.
 */
public class GithubAccount {
    public final Account account;   //
    public final AccountManager accountManager;  //
    public volatile static GithubAccount instance;
    public  Context context; //

    // singleton model
    public  static GithubAccount getInstance(Context context) {
        if (instance == null) {
            synchronized (GithubAccount.class) {
                if (instance == null) {
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                    String accountName = sharedPreferences.getString("ACCOUNT","");
                    if (accountName.isEmpty()) {
                        accountName = "NEW_ACCOUNT";
                    }
                    Account account = new Account(accountName,"com.githubentry"); // accountType = com.githubentry
                    instance = new GithubAccount(account, context);
                }
            }
        }
        return instance;
    }

    public GithubAccount(Account account, Context context) {
        this.account = account;
        this.accountManager = AccountManager.get(context);
        this.context = context;
    }

    public String getUserName() {
        return account.name;
    }

    public String getPassWord() {
        return accountManager.getPassword(account);
    }

    public void cancleToken(String token) {
        accountManager.invalidateAuthToken("com.githubentry", token);
    }

    /*

    public String getAccountToken() {
        final AccountManagerFuture<Bundle> accountManagerFuture = accountManager.getAuthToken(account,"com.githubentry", null, context, null, null);


    }
    */

}
