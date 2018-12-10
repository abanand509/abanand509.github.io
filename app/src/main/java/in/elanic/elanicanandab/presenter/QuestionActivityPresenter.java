package in.elanic.elanicanandab.presenter;

/**
 * Created by Anand A <anandabktda@gmail.com/>
 * Call when user interact with the view
 * Using for input actions
 */

public interface QuestionActivityPresenter {

    void onSearchSubmit(String query);

    void requestDataFromServer(String query);
}
