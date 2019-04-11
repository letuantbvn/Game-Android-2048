package com.example.game2048;

import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements setDataa {

    private GridView oluoi;
    private View.OnTouchListener listener; //khoi tao bien de vuot
    private float x, y; //khoi tao 2 bien de xet toa do.nhan vao toa do ma ng dung cham
    private OAdapter adapter;
    private TextView newgame, point, max;
    private InforUser i = new InforUser();
    boolean huy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newgame = (TextView) findViewById(R.id.newgame);
        newgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               newgame();

            }
        });
        anhxa();
        ktao();
        setData();

        i.getData(this);

    }


    private void anhxa() {
        oluoi = (GridView) findViewById(R.id.oluoi);
        point = findViewById(R.id.point);
        max = findViewById(R.id.max);

    }


    private void ktao() {
        huy = false;
        Datagame.getDatagame().khoitao(MainActivity.this);
        adapter = new OAdapter(MainActivity.this, 0, Datagame.getDatagame().getArrayList());
        //motionEvent tra ve nhan tha man hinh
        listener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (huy) {
                    return true;
                }
                if (Datagame.getDatagame().win) {
                    win();
                    return true;
                }
                Datagame.getDatagame().setQuayLai();
                switch (event.getAction()) { //lay ra su kien nguoi dung kich vao
                    case MotionEvent.ACTION_DOWN: //cham vao man hinh
                        //lay toa do ng dung nhap
                        x = event.getX();
                        y = event.getY();
                        break;
                    case MotionEvent.ACTION_UP: // su kien nhả
                        //bat su kien vuot ngang
                        if (Math.abs(event.getX() - x) > Math.abs(event.getY() - y)) {
                            //vuot phai
                            if (event.getX() < x) {
                                Datagame.getDatagame().vuotTrai();
                                adapter.notifyDataSetChanged();
                            }
                            if (event.getX()> x){
                                Datagame.getDatagame().vuotPhai();
                                adapter.notifyDataSetChanged();
                            }

                        }
                        if (Math.abs(event.getX()-x)< Math.abs(event.getY()-y)){
                            if (event.getY() < y) {
                                Datagame.getDatagame().vuotXuong();
                                adapter.notifyDataSetChanged();
                            }
                            if(event.getY() > y) {
                                Datagame.getDatagame().vuotLen();
                                adapter.notifyDataSetChanged();

                                //Toast.makeText(MainActivity.this, "vuot len", Toast.LENGTH_SHORT).show();
                            }
                        }
                        break;
                }
                point.setText("" + Datagame.getDatagame().point);

                if (i.point < Datagame.getDatagame().point) {
                    i.point = Datagame.getDatagame().point;
                    i.saveData(MainActivity.this);
                    max.setText("" + i.point);
                }
                if (Datagame.getDatagame().end) {
                    setdd();
                }
                return true;
            }

        };

        point.setText("" + Datagame.getDatagame().point);
        max.setText("" + i.point);
    }

    private void setData() {
        oluoi.setAdapter(adapter);
        oluoi.setOnTouchListener(listener);
        point.setText("" + Datagame.getDatagame().point);

    }


    public void setdd() {

        Toast.makeText(this, "Trò chơi kết thúc!", Toast.LENGTH_SHORT).show();

    }



    public void win() {
        if (Datagame.getDatagame().win) {
            new AlertDialog.Builder(this)
                    .setTitle("Bạn đã thắng")
                    .setMessage("Bạn có muốn chơi tiếp hay không ?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Datagame.getDatagame().voCuc = true;
                            Datagame.getDatagame().win = false;
                            huy = false;
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            huy = true;
                            setdd();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
                      Datagame.getDatagame().win = false;
        }
    }
    public void newgame(){

            new AlertDialog.Builder(this)
                    .setTitle("Thông báo")
                   .setMessage("Bạn có muốn bắt đầu lại hay không ?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            ktao();
                            setData();
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

    }

    public void quayLai(View view) {
        if (!huy) {
            Datagame.getDatagame().quayLai();
            adapter.notifyDataSetChanged();
        }
    }
}
