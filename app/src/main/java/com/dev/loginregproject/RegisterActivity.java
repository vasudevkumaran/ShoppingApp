package com.dev.loginregproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {


    private EditText usernameEd,passwordEd,firstNameEd,lastNameEd;
    private RadioButton maleRd,femaleRd;
    private CheckBox bizCb,holidayCb,travelCb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Register");

        usernameEd = (EditText)findViewById(R.id.usernameEd);
        passwordEd = (EditText)findViewById(R.id.passwordEd);
        firstNameEd = (EditText)findViewById(R.id.firstNameEd);
        lastNameEd = (EditText)findViewById(R.id.lastNameEd);

        maleRd = (RadioButton)findViewById(R.id.maleRd);
        femaleRd = (RadioButton)findViewById(R.id.femaleRd);

        bizCb = (CheckBox)findViewById(R.id.bizCb);
        travelCb = (CheckBox)findViewById(R.id.travelCb);
        holidayCb = (CheckBox)findViewById(R.id.holidayCb);



    }

    public void onRegPressed(View view){
        JSONObject payLoadObject = new JSONObject();
        try {
            payLoadObject.put("username",usernameEd.getText().toString());
            payLoadObject.put("password",passwordEd.getText().toString());
            payLoadObject.put("firstname",firstNameEd.getText().toString());
            payLoadObject.put("lastname",lastNameEd.getText().toString());
            payLoadObject.put("gender",2);
            payLoadObject.put("is_business",2);
            payLoadObject.put("is_travel",2);
            payLoadObject.put("is_holidays",2);
            if (maleRd.isChecked()){
                payLoadObject.put("gender",1);
            }
            if (bizCb.isChecked()){
                payLoadObject.put("is_business",1);
            }
            if (travelCb.isChecked()){
                payLoadObject.put("is_travel",1);
            }
            if (holidayCb.isChecked()){
                payLoadObject.put("is_holidays",1);
            }
            SendToServer sendToServer = new SendToServer(this, payLoadObject) {
                @Override
                public void onResultArrive(String result) {
                    Log.w("FROM_SERVER",result);
                    try {
                        JSONObject resultObject = new JSONObject(result);
                        if (resultObject.getString("result").equals("OK")){
                            Util.display(getBaseContext(),"Successfully Registered");
                            finish();
                        }else{
                            Util.display(getBaseContext(),resultObject.getString("message"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            sendToServer.start("registration");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
