package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwei.monthtext1221.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import app.MyApp;
import beans.GoodsBean;
import beans.SyNine;

/**
 * Created by Lenovo on 2018/12/21.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{
    private List<GoodsBean.DataBean> list;
    private Context context;

    public ListAdapter(List<GoodsBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_list, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.list_title.setText(list.get(position).getTitle()+"   ");
        holder.list_price.setText(list.get(position).getPrice()+"   ");
        //切割图片
        String images = list.get(position).getImages();//得到图片集
        String[] split = images.split("\\|");//得到一个图片
        if (split.length>0) {
            //将https成http  进行联网显示
            String replace = split[0].replace("https", "http");
            Glide.with(context).load(replace).into(holder.list_img);//设置图片
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemListener.success(" -地址"+list.get(position).getDetailUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView list_title,list_price;
        private ImageView list_img;

        public ViewHolder(View itemView) {
            super(itemView);
            list_title=itemView.findViewById(R.id.list_title);
            list_price=itemView.findViewById(R.id.list_price);
            list_img=itemView.findViewById(R.id.list_img);
        }
    }
    private ItemListener itemListener;
    public interface ItemListener{
        void success(String data);
    };
    public void setOnItemListener2(ItemListener itemListeners){
        itemListener=itemListeners;
    }
}
