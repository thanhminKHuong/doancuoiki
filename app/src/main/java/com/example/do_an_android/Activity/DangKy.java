package com.example.do_an_android.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.do_an_android.R;
import com.example.do_an_android.Util.CheckConnection;
import com.example.do_an_android.Util.Server;
import com.example.do_an_android.Util.VolleySingleton;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class DangKy extends AppCompatActivity {

    private EditText nameuser,email,password,Repassword;
    private TextView btnDK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangki);

        nameuser=(EditText) findViewById(R.id.txt_hotendangki);
        email=(EditText) findViewById(R.id.txt_emaildangki);
        password=(EditText) findViewById(R.id.txt_passworddangki);
        Repassword=(EditText) findViewById(R.id.txt_Repassworddangki);
        btnDK=(TextView)findViewById(R.id.btntrangdangki) ;
//        bấm vào nút đang kí
        btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CheckConnection.haveNetworkConnection(getApplicationContext())){
                    register();
                }else {
                    Toast.makeText(getApplicationContext(), "Vui lòng kiểm tra kết nối Internet !", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });
    }
    public void onLoginClick(View view){
        finish();
        startActivity(new Intent(this, DangNhap.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }

    public void register( ) {
        String nameuser = this.nameuser.getText().toString();
        String email = this.email.getText().toString();
        String password = this.password.getText().toString();
        String repass=this.Repassword.getText().toString();
        if(nameuser.equals("")||email.equals("")||password.equals("")||repass.equals("")){
            Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }else if(email.indexOf("@")<0){
            Toast.makeText(getApplicationContext(), "Email không đúng định dạng!", Toast.LENGTH_SHORT).show();
        }
        else if(password.equals(repass)!=true){
            Toast.makeText(getApplicationContext(), "Mật khẩu không trùng khớp!", Toast.LENGTH_SHORT).show();
            return;
        }else {
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.registeruser, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

//                if(response.equals("Done")){
                    Log.d("kiemmmtraaa","fdfsdfsdfsfsdfsdfsdfsd");
                    finish();
                    Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), DangNhap.class));
//                }
//                else if(response.equals("Error")) {
//                    Toast.makeText(getApplicationContext(), "Đăng ký không thành công" , Toast.LENGTH_SHORT).show();
//                }
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
                param.put("nameuser", nameuser);
                param.put("email", (email));
                param.put("password", MD5(password));
                return param;
            }
        };
            VolleySingleton.getInstance(this).getRequestQueue().add(stringRequest);
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