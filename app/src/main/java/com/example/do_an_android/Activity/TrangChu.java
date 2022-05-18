package com.example.do_an_android.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.do_an_android.Model.User;
import com.example.do_an_android.R;
import com.example.do_an_android.Model.DienThoai;
import com.example.do_an_android.Util.DienthoaiAdapter;
import com.example.do_an_android.Util.Server;
import com.example.do_an_android.Util.VolleySingleton;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class TrangChu extends AppCompatActivity {

    EditText editseacrchh;
    TextView xemtca;
    RecyclerView  recyclerView;

    List<Integer> integerList ;

    CircleIndicator circleIndicator;
    ViewPager viewPager ;
    MyAdapterSlider myAdapterSlider ;
    Timer timer;


    ImageView search;
    public static ArrayList<DienThoai> dienthoaiArrayList = new ArrayList<>();
    CategoryAdapter categoryAdapter ;

    private static final String FILE_NAME = "myFile";

    BottomNavigationView bottomNavigationView;
    public static String email_user = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchu);
        AnhXa();

//       slide nè
         integerList = new ArrayList<>();
        integerList.add(R.drawable.slide_1);
        integerList.add(R.drawable.slide_2);
        integerList.add(R.drawable.slide_3);
        integerList.add(R.drawable.slide_4);
        integerList.add(R.drawable.slide_5);
        integerList.add(R.drawable.slide_7);
        myAdapterSlider = new MyAdapterSlider(integerList);
        viewPager.setAdapter(myAdapterSlider);

        circleIndicator.setViewPager(viewPager);
        autoslideImages();


//      Lấy email của người dùng hiện tại được ghi ở phần đăng nhập
        SharedPreferences sharedPreferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        email_user = sharedPreferences.getString("email", "");

      categoryAdapter = new CategoryAdapter(this);




//        categoryAdapter.setData(getdata());
        getdata();
       categoryAdapter.setData(dienthoaiArrayList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);

        getUserIdByEmail(email_user);



        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent6= new Intent(getApplicationContext(), TimKiemSP.class);
                String search_product = editseacrchh.getText().toString().trim();
                intent6.putExtra("search_product", search_product);
                startActivity(intent6);
            }
        });
        recyclerView.setAdapter(categoryAdapter);
        getdata();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav) ;
       bottomNavigationView.setOnNavigationItemSelectedListener((item)->{
           finish();
           switch (item.getItemId()){
               case R.id.page_1:
                   Intent intent= new Intent(this, TrangChu.class);
                   startActivity(intent);
                   break;
               case R.id.Click_to_cart:
                   Intent intent1 = new Intent(this, GioHang.class);
                   startActivity(intent1);
                   break;
               case R.id.page_3:
                   Intent intent2= new Intent(this, DanhSachSP.class);
                   startActivity(intent2);
                   break;
               case R.id.page_4:
                   Intent intent6= new Intent(this, TrangChu.class);
                   Toast.makeText(getApplicationContext(), "Sắp có", Toast.LENGTH_SHORT).show();
                   startActivity(intent6);
                   break;
               case R.id.page_5:
                   Intent intent5= new Intent(this, ThongTinCaNhan.class);
                   startActivity(intent5);
                   break;
           }
            return true;
       });
//       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//           @Override
//           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//               Intent intent = new Intent(getApplicationContext(),ChiTietSanPham.class);
//               int idpro = dienthoaiArrayList.get(i).getIdproduct();
//               finish();
//               intent.putExtra("idproduct", dienthoaiArrayList.get(i).getIdproduct());
//               startActivity(intent);
//           }
//       });


    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(getApplicationContext(),DangNhap.class));
    }

    public  void getdata() {
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Server.getAllProduct, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dienthoaiArrayList.clear();

                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i=0;i<jsonArray.length();i++)
                    {
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
                        String hinh = jsonObject.getString("hinh");
                        DienThoai dt= new DienThoai(idproduct,nameproduct,price,manhinh,hdh, camerasau, cameratruoc, chip, ram, bnt, sim, pinsac,sum,idtype,hinh);
                        dienthoaiArrayList.add(dt);
   //                    Toast.makeText(getApplicationContext(), "" + dienthoaiArrayList.size(), Toast.LENGTH_SHORT).show();
                        categoryAdapter.notifyDataSetChanged();
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
        VolleySingleton.getInstance(this).getRequestQueue().add(stringRequest);
//        requestQueue.add(stringRequest);
    }



    public void AnhXa(){

        circleIndicator  = this.<CircleIndicator>findViewById(R.id.indicator);
        search=findViewById(R.id.image_search);
        editseacrchh=findViewById(R.id.search);
        recyclerView = this.<RecyclerView>findViewById(R.id.mylistview);
        viewPager = this.<ViewPager>findViewById(R.id.viewpager222);



       // item = (MenuItem) findViewById(R.id.Click_to_cart);
    }
//

    //Hide keyboard
    public void AnBanPhim() {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(DangNhap.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);

        }catch (NullPointerException ex)
        {ex.printStackTrace();}

    }
    private  static int userid;
    public int getUserIdByEmail(String putemail){
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.getUserByEmail, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Toast.makeText(getApplicationContext(), "xinchao"+response, Toast.LENGTH_LONG).show();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    int iduser = jsonObject.getInt("iduser");
                    userid=iduser;
                    SharedPreferences.Editor editor = getSharedPreferences(FILE_NAME, MODE_PRIVATE).edit();
                    editor.putInt("iduser",iduser);
                    editor.apply();

                    String nameuser = jsonObject.getString("nameuser");
                    String birthday  =  jsonObject.getString("birthday");
                    String phone = jsonObject.getString("phone");
                    String email = jsonObject.getString("email");
                    String gender = jsonObject.getString("gender");
                    String password = jsonObject.getString("password");
                    int typeuser = jsonObject.getInt("typeuser");

                    User user = new User(iduser, nameuser, birthday, phone, email, gender, password, typeuser);
//                    userid = (user.getIduser());

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
                param.put("email", putemail);

                return param;
            }
        };
//        requestQueue.add(stringRequest);
        VolleySingleton.getInstance(this).getRequestQueue().add(stringRequest);
        return userid;

    }

    public void icon_dienthoai(View view) {
        finish();
        Intent intent = new Intent(getApplicationContext(),DanhSachSP.class);
        startActivity(intent);

    }

    public void btngiohang(View view) {
        startActivity(new Intent(getApplicationContext(),GioHang.class));
    }

    public void xemtatca(View view) {
        startActivity(new Intent(getApplicationContext(),DanhSachSP.class));
    }

    private void autoslideImages()
    {
      if(integerList == null || integerList.isEmpty() || viewPager == null)
      {
          return ;
      }

      if(timer == null )
      {
          timer = new Timer();
      }
      timer.schedule(new TimerTask() {
          @Override
          public void run() {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    int  currentItem = viewPager.getCurrentItem();
                    int totalItems = integerList.size() - 1 ;
                    if(currentItem < totalItems)
                    {
                        currentItem++ ;
                        viewPager.setCurrentItem(currentItem);
                    }else
                    {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
          }
      },500,2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(timer != null)
        {
            timer.cancel();
            timer = null ;
        }
    }
}