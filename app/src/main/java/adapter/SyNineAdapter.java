package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.monthtext1221.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import app.MyApp;
import beans.SyNine;

/**
 * Created by Lenovo on 2018/12/21.
 */

public class SyNineAdapter extends RecyclerView.Adapter<SyNineAdapter.ViewHolder>{
    private List<SyNine.DataBean> list;
    private Context context;

    public SyNineAdapter(List<SyNine.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @Override
    public SyNineAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_nine, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(SyNineAdapter.ViewHolder holder, int position) {
        holder.text_title.setText(list.get(position).getName());
        ImageLoader.getInstance().displayImage(list.get(position).getIcon(),holder.img_pic,MyApp.getOptions());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView text_title;
        private ImageView img_pic;

        public ViewHolder(View itemView) {
            super(itemView);
            text_title=itemView.findViewById(R.id.text_title);
            img_pic=itemView.findViewById(R.id.img_pic);
        }
    }
}
