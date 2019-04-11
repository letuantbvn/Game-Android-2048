package com.example.game2048;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class OAdapter extends ArrayAdapter<Integer> {
    private Context context;
    private ArrayList<Integer> arr;


    public OAdapter(Context context, int resource, List<Integer> objects) {
        super(context, resource, objects);
        this.context=context;
        this.arr=new ArrayList<>(objects);
    }

    public void notifyDataSetChanged(){
        arr=Datagame.getDatagame().getArrayList();
        super.notifyDataSetChanged();
    }
    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
       if (convertView==null){
           LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           convertView=inflater.inflate(R.layout.item,null);

       }
       if (arr.size()>0){
           ovuong o=(ovuong) convertView.findViewById(R.id.tvovuong);  //anh xa
           o.setT(arr.get(position));
       }
       return convertView;

    }

}
