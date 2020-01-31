package com.example.gridimages;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.GridView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gridimages.Adapter.AdapterGalleryImages;
import com.example.gridimages.Model.GalleryImage;
import com.example.gridimages.Utils.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
GridView gridView;
AdapterGalleryImages adapterGalleryImages;
ArrayList<GalleryImage> galleryImages;
     ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       gridView=findViewById(R.id.gridView);
       galleryImages=new ArrayList<>();
        adapterGalleryImages=new AdapterGalleryImages(MainActivity.this,galleryImages);
        gridView.setAdapter(adapterGalleryImages);
        getdata();
    }

    private void getdata() {
        loading = ProgressDialog.show(this, "Please wait...","Fetching data...",false,false);
        loading.show();
        StringRequest stringRequest=new StringRequest(Request.Method.GET, Config.URL_SERVER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            loading.dismiss();
                            JSONArray jsonArray=new JSONArray(response);
                            for (int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject jsonObject=jsonArray.getJSONObject(i);
                                int id=jsonObject.getInt("id");
                                String auther=jsonObject.getString("author");
                                GalleryImage galleryImage=new GalleryImage(id,auther);
                                galleryImages.add(galleryImage);
                            }
                            adapterGalleryImages.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
            }
        });
        Volley.newRequestQueue(MainActivity.this).add(stringRequest);


    }
}
