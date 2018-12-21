package bases;

/**
 * Created by Lenovo on 2018/12/21.
 */

public abstract class BasePresenter<V extends IView>{
    protected V View;
    public BasePresenter(V View) {
        this.View = View;
        initModel();
    }

    protected abstract void initModel();
    void onDestory(){
        View=null;
    }
}
