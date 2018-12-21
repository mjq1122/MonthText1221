package presenter;

import com.bwei.monthtext1221.MainActivity;

import bases.BasePresenter;
import bases.IView;
import beans.GoodsBean;
import beans.SyNine;
import beans.SyPic;
import model.MainModel;
import model.MainModel.IMainModelCallBack1;
import view.MainView;

/**
 * Created by Lenovo on 2018/12/21.
 */

public class MainPresenter extends BasePresenter<MainView>{
    private MainModel mainModel;

    public MainPresenter(MainView iMainView) {
        super(iMainView);
    }

    @Override
    protected void initModel() {
        mainModel = new MainModel();
    }
    public void getPic(){
        mainModel.getPic(new MainModel.IMainModelCallBack1() {
            @Override
            public void getSuccess(SyPic syPic) {
                if(View!=null){
                    View.onSuccess(syPic);
                }
            }

            @Override
            public void getError(String error) {
                if(View!=null){
                    View.onFailed(error);
                }
            }
        });
    }

    public void getSyNine() {
        mainModel.getSyNine(new MainModel.IMainModelCallBack2() {

            @Override
            public void getError(String error) {
                if(View!=null){
                    View.onFailed(error);
                }
            }

            @Override
            public void getNineSuccess(SyNine syNine) {
                if(View!=null){
                    View.onNineSuccess(syNine);
                }
            }
        });
    }

    public void getList() {
        mainModel.getList(new MainModel.IMainModelCallBack3() {

            @Override
            public void getError(String error) {
                if(View!=null){
                    View.onFailed(error);
                }
            }

            @Override
            public void getListSuccess(GoodsBean goodsBean) {
                if(View!=null){
                    View.onListSuccess(goodsBean);
                }
            }

        });
    }
    public void ondestory(){
        if(mainModel!=null){
            mainModel=null;
        }
    }
}
