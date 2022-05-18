package com.example.do_an_android.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.do_an_android.Activity.ChiTietSanPham;
import com.example.do_an_android.Activity.ThemLoaiSP;
import com.example.do_an_android.Activity.TrangChu;
import com.example.do_an_android.Model.DienThoai;
import com.example.do_an_android.Model.LoaiSanPham;
import com.example.do_an_android.R;
import com.example.do_an_android.Util.DienthoaiAdapter;
import com.example.do_an_android.Util.LoaiAdapter;
import com.example.do_an_android.Util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DanhSachLoaiSP_Admin extends AppCompatActivity {

    ImageView trove,them;
    TextView chitiet;
    ListView listView;
    public static ArrayList<LoaiSanPham> loaiArrayList = new ArrayList<>();
    LoaiAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_loai_sp_admin);
        listView = findViewById(R.id.mylistview_DanhsachLoaispAdmin);
        adapter= new LoaiAdapter(this,loaiArrayList);
        listView.setAdapter(adapter);
        getdata();
        them = (ImageView) findViewById(R.id.themloaisp);
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(), ThemLoaiSP.class));
            }
        });

        trove = (ImageView) findViewById(R.id.IV_BackToHome);
        trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Admin.class);
finish();
                startActivity(intent);
            }
        });
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getApplicationContext(), "" + loaiArrayList.get(i).getIdproduct(), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getApplicationContext(), ChiTietSanPham.class);
//                int idpro = dienthoaiArrayList.get(i).getIdproduct();
//                intent.putExtra("idproduct", dienthoaiArrayList.get(i).getIdproduct());
//                startActivity(intent);
//            }
//        });
    }
    public  void getdata()
    {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Server.getallloai, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loaiArrayList.clear();
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int idtype_admin = jsonObject.getInt("idtype");
                        String nametype_admin = jsonObject.getString("nametype");

                        LoaiSanPham loai= new LoaiSanPham(idtype_admin,nametype_admin);
                        loaiArrayList.add(loai);
//                        Toast.makeText(getApplicationContext(), "" + dienthoaiArrayList.size(), Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Lỗi " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(getApplicationContext(),Admin.class));
    }
}