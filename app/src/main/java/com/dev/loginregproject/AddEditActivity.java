package com.dev.loginregproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import org.json.JSONException;
import org.json.JSONObject;

public class AddEditActivity extends AppCompatActivity {

    private String itemId = Util.NEW_ITEM;
    private EditText itemNameEd,itemQtyEd,itemPriceEd;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Add Item");
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        itemNameEd = (EditText)findViewById(R.id.itemNameEd);
        itemQtyEd = (EditText)findViewById(R.id.itemQtyEd);
        itemPriceEd = (EditText)findViewById(R.id.itemPriceEd);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
           itemId = bundle.getString(Util.ITEM_ID);
        }
    }

    public void onSavePressed(View view){
        progressBar.setVisibility(ProgressBar.VISIBLE);
        JSONObject payload = new JSONObject();
        try {
            payload.put("username",Util.getString(this,Util.LOGIN_ID,Util.NOT_LOGGED_IN));
            payload.put("password",Util.getString(this,Util.PASSWORD,Util.NOT_LOGGED_IN));
            payload.put("itemname",itemNameEd.getText().toString());
            payload.put("itemqty",itemQtyEd.getText().toString());
            payload.put("itemprice",itemPriceEd.getText().toString());
            if (itemId.equals(Util.NEW_ITEM)){
                //add
                SendToServer sendToServer = new SendToServer(payload) {
                    @Override
                    public void onResultArrive(String result) {
                        progressBar.setVisibility(ProgressBar.GONE);
                        Log.w("ITEM",result);
                    }
                };
                sendToServer.start("additem");
            }else{
                //edit
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
