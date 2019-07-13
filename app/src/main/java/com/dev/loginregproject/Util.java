package com.dev.loginregproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class Util {

    public static final String LOGIN_ID = "_login_id";
    public static final String PASSWORD = "_password";
    public static final String NOT_LOGGED_IN = "_na";
    public static final String ITEM_ID = "_item_id";
    public static final String NEW_ITEM = "_new_item";
    public static final int REQ_CODE = 112;
    public static final int RES_CODE = 232;
    public static final String BASE_URL = "https://vasudevkumaran.com/voldblog/ang/";

    public static void setString(Context context,String key, String value){
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(key,value);
        editor.commit();
    }

    public static String getString(Context context, String key, String defaultValue){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(key,defaultValue);
    }

    public static void display(Context context,String text){
        Toast.makeText(context,text,Toast.LENGTH_LONG).show();
    }
}
