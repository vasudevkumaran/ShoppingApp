package com.dev.loginregproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText passwordEd;
    private EditText usernameEd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Login");
        usernameEd = (EditText)findViewById(R.id.usernameEd);
        passwordEd = (EditText)findViewById(R.id.passwordEd);
        Button loginBtn = (Button)findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (usernameEd.getText().toString().equals("test")){
                    Util.setString(LoginActivity.this,Util.LOGIN_ID,usernameEd.getText().toString());
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Util.display(LoginActivity.this,"Username or Password wrong!");
                }
            }
        });
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
