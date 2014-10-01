package fr.doodz.openmv.app.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import fr.doodz.openmv.api.object.IInstallObject;
import fr.doodz.openmv.api.object.Upgraded;
import fr.doodz.openmv.app.R;

/**
 * Created by doods on 09/08/14.
 */
public class UpgradeAdapter extends BaseAdapter {
    private ArrayList<? extends IInstallObject> listData;

    private LayoutInflater layoutInflater;
    private SparseBooleanArray mSelectedItemsIds;

    public UpgradeAdapter(Context context, ArrayList<? extends IInstallObject> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
        mSelectedItemsIds = new SparseBooleanArray();
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
            convertView = layoutInflater.inflate(R.layout.list_adapter_apt, null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.Apt_Name);
            holder.description = (TextView) convertView.findViewById(R.id.Apt_Description);
            holder.maintainer = (TextView) convertView.findViewById(R.id.Apt_Maintainer);
            holder.repository = (TextView) convertView.findViewById(R.id.Apt_Repository);
            holder.size = (TextView) convertView.findViewById(R.id.Apt_Size);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        IInstallObject upgraded = listData.get(position);
        holder.name.setText(upgraded.getName());
        holder.description.setText(upgraded.getDescription());
        holder.maintainer.setText(upgraded.getMaintainer());
        holder.repository.setText(upgraded.getRepository());
        holder.size.setText(upgraded.getSize());
        convertView.setBackgroundColor(mSelectedItemsIds.get(position) ?
                convertView.getContext().getResources().getColor(R.color.color_omv)
                : Color.TRANSPARENT);
        return convertView;
    }

    public int getSelectedCount() {
        return mSelectedItemsIds.size();
    }

    public void toggleSelection(int position) {
        selectView(position, !mSelectedItemsIds.get(position));
    }

    public void selectView(int position, boolean value) {
        if (value)
            mSelectedItemsIds.put(position, value);
        else
            mSelectedItemsIds.delete(position);

        notifyDataSetChanged();
    }

    public SparseBooleanArray getSelectedIds() {
        return mSelectedItemsIds;
    }

    public void selectAll() {
        mSelectedItemsIds = new SparseBooleanArray();
        for (int i = 0; i < listData.size(); i++)
            mSelectedItemsIds.put(i, true);
        notifyDataSetChanged();
    }

    public void removeSelection() {
        mSelectedItemsIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    public void remove(Upgraded object) {
        // super.remove(object);
        listData.remove(object);
        notifyDataSetChanged();
    }

    static class ViewHolder {
        TextView name;
        TextView description;
        TextView maintainer;
        TextView repository;
        TextView size;
    }
}

