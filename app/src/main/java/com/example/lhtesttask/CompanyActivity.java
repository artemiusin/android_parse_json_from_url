package com.example.lhtesttask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CompanyActivity extends AppCompatActivity {
    TextView textViewId, textView1, textView2, textView3, textView4, textView5, textView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        textViewId = findViewById(R.id.textViewId);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
        Intent intent = getIntent();
        String response = intent.getStringExtra("response");

        textViewId.setText(response);

        try {
            JSONArray jsonArray = new JSONArray(response);

                JSONObject object = jsonArray.getJSONObject(0);

                textViewId.setText("ID: "+ object.getString("id"));
                textView1.setText("Название: "+ object.getString("name"));
                textView2.setText("Описание: "+ object.getString("description"));
                textView3.setText("Широта: "+ object.getString("lat"));
                textView4.setText("Долгота: "+ object.getString("lon"));
                textView5.setText("www: "+ object.getString("www"));
                textView6.setText("Телефон: "+ object.getString("phone"));

        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "fuck", Toast.LENGTH_LONG).show();

        }
    }
}