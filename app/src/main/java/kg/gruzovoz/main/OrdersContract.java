package kg.gruzovoz.main;

import java.util.List;

import kg.gruzovoz.models.Order;
import kg.gruzovoz.BaseContract;
public interface OrdersContract {

    interface View extends BaseContract.BaseView{
        void logOut();
        void openHistoryScreen();
        void showDetailScreen(long id);

    }

    interface Presenter extends BaseContract.BasePresenter{

    }
}
