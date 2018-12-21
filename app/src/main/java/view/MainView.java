package view;

import bases.IView;
import beans.GoodsBean;
import beans.SyNine;
import beans.SyPic;

/**
 * Created by Lenovo on 2018/12/21.
 */

public interface MainView extends IView{
    void onSuccess(SyPic syPic);
    void onNineSuccess(SyNine syNine);
    void onListSuccess(GoodsBean goodsBean);
    void onFailed(String error);
}
