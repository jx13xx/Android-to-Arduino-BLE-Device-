package com.dfrobot.angelo.blunobasicdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLock extends Activity {

    public EditText admin_user;
    public EditText admin_pass;
    public Button   login_btn;
    public String   password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminlock);

        admin_user = (EditText)findViewById(R.id.admin_user);
        admin_pass = (EditText)findViewById(R.id.admin_pass);
        login_btn = (Button)findViewById(R.id.login_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                admin_loginCheck();
            }
        });

    }

    public void admin_loginCheck(){
        password = admin_pass.getText().toString();
        if(password.contentEquals("admin")){
            Intent intent = new Intent(this, Adminlogin.class);
            startActivity(intent);
            admin_pass.setText("");
            admin_user.setText("");

        }
        else {
            Toast.makeText(this," Incorrect Username or Password", Toast.LENGTH_SHORT).show();
        }
    }
}
