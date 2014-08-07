package fr.doodz.openmv.app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import fr.doodz.openmv.api.object.Service;
import fr.doodz.openmv.app.R;

/**
 * Created by doods on 29/07/14.
 */
public class ServiceAdapter  extends BaseAdapter {
    private ArrayList<Service> listData;

    private LayoutInflater layoutInflater;

    public ServiceAdapter(Context context, ArrayList<Service> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
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
            convertView = layoutInflater.inflate(R.layout.list_adapter_services, null);
            holder = new ViewHolder();
            holder.titleView = (TextView) convertView.findViewById(R.id.tvtitle);
            holder.enabledView = (Switch) convertView.findViewById(R.id.sEnabled);
            holder.runningView = (RadioButton) convertView.findViewById(R.id.rbRunning);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Service service = listData.get(position);
        holder.titleView.setText(service.Title);
        holder.enabledView.setChecked(service.Enabled);
        holder.enabledView.setClickable(false);
        holder.runningView.setChecked(service.Running);
        holder.runningView.setClickable(false);

        return convertView;
    }

    static class ViewHolder {
        TextView titleView;
        Switch enabledView;
        RadioButton runningView;
    }
}
