package com.example.do_an_android.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.do_an_android.Model.cart;
import com.example.do_an_android.R;
import com.example.do_an_android.Util.Server;
import com.example.do_an_android.Util.itemInCartAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GioHang extends AppCompatActivity {

    ImageView backToHome,cancel;
    private static final String FILE_NAME = "myFile";
    SharedPreferences sharedPreferences;
    TextView tongSoLuong, soLuong, them, giam;
    public static ArrayList<cart> giohangArrayList = new ArrayList<>();
    private itemInCartAdapter adapter;
    private ListView listView;
    private static int iduser_share;
    TextView tongtiengh,getidorder,muaHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        sharedPreferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        iduser_share = sharedPreferences.getInt("iduser", 0);
        getidorder =(TextView) findViewById(R.id.txt_idorder);
        tongtiengh =(TextView) findViewById(R.id.tongTienGioHang);
        tongSoLuong = (TextView) findViewById(R.id.sumincart);
        backToHome = (ImageView) findViewById(R.id.veTrangChu);
        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i = new Intent(GioHang.this , TrangChu.class);
                startActivity(i);
            }
        });

        cancel = (ImageView) findViewById(R.id.veDanhSach);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i = new Intent(GioHang.this , DanhSachSP.class);
                startActivity(i);
            }
        });
        listView = findViewById(R.id.mylistview_Danhsachsp);

        adapter = new itemInCartAdapter(this,giohangArrayList);
        listView.setAdapter(adapter);
        getdata(iduser_share);

        muaHang = findViewById(R.id.muaHang);
        muaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              thanhtoan();

            }
        });

    }
    private static int idorder=0;
    public  void getdata(int id) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.getproductcart,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        giohangArrayList.clear();
                        int tong=0;
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                int iddetail = jsonObject.getInt("iddetail");
                                String nameproduct = jsonObject.getString("nameproduct");
                                int price  =  jsonObject.getInt("priceproduct");
                                int sum  =  jsonObject.getInt("sum");
                                int totalprice  =  jsonObject.getInt("totalprice");
                                idorder= jsonObject.getInt("idorder");
                                String hinh = jsonObject.getString("hinh");

                                tong+=totalprice;


                                cart gh= new cart(iddetail,nameproduct,price,sum,totalprice,hinh);
                                giohangArrayList.add(gh);
                                adapter.notifyDataSetChanged();
                            }

                            getidorder.setText(String.valueOf(idorder));
                            SharedPreferences.Editor editor = getSharedPreferences(FILE_NAME, MODE_PRIVATE).edit();
                            editor.putInt("idorder_gh",idorder);
                            editor.putInt("tongtien",tong);
                            editor.apply();
                            demSoluongSanPhamCoTrongGioHang(tong);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Lỗi " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                int i=1;
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("iduser",String.valueOf(id));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }
    public void demSoluongSanPhamCoTrongGioHang(int tong){
        int dem = giohangArrayList.size();
        tongSoLuong.setText("Giỏ hàng (" + dem + ")");
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        tongtiengh.setText(formatter.format(tong)+""+" VNĐ");
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
    }
    public void thanhtoan(){
        finish();
        Intent intent = new Intent(GioHang.this,DatHang.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(getApplicationContext(),TrangChu.class));
    }

}