package com.example.myride;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private Toolbar toolbar;
    ArrayList<String> listCity=new ArrayList<>();
    ArrayAdapter<String> newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        MenuItem searchItem = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        final SearchView.SearchAutoComplete searchAutoComplete = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
//        searchAutoComplete.setBackgroundColor(Color.BLUE);
//        searchAutoComplete.setTextColor(Color.GREEN);
       // searchAutoComplete.setDropDownBackgroundResource(android.R.color.holo_blue_light);
        obj_list();
        newsAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, listCity);
        searchAutoComplete.setAdapter(newsAdapter);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                Log.v("TAG",String.valueOf(listCity.size()));
                new Handler(getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {


                    }
                });
            }
        };

        runnable.run();
        searchAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int itemIndex, long id) {
                String queryString=(String)adapterView.getItemAtPosition(itemIndex);
                searchAutoComplete.setText("" + queryString);
                Toast.makeText(LoginActivity.this, "you clicked " + queryString, Toast.LENGTH_LONG).show();
            }
        });


        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.search)
            return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Toast.makeText(this, "Query Inserted", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return true;
    }


    public String getJson() {
        String json=null;
        try {
          InputStream inputStream =  getApplicationContext().getAssets().open("cities.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            // read values in the byte array
            inputStream.read(buffer);
            // close the stream --- very important
            inputStream.close();
            // convert byte to string
            json = new String(buffer, "UTF-8");
            Log.v("TAG",json);
        } catch (IOException e) {
            e.printStackTrace();
            return json;
        }
        return json;
    }
    void obj_list() {
        // Exceptions are returned by JSONObject when the object cannot be created
        try {
            // Convert the string returned to a JSON object
            JSONObject jsonObject=new JSONObject(getJson());
            // Get Json array
            Log.v("TAG",String.valueOf(jsonObject.length()));
            JSONArray array=jsonObject.getJSONArray("array");
            // Navigate through an array item one by one
            for(int i=0;i<array.length();i++) {
                // select the particular JSON data
                JSONObject object=array.getJSONObject(i);
                String city=object.getString("name");
                String state=object.getString("state");
                // add to the lists in the specified format
                Log.v("TAG",city);
                listCity.add(city);
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }
}
