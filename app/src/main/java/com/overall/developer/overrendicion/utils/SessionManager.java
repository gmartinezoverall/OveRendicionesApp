package com.overall.developer.overrendicion.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;


import java.util.HashMap;

/**
 * Created by terry on 9/26/2016.
 */
public class SessionManager
{
    /*SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;

    private static final String PREFER_NAME = "AndroidExamplePref";

    private static final String IS_USER_LOGIN = "IsUserLoggedIn";

    public static final String KEY_DNI = "dniUser";

    public static final String KEY_NOMBRE = "nombreUser";

    public static final String KEY_EMAIL = "emailUser";

    public SessionManager(Context _context) {
        this._context = _context;
        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createUserLoginSession(String dniUser, String nombeUser, String emailUser)
    {
        editor.putBoolean(IS_USER_LOGIN, true);

        editor.putString(KEY_DNI, dniUser);

        editor.putString(KEY_NOMBRE, nombeUser);

        editor.putString(KEY_EMAIL, emailUser);

        editor.commit();
    }
    public boolean checkLogin(){

        if(!this.isUserLoggedIn()){

            Intent i = new Intent(_context, LoginActivity.class);

            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


            _context.startActivity(i);

            return true;
        }
        return false;
    }
    public HashMap<String, String> getUserDetails(){

        HashMap<String, String> user = new HashMap<String, String>();

        user.put(KEY_DNI, pref.getString(KEY_DNI, null));

        user.put(KEY_NOMBRE, pref.getString(KEY_NOMBRE, null));

        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));


        return user;
    }
    public void logoutUser(){

        editor.clear();
        editor.commit();

        Intent i = new Intent(_context, LoginActivity.class);

        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        _context.startActivity(i);
    }


    public boolean isUserLoggedIn()
    {
        return pref.getBoolean(IS_USER_LOGIN, false);
    }*/

}
