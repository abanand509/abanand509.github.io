package in.elanic.elanicanandab.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static in.elanic.elanicanandab.util.ConstantStrings.BASE_URL;

public class RetrofitInstance {

    private static Retrofit retrofit;

    /**
     * Created by Anand A <anandabktda@gmail.com/>
     * Create an instance of Retrofit object
     * */

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}