package in.elanic.elanicanandab.network;

import java.util.Map;

import in.elanic.elanicanandab.model.QuestionModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Anand A <anandabktda@gmail.com/>
 * Built for requested APIs and parameters passing
 */

public interface GetQuestionDataService {

    @GET("questions")
    Call<QuestionModel> getNoticeData(
            @QueryMap Map<String, String> options
    );

}