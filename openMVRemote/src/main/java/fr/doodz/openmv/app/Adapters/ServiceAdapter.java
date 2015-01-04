package fr.doodz.openmv.app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import fr.doodz.openmv.api.api.object.Service;
import fr.doodz.openmv.app.R;

/**
 * Created by doods on 29/07/14.
 */
public class ServiceAdapter extends BaseAdapter {
    private ArrayList<Service> listData;
    private Context mContext;
    private LayoutInflater layoutInflater;

    public ServiceAdapter(Context context, ArrayList<Service> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_adapter_test, null);
            holder = new ViewHolder();
            holder.titleView = (TextView) convertView.findViewById(R.id.text_Service_Name);
            holder.enabledView = (Switch) convertView.findViewById(R.id.switch_enable);
            holder.runningView = (RadioButton) convertView.findViewById(R.id.radio_Service_Running);
            holder.imageView= (ImageView) convertView.findViewById(R.id.image_Service);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Service service = listData.get(position);
        holder.titleView.setText(service.getTitle());
        holder.enabledView.setChecked(service.getEnabled());
        holder.enabledView.setClickable(false);
        holder.runningView.setChecked(service.getRunning());
        holder.runningView.setClickable(false);
        holder.imageView.setImageResource(this.getImage(service.getName()));
        return convertView;
    }

    static class ViewHolder {
        TextView titleView;
        Switch enabledView;
        RadioButton runningView;
        ImageView  imageView;
    }


    private int getImage(String name){
        String mDrawableName = "ic_"+name;
        int resID = this.mContext.getResources().getIdentifier(mDrawableName , "drawable", this.mContext.getPackageName());
        if(resID == 0)
        return R.drawable.ic_launcher;
        else
            return resID;
    }
}
