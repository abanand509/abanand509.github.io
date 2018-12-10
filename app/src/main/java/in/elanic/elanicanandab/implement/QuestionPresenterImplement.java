package in.elanic.elanicanandab.implement;

import java.util.List;

import in.elanic.elanicanandab.interactor.GetQuestionInteractor;
import in.elanic.elanicanandab.model.QuestionModel;
import in.elanic.elanicanandab.presenter.QuestionActivityActions;
import in.elanic.elanicanandab.presenter.QuestionActivityPresenter;

/**
 * Created by Anand A <anandabktda@gmail.com/>
 */

public class QuestionPresenterImplement implements QuestionActivityPresenter, GetQuestionInteractor.OnFinishedListener {

    private QuestionActivityActions questionActivityActions;
    private GetQuestionInteractor getQuestionInteractor;

    public QuestionPresenterImplement(QuestionActivityActions questionActivityActions, GetQuestionInteractor getQuestionInteractor) {
        this.questionActivityActions = questionActivityActions;
        this.getQuestionInteractor = getQuestionInteractor;
    }

    /**
     * search a string
     */
    @Override
    public void onSearchSubmit(String query) {
        if (questionActivityActions != null) {
            questionActivityActions.showProgress();
        }
        getQuestionInteractor.getQuestionArrayList(query, this);
    }

    /**
     * Handling response data
     */
    @Override
    public void requestDataFromServer(String query) {
        if (questionActivityActions != null) {
            questionActivityActions.showProgress();
        }
        getQuestionInteractor.getQuestionArrayList(query,this);
    }

    /**
     * successful response get
     */
    @Override
    public void onFinished(List<QuestionModel.ItemsBean> questionArrayList) {
        questionActivityActions.hideProgress();
        assert questionArrayList != null;
        if(questionArrayList.size()>0){
            questionActivityActions.setDataToRecyclerView(questionArrayList);
        }else
            questionActivityActions.setBlankData();
    }

    /**
     * response failure case
     */
    @Override
    public void onFailure(Throwable t) {
        if (questionActivityActions != null) {
            questionActivityActions.onResponseFailure(t);
            questionActivityActions.hideProgress();
        }
    }
}
