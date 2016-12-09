package me.cafetorres.cp_android.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Carlos on 08/12/2016.
 */

public interface PostalCodeService {
    @GET("/postalCodeSearchJSON")
    Call<PostalCodes> ListPostalcodes(@Query("country") String country,
                                      @Query("maxRows") Integer maxRows,
                                      @Query("username")String username,
                                      @Query("postalcode")String postalcode);
}
