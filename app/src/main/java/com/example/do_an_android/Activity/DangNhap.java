package com.example.do_an_android.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.do_an_android.Model.User;
import com.example.do_an_android.R;
import com.example.do_an_android.Util.CheckConnection;
import com.example.do_an_android.Util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class DangNhap extends AppCompatActivity {

    EditText email,password;
    ImageView chuyentrangdangki1;
    TextView txtdangki,bnt_dangnhap;
    CheckBox remember_me;

    private static final int MY_SOCKET_TIMEOUT_MS = 50000 ;

    private static final String FILE_NAME = "myFile";
    private static final String FILE_SAVE = "savePass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        anhxa();
            bnt_dangnhap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (CheckConnection.haveNetworkConnection(getApplicationContext())){
                        event();
                    }else {
                        Toast.makeText(getApplicationContext(), "Vui lòng kiểm tra kết nối Internet !", Toast.LENGTH_SHORT).show();
                        return;
                    }

                }
            });
        txtdangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(), DangKy.class));
            }
        });
        chuyentrangdangki1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(), DangKy.class));
            }
        });
    }

    public void anhxa() {
        chuyentrangdangki1 = findViewById(R.id.chuyentrangdangki1);
        txtdangki = findViewById(R.id.txt_dangkidn);
        bnt_dangnhap = findViewById(R.id.btndangnhap);
        email = findViewById(R.id.edtEmail_Dangnhap);
        password = findViewById(R.id.edtPassword_Dangnhap);
        remember_me = findViewById(R.id.remember_me);
        remember_me.setChecked(false);
        //Auto điền pw email sau khi logout
        SharedPreferences sharedPreferences = getSharedPreferences(FILE_SAVE, MODE_PRIVATE);
        String edittext_phone = sharedPreferences.getString("edittext_phone", "");
        String edittext_password = sharedPreferences.getString("edittext_password", "");

        email.setText(edittext_phone);
        password.setText(edittext_password);
    }

    private void event() {
        txtdangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(), DangKy.class));
            }
        });
        chuyentrangdangki1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(), DangKy.class));
            }
        });
//        nút đăng nhập nè
        bnt_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login( );
            }
        });
    }




    public void login( ) {
        String email = this.email.getText().toString().trim();
        String password = (this.password.getText().toString().trim());
        
        if (email.equals("") || password.equals("")) {
            Toast.makeText(getApplicationContext(), "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        } else {
//            để đẩy dữ liệu trên visula
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.login, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("ddddd",response);
                        String s1 = response ;
//                    if (response.equals("1")) {
//                        finish();
//                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), TrangChu.class);
                        Remember(email,password);
//                        viết tên vào file file_name
                        SharedPreferences.Editor editor = getSharedPreferences(FILE_NAME, MODE_PRIVATE).edit();
                        editor.putString("email",email);
                        editor.apply();
                        startActivity(intent);
                        finish();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Lỗi " + error.toString(), Toast.LENGTH_SHORT).show();
                }
            })

            {

//                put email , password gửi lên
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> param = new HashMap<String, String>();
                    param.put("email", email);
                    param.put("password", MD5(password));
                    return param;
                }
            };

//            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
//                   5000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
//            ));

            requestQueue.add(stringRequest);
        }
    }

    private void Remember(String edittext_phone,String edittext_password) {
        if (remember_me.isChecked()){
            SharedPreferences.Editor editor = getSharedPreferences(FILE_SAVE, MODE_PRIVATE).edit();
            editor.putString("edittext_phone",edittext_phone);
            editor.putString("edittext_password",edittext_password);
            editor.apply();
        }else {
            SharedPreferences.Editor editor = getSharedPreferences(FILE_SAVE, MODE_PRIVATE).edit();
            editor.putString("edittext_phone","");
            editor.putString("edittext_password","");
            editor.apply();
        }
    }

    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        } catch(UnsupportedEncodingException ex){
        }
        return null;
    }
}