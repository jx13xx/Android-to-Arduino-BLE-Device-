package com.dfrobot.angelo.blunobasicdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class GetPill_Adapter extends ArrayAdapter<Medicine_list> {

    private List<Medicine_list> plists;
    private Context context;


    //Constructor
    public GetPill_Adapter(Context context, int resource, List<Medicine_list> plists){
        super(context, resource, plists);
        this.context = context;
        this.plists = plists;


    }

    @Override
    public View getView(int position , View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listViewItem = inflater.inflate(R.layout.get_pill, null, true);
        Button scan = (Button) listViewItem.findViewById(R.id.buttonScan);
        TextView medname = (TextView) listViewItem.findViewById(R.id.pillname);
        //TextView med_expiry = (TextView) listViewItem.findViewById(R.id.med_expiry);
        TextView med_count = (TextView) listViewItem.findViewById(R.id.pill_qty);

        Medicine_list val = plists.get(position);
        medname.setText(val.getMed_name());
       // med_expiry.setText(val.getMed_expiry());
        med_count.setText(val.getMed_value() +" Pills Left");
        med_count.setText("");


        return listViewItem;





    }







}
