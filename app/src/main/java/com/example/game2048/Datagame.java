package com.example.game2048;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.Random;

public class Datagame {
    private static Datagame datagame;

    private ArrayList<Integer> arrayList = new ArrayList<>();
    private int[] mangMau;
    private int[][] manghaichieu = new int[4][4];
    private int[][] mangQuayLai = new int[4][4];
    private Random r = new Random();
    public int point;
    public boolean win,end,voCuc,newgame, dau;

    static {
        datagame = new Datagame();
    }

    public static Datagame getDatagame() {
        return datagame;
    }
    //khoi tao diem


    //khoi tao hien thi
    public void khoitao(Context context) {
        win = false;
        end = false;
        voCuc =false;
        dau=true;
        point = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                manghaichieu[i][j] = 0;
                mangQuayLai[i][j] = 0;
                arrayList.add(0);
            }
        }
        TypedArray ta = context.getResources().obtainTypedArray(R.array.mauso);//tro den thu muc res
        mangMau = new int[ta.length()];  //lau so phan tu cua mau
        for (int i = 0; i < ta.length(); i++) {
            mangMau[i] = ta.getColor(i, 0);
        }
        ta.recycle(); //dong mang mau
        taoSo();
        chuyendoi();
    }

    public ArrayList<Integer> getArrayList() {
        return arrayList;
    }

    public int colorr(int so) {
        if (so == 0) {
            return Color.WHITE;
        } else {
            int a = (int) (Math.log(so) / Math.log(2)); //chia ra so mu
            return mangMau[a - 1]; //vi tri 1 là vi tri cua mau 4,lay mau cua 2 là a-1
        }
    }
public void checkThua(){
    end =true;
    for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 4; j++) {
            if(manghaichieu[i][j]==0){
                end = false;
                break;
            }
        }
    }
    if(end){
        end =true;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if(manghaichieu[i][j]==manghaichieu[i][j+1]){
                    end = false;
                    break;
                }
            }
        }
        if(end){
            end =true;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 3; j++) {
                    if(manghaichieu[j][i]==manghaichieu[j+1][i]){
                        end = false;
                        break;
                    }
                }
            }
        }
    }
}
    public void taoSo() {


        int so0 = 0;
        for (int i = 0; i < 16; i++) {
            if (arrayList.get(i) == 0) {
                so0++;
            }
        }
        int soOTao = 0;
        if (so0 > 1) {
            soOTao = r.nextInt(1) + 1;//tạo ta 1 den 2 so(chỉ tạo ra 1 số)
            if (dau){
                dau=false;
                soOTao=2;
            }
        } else if (so0 == 1) {
            soOTao = 1;
        } else if (so0 == 0) {
            soOTao = 0;
        }
        checkThua();
        while (soOTao != 0) {//chay cho den khi so o tao =0 thi dung
            int i = r.nextInt(4), j = r.nextInt(4);
            if (manghaichieu[i][j] == 0) {
                int k = r.nextInt(2) + 1;
                    k=k*2;
                    manghaichieu[i][j] = k;
                    soOTao--;
            }
        }

    }


    public void intt() {
        taoSo();
        arrayList.clear();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arrayList.add(manghaichieu[i][j]);
            }
        }
    }

    public int[][] getManghaichieu() {
        return manghaichieu;
    }

    public void chuyendoi() {
        arrayList.clear();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(manghaichieu[i][j]>=2048&&!voCuc){
                    win = true;


                }
                arrayList.add(manghaichieu[i][j]);

            }
        }
    }

    public void vuotTrai() {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int so = manghaichieu[i][j];
                if (so == 0) {
                    continue;
                } else {
                    for (int k = j + 1; k < 4; k++) {
                        int sok = manghaichieu[i][k];
                        if (sok == 0) {
                            continue;
                        } else if (sok == so) {
                            manghaichieu[i][j] = so * 2;////////
                            point = point +so*2;
                            manghaichieu[i][k] = 0;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int so = manghaichieu[i][j];
                if (so == 0) {
                    for (int k = j + 1; k < 4; k++) {
                        int so1 = manghaichieu[i][k];
                        if (so1 == 0) {
                            continue;
                        } else {
                            manghaichieu[i][j] = manghaichieu[i][k];
                            manghaichieu[i][k] = 0;
                            break;
                        }
                    }
                }
            }
        }
        taoSo();
        chuyendoi();
    }

    public void vuotPhai() {

        for (int i = 3; i >= 0; i--) {
            for (int j = 3; j >= 0; j--) {
                int so = manghaichieu[i][j];
                if (so == 0) {
                    continue;
                } else {
                    for (int k = j - 1; k >= 0; k--) {
                        int sok = manghaichieu[i][k];
                        if (sok == 0) {
                            continue;
                        } else if (sok == so) {
                            manghaichieu[i][j] = so * 2;
                            point = point +so*2;
                            manghaichieu[i][k] = 0;

                        } else {
                            break;
                        }
                    }

                }
            }
        }
        for (int i = 3; i >= 0; i--) {
            for (int j = 3; j >= 0; j--) {
                int so = manghaichieu[i][j];
                if (so == 0) {
                    for (int k = j - 1; k >= 0; k--) {
                        int so1 = manghaichieu[i][k];
                        if (so1 == 0) {
                            continue;
                        } else {
                            manghaichieu[i][j] = manghaichieu[i][k];
                            manghaichieu[i][k] = 0;

                            break;
                        }
                    }

                }
            }
        }
        taoSo();
        chuyendoi();
    }

    public void vuotXuong() {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int so = manghaichieu[j][i];
                if (so == 0) {
                    continue;
                } else {
                    for (int k = j + 1; k < 4; k++) {
                        int sok = manghaichieu[k][i];
                        if (sok == 0) {
                            continue;
                        } else if (sok == so) {
                            manghaichieu[j][i] = so * 2;
                            point = point +so*2;
                            manghaichieu[k][i] = 0;


                        } else {
                            break;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int so = manghaichieu[j][i];
                if (so == 0) {
                    for (int k = j + 1; k < 4; k++) {
                        int so1 = manghaichieu[k][i];
                        if (so1 == 0) {
                            continue;
                        } else {
                            manghaichieu[j][i] = manghaichieu[k][i];
                            manghaichieu[k][i] = 0;

                            break;
                        }
                    }

                }
            }
        }
        taoSo();
        chuyendoi();
    }

    public void vuotLen() {
        for (int i = 3; i >= 0; i--) {
            for (int j = 3; j >= 0; j--) {
                int so = manghaichieu[j][i];
                if (so == 0) {
                    continue;
                } else {
                    for (int k = j - 1; k >= 0; k--) {
                        int sok = manghaichieu[k][i];
                        if (sok == 0) {
                            continue;
                        } else if (sok == so) {
                            manghaichieu[j][i] = so * 2;
                            point = point +so*2;
                            manghaichieu[k][i] = 0;

                            break;

                        } else {
                            break;
                        }
                    }
                }
            }
        }
        for (int i = 3; i >= 0; i--) {
            for (int j = 3; j >= 0; j--) {
                int so = manghaichieu[j][i];
                if (so == 0) {
                    for (int k = j - 1; k >= 0; k--) {
                        int so1 = manghaichieu[k][i];
                        if (so1 == 0) {
                            continue;
                        } else {
                            manghaichieu[j][i] = manghaichieu[k][i];
                            manghaichieu[k][i] = 0;

                            break;
                        }
                    }

                }
            }
        }
        taoSo();
        chuyendoi();
    }


    public void setQuayLai(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                mangQuayLai[j][i]= manghaichieu[i][j];
            }
    }}
    public void quayLai() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                manghaichieu[j][i]= mangQuayLai[i][j];

            }
        }
        arrayList.clear();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arrayList.add(manghaichieu[i][j]);
            }
        }
    }
}
