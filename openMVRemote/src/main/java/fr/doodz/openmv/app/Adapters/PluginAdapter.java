package fr.doodz.openmv.app.Adapters;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import fr.doodz.openmv.api.api.object.IInstallObject;
import fr.doodz.openmv.api.api.object.Upgraded;
import fr.doodz.openmv.app.R;

/**
 * Created by doods on 12/10/2014.
 */
public class PluginAdapter extends BaseAdapter {
    private ArrayList<? extends IInstallObject> listData;
    private Context mContext;
    private LayoutInflater layoutInflater;
    private SparseBooleanArray mSelectedItemsIds;

    public PluginAdapter(Context context,ArrayList<? extends IInstallObject> listData) {
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
            convertView = layoutInflater.inflate(R.layout.list_adapter_plugin, null);
            holder = new ViewHolder();
            holder.nameView = (TextView) convertView.findViewById(R.id.text_Plugin_Name);
            holder.siseView = (TextView) convertView.findViewById(R.id.text_Plugin_Size);
            holder.maintainerView = (TextView) convertView.findViewById(R.id.text_Plugin_Maintainer);
            holder.repositoryView = (TextView) convertView.findViewById(R.id.text_Plugin_Repository);
            holder.descriptionView = (TextView) convertView.findViewById(R.id.text_Plugin_Description);
            holder.instaledView= (ImageView) convertView.findViewById(R.id.image_Plugin_Instaled);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        IInstallObject plugins = listData.get(position);
        holder.nameView.setText(plugins.getName());
        holder.siseView.setText(""+plugins.getSize());
        holder.maintainerView.setText(plugins.getMaintainer());
        holder.repositoryView.setText(plugins.getRepository());
        holder.descriptionView.setText(plugins.getDescription());

        holder.instaledView.setImageResource(this.getImage(plugins.getInstalled()));
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
        TextView nameView;
        TextView siseView;
        TextView maintainerView;
        TextView  repositoryView;
        TextView descriptionView;
        ImageView instaledView;
    }


    private int getImage(boolean installed){

        if(installed)
        return this.mContext.getResources().getIdentifier("ic_yes" , "drawable", this.mContext.getPackageName());
        else
            return 0;
    }
}
