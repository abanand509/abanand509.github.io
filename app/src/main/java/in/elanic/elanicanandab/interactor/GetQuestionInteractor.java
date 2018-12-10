package in.elanic.elanicanandab.interactor;

import java.util.List;

import in.elanic.elanicanandab.model.QuestionModel;

/**
 * Created by Anand A <anandabktda@gmail.com/>
 * Intractors are classes built for fetching data from web services.
 */

public interface GetQuestionInteractor {

    interface OnFinishedListener {
        void onFinished(List<QuestionModel.ItemsBean> questionArrayList);

        void onFailure(Throwable t);
    }

    void getQuestionArrayList(String query, OnFinishedListener onFinishedListener);
}
