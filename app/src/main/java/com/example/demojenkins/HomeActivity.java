package com.example.demojenkins;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.e("Home", "onCreate: Home Activity");
        getName("PrathviRaj");
    }

    void getName(String s){
        Log.e("name", "getName:"+s);
    }
}
