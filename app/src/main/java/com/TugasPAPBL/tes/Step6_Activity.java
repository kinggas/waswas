package com.TugasPAPBL.tes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.TugasPAPBL.tes.sqlite.DBHandler;
import com.TugasPAPBL.tes.sqlite.User;

public class Step6_Activity extends AppCompatActivity {

    DBHandler dbHandler;
    private Toolbar toolbar;
    private Button btn_lanjut;
    private ImageView img_info;
    private Context mContext;
    private Activity mActivity;
    private RelativeLayout mRelativeLayout;
    private PopupWindow mPopupWindow;
    private Spinner sp_lvl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step6_);

        //View Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Set ikon ke toolbar
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        sp_lvl = findViewById(R.id.list_levelact);
        dbHandler = new DBHandler(this);

        final Spinner List = findViewById(R.id.list_levelact);
        btn_lanjut = findViewById(R.id.btn_lanjut);
        btn_lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dtbs
                SQLiteDatabase db = dbHandler.getWritableDatabase();
                String query = "UPDATE " + User.UserDetails.TABLE_NAME + " SET " +
                        User.UserDetails.COL_LVL + " = '" + sp_lvl.getSelectedItem().toString() + "' WHERE id=1";
                db.execSQL(query);
                db.close();
                Toast.makeText(getApplicationContext(), "Oke~", Toast.LENGTH_SHORT).show();

                Intent kefinish = new Intent(getApplicationContext(), Finish_Activity.class);
                startActivity(kefinish);
            }
        });

        //DIALOG
        mContext = getApplicationContext();
        mActivity = Step6_Activity.this;

        mRelativeLayout = findViewById(R.id.step6_layout);
        img_info = findViewById(R.id.img_info);

        img_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                View customView = inflater.inflate(R.layout.dialog_layout, null);
                mPopupWindow = new PopupWindow(
                        customView, RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT
                );

                if (Build.VERSION.SDK_INT >= 21) {
                    mPopupWindow.setElevation(5.0f);
                }
                ImageButton close = customView.findViewById(R.id.ib_close);

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPopupWindow.dismiss();
                    }
                });
                mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER, 0, 0);
            }
        });
    }
}

