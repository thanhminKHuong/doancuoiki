package com.example.do_an_android.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.do_an_android.R;
import com.example.do_an_android.Model.DienThoai;
import com.example.do_an_android.Util.DienthoaiAdapter;
import com.example.do_an_android.Util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TimKiemSP extends AppCompatActivity {
    String id;
    ListView listView1;
    public static ArrayList<DienThoai> dienthoaiArrayList1 = new ArrayList<>();
    DienthoaiAdapter adapterSearch ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent intent = getIntent();
        id = intent.getStringExtra("search_product");

        adapterSearch= new DienthoaiAdapter(this,dienthoaiArrayList1);

        listView1= findViewById(R.id.mylistview1);
        listView1.setAdapter(adapterSearch);
        getdata();

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                finish();
                Intent intent = new Intent(getApplicationContext(),ChiTietSanPham.class);
                int idpro = dienthoaiArrayList1.get(i).getIdproduct();
                intent.putExtra("idproduct", dienthoaiArrayList1.get(i).getIdproduct());
                startActivity(intent);
            }
        });

}
    public  void getdata()
    {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.searchproductbyname, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dienthoaiArrayList1.clear();
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int idproduct = jsonObject.getInt("idproduct");
                        String nameproduct = jsonObject.getString("nameproduct");
                        int price  =  jsonObject.getInt("price");

                        String manhinh = jsonObject.getString("manhinh");
                        String hdh = jsonObject.getString("hdh");
                        String camerasau = jsonObject.getString("camerasau");
                        String cameratruoc = jsonObject.getString("cameratruoc");
                        String chip = jsonObject.getString("chip");
                        String ram = jsonObject.getString("ram");
                        String bnt = jsonObject.getString("bnt");
                        String sim = jsonObject.getString("sim");
                        String pinsac = jsonObject.getString("pinsac");

                        int sum = jsonObject.getInt("sum");
                        int idtype = jsonObject.getInt("idtype");
                        String hinh = jsonObject.getString("hinh"); // hình nhưu k có cái link hình thì phả
                        DienThoai dt= new DienThoai(idproduct,nameproduct,price,manhinh,hdh, camerasau, cameratruoc, chip, ram, bnt, sim, pinsac,sum,idtype,hinh);
                        dienthoaiArrayList1.add(dt);
                        adapterSearch.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Lỗi " + error.toString(), Toast.LENGTH_SHORT).show();
                Log.d("lỗi",error.toString() + " ");
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
                Intent intent = getIntent();
                id = intent.getStringExtra("search_product");
                param.put("nameproduct",id );

                return param;
            }
        };

        requestQueue.add(stringRequest);
    }
    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(getApplicationContext(),TrangChu.class));
    }

}