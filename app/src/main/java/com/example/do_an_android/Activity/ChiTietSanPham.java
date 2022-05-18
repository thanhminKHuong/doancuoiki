package com.example.do_an_android.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import com.example.do_an_android.Model.BinhLuanModel;
import com.example.do_an_android.Model.HinhAnhModel;
import com.example.do_an_android.R;
import com.example.do_an_android.Util.DownloadImageTask;
import com.example.do_an_android.Util.Server;
import com.example.do_an_android.Util.VolleySingleton;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChiTietSanPham extends AppCompatActivity {
    int id;
    ImageView hinh;
    Toolbar toolbar_back_chitiet;
    TextView tenSP,giaSP,manhinhSP,hdhSP,camerasauSP,cameratruocSP,chipSP,ramSP,bonhotrongSP,simSP,pinsacSP,txtdatmua;
    Button btnUpdateCategory , btnThemBinhLuan ;
    EditText noidungbinhluan ;
    ListView lvDanhSachBinhLuan ;

    itemCmtAdapter itemCmtAdapter ;
    List<BinhLuanModel> binhLuanModels ;
  
    private static final String FILE_NAME = "myFile";

    SharedPreferences sharedPreferences;
    private  static int iduser_share,idorder;

    public static  ArrayList<BinhLuanModel> binhLuanModels222 = new ArrayList<>();

    public static ArrayList<HinhAnhModel> hinhAnhModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        anhxa();



//       getBinhLuanByIdProduct();
//        Lấy id của người dùng hiện tại
        sharedPreferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        iduser_share = sharedPreferences.getInt("iduser", 0);
        Log.d("hhhhhh",String.valueOf(iduser_share) + " ");



        btnThemBinhLuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDataBinhLuan();
            }
        });



        toolbar_back_chitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(), DanhSachSP.class));
            }
        });
//        Nhận intent lấy được id gửi từ bên kia quaa
        Intent intent = getIntent();
         id = intent.getIntExtra("idproduct",-1);
         Log.d("idnehahah",String.valueOf(id) + " ");

        getBinhLuanByIdProduct();

//        slideModels.add()

//         List<BinhLuanModel> binhLuanModels = new ArrayList<>();
//        Bình luận
         itemCmtAdapter = new itemCmtAdapter(ChiTietSanPham.this,R.layout.item_comment,binhLuanModels222);

         itemCmtAdapter.notifyDataSetChanged();
         lvDanhSachBinhLuan.setAdapter(itemCmtAdapter);

        hinhAnhModels.clear();


        binhLuanModels222.clear();


        getData();

        getIdOrder(iduser_share);
        idorder = sharedPreferences.getInt("idorder", 0);

        txtdatmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertProduct();

            }

        });

    }
    private static int kiemtra=0;
    private void anhxa(){

        toolbar_back_chitiet = findViewById(R.id.toolbar_back_chitiet);
        tenSP = findViewById(R.id.ten_san_pham_chitiet);
        giaSP = findViewById(R.id.gia_SP_chitiet);
        manhinhSP = findViewById(R.id.txt_manhinh_chitiet);
        hdhSP= findViewById(R.id.txt_hdh_chitiet);
        camerasauSP= findViewById(R.id.txt_camerasau_chitiet);
        cameratruocSP= findViewById(R.id.txt_cameratruoc_chitiet);
        chipSP= findViewById(R.id.txt_chip_chitiet);
        ramSP= findViewById(R.id.txt_ram_chitiet);
        bonhotrongSP= findViewById(R.id.txt_bonhotrong_chitiet);
        simSP= findViewById(R.id.txt_sim_chitiet);
        pinsacSP= findViewById(R.id.txt_pinsac_chitiet);
        txtdatmua=findViewById(R.id.txt_datmua);
        hinh = findViewById(R.id.imghinh);
//        hinh = findViewById(R.id.img_sanpham);
//        imageSlider = this.<ImageSlider>findViewById(R.id.image_slider);
        btnUpdateCategory = this.<Button>findViewById(R.id.txt_updateCategory);
        noidungbinhluan = this.<EditText>findViewById(R.id.noidungbinhluan);
        btnThemBinhLuan = this.<Button>findViewById(R.id.btnthembinhluan);
        lvDanhSachBinhLuan = this.<ListView>findViewById(R.id.lv_danhsachbinhluan);
    }
    private static int giasp;
    private void getData() {
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.getproductbyid, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    Log.d("dfsfsdf",jsonArray.toString() + " ");
                    Log.d("ahihihi",jsonObject.names() + " ");
                    int idproduct = jsonObject.getInt("idproduct");
                    String nameproduct = jsonObject.getString("nameproduct");
                    int price  =  jsonObject.getInt("price");
                    giasp=price;
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

                    getHinhAnhSanPham();

                    String hinhanh = jsonObject.getString("hinh");
                   // Uri imgUri=Uri.parse(hinhanh);
                    Glide.with(getApplicationContext()).load(hinhanh).into(hinh);


                    tenSP.setText(nameproduct);
                    DecimalFormat formatter = new DecimalFormat("###,###,###");
                    giaSP.setText("Giá: "+ formatter.format(price)  + "VNĐ");
                    manhinhSP.setText(manhinh);
                    hdhSP.setText(hdh);
                    camerasauSP.setText(camerasau);
                    cameratruocSP.setText(cameratruoc);
                    chipSP.setText(chip);
                    ramSP.setText(ram);
                    bonhotrongSP.setText(bnt);
                    simSP.setText(sim);
                    pinsacSP.setText(pinsac);

//

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
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("idproduct", String.valueOf(id));
                return param;
            }
        };
//        requestQueue.add(stringRequest);
        VolleySingleton.getInstance(this).getRequestQueue().add(stringRequest);
    }

    public void getHinhAnhSanPham()
    {
        String id_baiviet = String.valueOf(id);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.layhinhanhbinhluan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    Log.d("gdsdfasfasdfafaf",jsonArray.length() + " ");

                    if(jsonArray.length() > 0)
                    {
                        Log.d("kiemmmtraaaa",jsonArray.toString() + " ");
                        if(id == id)
                        {
                            for (int i=0;i <= jsonArray.length();i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Log.d("",jsonObject.toString() +  " ");
                                int id_hinh = jsonObject.getInt("id_hinh");
                                String tenhinh = jsonObject.getString("ten_hinh");
                                int idbv = jsonObject.getInt("id_baiviet");
//                                int id_baiviet = jsonObject.getInt("id_baiviet");
//                                int id_binhluan = jsonObject.getInt("id_binhluan");
//                                String ten = jsonObject.getString("nameuser");
                                HinhAnhModel hinhAnhModel = new HinhAnhModel(id_hinh,idbv,tenhinh);
                                hinhAnhModels.add(hinhAnhModel);
//                                BinhLuanModel binhLuanModel = new BinhLuanModel(id_binhluan, id_baiviet, noidung , ten);
                                Log.d("hahahahahahha", jsonObject.toString() + "");
//                                binhLuanModels222.add(binhLuanModel);
//                                itemCmtAdapter.notifyDataSetChanged();
//                                Log.d("solanchay",i + " ");
//                                Log.d("listttleng",binhLuanModels222.size() + " ");
                            }
                        }
                    }else{
                        Log.d("kocodulieu","Ko có lấy được bình luận nào carr ô cơ chưa");
//                        binhLuanModels222.clear();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("vjp",e.getMessage() + " ");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Lỗi " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
//                String s  = String.valueOf(id);
                Log.d("idbaiviet",id_baiviet + " " );
                param.put("idbaiviet", id_baiviet);
                return param;
            }
        };
//        requestQueue.add(stringRequest);
        VolleySingleton.getInstance(this).getRequestQueue().add(stringRequest);
    }

    public void getBinhLuanByIdProduct()
    {
//        Lấy id của bài viết người dùng click vào xem
        String id_baiviet = String.valueOf(id);
//        Log.d("getBinhLuanByIdProduct","Fsdfsf");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.laytatcabinhluan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    Log.d("nnnnnn",jsonArray.length() + " ");

                    if(jsonArray.length() > 0)
                    {
                        Log.d("kiemmmtraaaa",jsonArray.toString() + " ");
                        if(id == id)
                        {
//                            jsonArray số bình luận lấy được
                            for (int i=0;i <= jsonArray.length();i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String noidung = jsonObject.getString("noidung");
                                int id_baiviet = jsonObject.getInt("id_baiviet");
                                int id_binhluan = jsonObject.getInt("id_binhluan");
                                String ten = jsonObject.getString("nameuser");
                                BinhLuanModel binhLuanModel = new BinhLuanModel(id_binhluan, id_baiviet, noidung , ten);
                                Log.d("hahahahahahha", jsonObject.toString() + "");
//                                thêm bình luận đó vào mạng
                                binhLuanModels222.add(binhLuanModel);
                                itemCmtAdapter.notifyDataSetChanged();
//                                Log.d("solanchay",i + " ");
//                                Log.d("listttleng",binhLuanModels222.size() + " ");


                            }
                        }
                    }else{
                       Log.d("kocodulieu","Ko có lấy được bình luận nào carr ô cơ chưa");
                       binhLuanModels222.clear();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                   Log.d("vjp",e.getMessage() + " ");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Lỗi " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
//                String s  = String.valueOf(id);
                Log.d("idbaiviet",id_baiviet + " " );
                param.put("idbaiviet", id_baiviet);
                return param;
            }
        };
//        requestQueue.add(stringRequest);
        VolleySingleton.getInstance(this).getRequestQueue().add(stringRequest);
    }



    private void getDataBinhLuan()
    {
      String id_user = String.valueOf(iduser_share) ;

      String id_baiviet = String.valueOf(id);
      String noidungbinhluan = this.noidungbinhluan.getText().toString().trim();

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.addcommentbyid, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                if (response.equals("Done")) {
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(getApplicationContext(), TrangChu.class));
//                } else {
//                    Toast.makeText(getApplicationContext(), "Thêm không thành công" , Toast.LENGTH_SHORT).show();
//                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Lỗi bìnhluan " + error.toString(), Toast.LENGTH_SHORT).show();
            }

    }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("idbaiviet", id_baiviet);
                param.put("iduser", id_user);
                param.put("noidungbinhluan", noidungbinhluan);

                return param;
            }
        };
        requestQueue.add(stringRequest);

    }




    private static String idget;
    private void getIdOrder(int iduser){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.insert, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
//                    Toast.makeText(getApplicationContext(), ""+response, Toast.LENGTH_SHORT).show();
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    int _idorder = jsonObject.getInt("id");
//                    Toast.makeText(getApplicationContext(), "id:"+idorder, Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = getSharedPreferences(FILE_NAME, MODE_PRIVATE).edit();
                    editor.putInt("idorder",_idorder);
                    editor.apply();
                    idget=String.valueOf(_idorder);
                    if(idorder!=_idorder){
                        finish();
                        startActivity(new Intent(getApplicationContext(),TrangChu.class));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Lỗi 1" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();

                param.put("iduser", String.valueOf(iduser));

                return param;
            }
        };
        requestQueue.add(stringRequest);
//        VolleySingleton.getInstance(this).getRequestQueue().add(stringRequest);
//        return idget;
    }
    private  void insertProduct(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.insertItemToCart, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                if(response.equals("Done")){
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(getApplicationContext(), GioHang.class));
//                }
//                else {
//                    Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
//                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Lỗi 2 " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("idorder", String.valueOf(idorder));
                param.put("priceproduct", String.valueOf(giasp));
                param.put("idproduct", String.valueOf(id));
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

    public void trovetrangchu(View view) {
        startActivity(new Intent(getApplicationContext(),TrangChu.class));
    }

    public void hienthiclick()
    {
        Log.d("đãclickroine","fsfdfsdfsdfsd");
    }


}