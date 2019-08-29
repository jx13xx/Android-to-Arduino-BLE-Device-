package com.dfrobot.angelo.blunobasicdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MedineSearch_Adapter extends ArrayAdapter<Medicine_list> {

    private List<Medicine_list> plists;
    private Context context;

    //Constructor
    public MedineSearch_Adapter(Context context, int resource, List<Medicine_list> plists){
        super(context, resource, plists);
        this.context = context;
        this.plists = plists;
    }

    @Override
    public View getView(int position , View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listViewItem = inflater.inflate(R.layout.medicine_viewdetails, null, true);

        TextView medname = (TextView) listViewItem.findViewById(R.id.med_details);
        TextView med_expiry = (TextView) listViewItem.findViewById(R.id.med_expiry);
        TextView med_count = (TextView) listViewItem.findViewById(R.id.med_count);

        Medicine_list val = plists.get(position);
        medname.setText(val.getMed_name());
        med_expiry.setText(val.getMed_expiry());
        med_count.setText(val.getMed_value());

        return listViewItem;





    }







}
