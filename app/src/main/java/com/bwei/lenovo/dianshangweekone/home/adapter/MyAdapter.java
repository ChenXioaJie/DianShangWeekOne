package com.bwei.lenovo.dianshangweekone.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.lenovo.dianshangweekone.R;
import com.bwei.lenovo.dianshangweekone.home.bean.MyBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class MyAdapter extends BaseAdapter {

   private Context context;
    private List<MyBean.DataBean.BannerBean> list;

    public MyAdapter(Context context, List<MyBean.DataBean.BannerBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = new ViewHolder();
        if (convertView ==null){
            convertView = View.inflate(context,R.layout.item_grid,null);
            holder.i1 = convertView.findViewById(R.id.i1);
            holder.tt1 = convertView.findViewById(R.id.t1);
            holder.tt2 = convertView.findViewById(R.id.t2);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tt1.setText(list.get(position).getTitle());
        holder.tt2.setText(list.get(position).getTitle());
        ImageLoader.getInstance().displayImage(list.get(position).getUrl(),holder.i1);

        return convertView;
    }

    class ViewHolder{
        TextView tt1,tt2;
        ImageView i1;
    }

}
