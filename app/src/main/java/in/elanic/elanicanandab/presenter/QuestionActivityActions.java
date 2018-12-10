package in.elanic.elanicanandab.presenter;

import java.util.List;

import in.elanic.elanicanandab.model.QuestionModel;

/**
 * Created by Anand A <anandabktda@gmail.com/>
 * showProgress() and hideProgress() would be used for displaying and hiding the progressBar
 * while the setDataToRecyclerView and onResponseFailure
 */

public interface QuestionActivityActions {

    void showProgress();

    void hideProgress();

    void setDataToRecyclerView(List<QuestionModel.ItemsBean> noticeArrayList);

    void setBlankData();

    void onResponseFailure(Throwable throwable);
}
