package gitluck.com.githubentry.AccountManager;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import gitluck.com.githubentry.AuthActivity;
import gitluck.com.githubentry.AccountManager.StasticAccount.*;
/**
 * Created by xiao on 3/6/16.
 */
public class Authenticator extends AbstractAccountAuthenticator{

    public static final String TAG = "TAGTAG Authenticator";
    public Context context;
    public Authenticator(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
        return null;
    }

    @Override
    public Bundle addAccount(AccountAuthenticatorResponse response, String accountType, String authTokenType, String[] requiredFeatures, Bundle options) throws NetworkErrorException {

        Log.i(TAG, "addAcoount");
        Intent intent = new Intent(context, AuthActivity.class);
        intent.putExtra("ACCOUNT_TYPE", accountType);
        intent.putExtra("AUTH_TYPE", authTokenType);
        intent.putExtra("IS_ADDING_ACCOUNT", true);
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
        final Bundle bundle = new Bundle();
        bundle.putParcelable(AccountManager.KEY_INTENT, intent);
        Log.i(TAG,"addAcoount over");
        return bundle;
    }

    @Override
    public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account, Bundle options) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {

        Log.i(TAG, "getAuthToken");
        Log.i(TAG, "account=" + account);
        AccountManager accountManager = AccountManager.get(context);
        String token = accountManager.peekAuthToken(account, authTokenType);

        if (TextUtils.isEmpty(token)) {

            Log.i(TAG, "TextUtils.isEmpty(token)");
            String passWord = accountManager.getPassword(account);
            if (passWord != null) {

                Log.i(TAG, "passWord != null");
                GithubToken githubToken = new GithubToken();
                token = githubToken.getToken(account.name, passWord);
            }
        }

        if (!TextUtils.isEmpty(token)) {

                Log.i(TAG, "!TextUtils.isEmpty(token)");
                Bundle bundle = new Bundle();
                bundle.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
                bundle.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type);
                bundle.putString(AccountManager.KEY_AUTHTOKEN, token);

                Log.i(TAG, "getAuthToken over1");
                return bundle;
        }

            Intent intent = new Intent(context, AuthActivity.class);
            intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
            intent.putExtra("ACCOUNT_TYPE", account.type);
            intent.putExtra("AUTH_TYPE", authTokenType);
            intent.putExtra("ACCOUNT_NAME", account.name);
            Bundle bundle = new Bundle();
            bundle.putParcelable(AccountManager.KEY_INTENT, intent);

            Log.i(TAG, "getAuthToken over2");
            return bundle;

    }



    @Override
    public String getAuthTokenLabel(String authTokenType) {
        return null;
    }

    @Override
    public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account, String[] features) throws NetworkErrorException {
        return null;
    }
}
