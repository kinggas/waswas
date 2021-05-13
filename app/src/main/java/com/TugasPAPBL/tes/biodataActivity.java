package com.TugasPAPBL.tes;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class biodataActivity extends AppCompatActivity {
    private Button btn;
    private FragmentManager frgManager;
    private FragmentTransaction frgtransaction;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    startActivity(new Intent(biodataActivity.this, MainActivity.class));
                    return true;
                case R.id.navigation_dashboard:
                    startActivity(new Intent(biodataActivity.this, ResepActivity.class));
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biodata);
        btn = findViewById(R.id.btn_editBiodata);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.getMenu().getItem(2).setChecked(true);

        FragmentBiodata fragmentBiodata = new FragmentBiodata();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(biodataActivity.this, editBiodataActivity.class);
                startActivity(intent);
            }
        });
        frgManager = getSupportFragmentManager();
        frgtransaction = frgManager.beginTransaction();
        Bundle bundle = new Bundle();
        fragmentBiodata.setArguments(bundle);
        frgtransaction.add(R.id.containerBiodata, fragmentBiodata);
        frgtransaction.addToBackStack(null);
        frgtransaction.commit();

    }
}
