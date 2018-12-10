package in.elanic.elanicanandab.interactor;

import java.util.HashMap;
import java.util.Map;

import in.elanic.elanicanandab.model.QuestionModel;
import in.elanic.elanicanandab.network.GetQuestionDataService;
import in.elanic.elanicanandab.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static in.elanic.elanicanandab.util.ConstantStrings.PARAM_ACTIVITY;
import static in.elanic.elanicanandab.util.ConstantStrings.PARAM_DESC;
import static in.elanic.elanicanandab.util.ConstantStrings.PARAM_ORDER;
import static in.elanic.elanicanandab.util.ConstantStrings.PARAM_SITE;
import static in.elanic.elanicanandab.util.ConstantStrings.PARAM_SORT;
import static in.elanic.elanicanandab.util.ConstantStrings.PARAM_STACKOVERFLOW;
import static in.elanic.elanicanandab.util.ConstantStrings.PARAM_TAGGED;

/**
 * Created by Anand A
 */

public class GetQuestionInteractImplement implements GetQuestionInteractor {

    @Override
    public void getQuestionArrayList(String query, final OnFinishedListener onFinishedListener) {


        /** Create handle for the RetrofitInstance interface*/
        GetQuestionDataService service = RetrofitInstance.getRetrofitInstance().create(GetQuestionDataService.class);

        /** Call the method with parameter in the interface to get the notice data*/
        Call<QuestionModel> call = service.getNoticeData(getData(query));

        /**Log the URL called*/
        call.enqueue(new Callback<QuestionModel>() {

            @Override
            public void onResponse(Call<QuestionModel> call, Response<QuestionModel> response) {
                onFinishedListener.onFinished(response.body().getItems());
            }

            @Override
            public void onFailure(Call<QuestionModel> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }

    /** Giving all request parameters*/
    private Map<String, String> getData(String query){
        Map<String, String> data = new HashMap<>();
        data.put(PARAM_SITE, PARAM_STACKOVERFLOW);
        data.put(PARAM_TAGGED, query);
        data.put(PARAM_SORT, PARAM_ACTIVITY);
        data.put(PARAM_ORDER, PARAM_DESC);
        return data;
    }

}
