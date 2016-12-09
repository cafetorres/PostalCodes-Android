package me.cafetorres.cp_android.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Carlos on 08/12/2016.
 */

public class PostalCodeClient {
    Retrofit retrofit;
    private final static String BASE_URL="http://api.geonames.org/";

    public PostalCodeClient(){
        this.retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public PostalCodeService getPostalCodeService(){
        return this.retrofit.create(PostalCodeService.class);
    }
}
