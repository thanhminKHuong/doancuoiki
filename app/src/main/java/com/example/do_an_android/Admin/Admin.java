package com.example.do_an_android.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.do_an_android.Activity.ThongTinCaNhan;
import com.example.do_an_android.Activity.TrangChu;
import com.example.do_an_android.R;

public class Admin extends AppCompatActivity {

    TextView txtdssp,txtdsloai,txtdsuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        AnhXa();
        txtdsuser.setClickable(false);
        txtdsuser.setVisibility(View.INVISIBLE);
        txtdssp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(getApplicationContext(), DanhSachSP_Admin.class);

                startActivity(intent);
            }
        });
        txtdsuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(getApplicationContext(), DanhSachUser_Admin.class);

                startActivity(intent);
            }
        });
        txtdsloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(getApplicationContext(), DanhSachLoaiSP_Admin.class);

                startActivity(intent);
            }
        });
    }






    public void AnhXa()
    {
        txtdssp=findViewById(R.id.txt_dsspadmin);
        txtdsloai=findViewById(R.id.txt_dsloaispadmin);
        txtdsuser=findViewById(R.id.txt_dsusersadmin);

    }
    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(getApplicationContext(), ThongTinCaNhan.class));
    }
}