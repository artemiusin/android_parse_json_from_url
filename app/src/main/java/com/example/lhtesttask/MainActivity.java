package com.example.lhtesttask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    //TextView textView;
    ListView listView;
    ArrayList<String> list = new ArrayList<>();
    static ArrayList<String> list1 = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // textView = findViewById(R.id.textView);
        listView = findViewById(R.id.listView);


        OkHttpClient client = new OkHttpClient();
        String url = "https://lifehack.studio/test_task/test.php";
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String myResponse = response.body().string();

                    try {
                        //JSONObject jsonObject = new JSONObject(myResponse);
                        //JSONArray jsonArray = jsonObject.getJSONArray("");
                        JSONArray jsonArray = new JSONArray(myResponse);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            String id = object.getString("id");
                            String name = object.getString("name");
                            list.add(name);


                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // textView.setText(myResponse);
                        arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, list);
                        listView.setAdapter(arrayAdapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Toast.makeText(getApplicationContext(), list.get(position), Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(MainActivity.this, CompanyActivity.class);
                                int i = position + 1;
                                String s = "" + i;

                                String url1 = "https://lifehack.studio/test_task/test.php?id=" + s;
                                OkHttpClient client = new OkHttpClient();
                                Request request = new Request.Builder().url(url1).build();
                                client.newCall(request).enqueue(new Callback() {
                                    @Override
                                    public void onFailure(Call call, IOException e) {

                                    }

                                    @Override
                                    public void onResponse(Call call, Response response) throws IOException {
                                        if (response.isSuccessful()) {
                                            String myResponse = response.body().string();
                                            intent.putExtra("response",myResponse);
                                            startActivity(intent);
                                        }
                                    }

                                });

                            }
                        });
                    }
                });
            }
        });
    }
}

