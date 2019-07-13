package com.dev.loginregproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText passwordEd;
    private EditText usernameEd;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Login");
        usernameEd = (EditText)findViewById(R.id.usernameEd);
        passwordEd = (EditText)findViewById(R.id.passwordEd);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        Button loginBtn = (Button)findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });
    }

    private void checkLogin(){
        JSONObject payload = new JSONObject();
        try {
            payload.put("username",usernameEd.getText().toString());
            payload.put("password",passwordEd.getText().toString());
            progressBar.setVisibility(ProgressBar.VISIBLE);
            SendToServer sendToServer = new SendToServer(payload) {
                @Override
                public void onResultArrive(String result) {
                    Log.w("LOGIN_RESULT",result);

                    progressBar.setVisibility(ProgressBar.GONE);

                    try {
                        JSONObject resultObject = new JSONObject(result);
                        if (resultObject.getString("result").equals("OK")){
                            JSONArray resultArray = resultObject.getJSONArray("data");
                            JSONObject dataObject = resultArray.getJSONObject(0);
                            Util.setString(getBaseContext(),Util.LOGIN_ID,dataObject.getString("user_name"));
                            Util.setString(getBaseContext(),Util.PASSWORD,dataObject.getString("user_password"));
                            startActivity(new Intent(getBaseContext(),MainActivity.class));
                            finish();
                        }else{
                            Util.display(getBaseContext(),"Username / Password wrong");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            };
            sendToServer.start("login");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_register){
            startActivity(new Intent(this,RegisterActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
