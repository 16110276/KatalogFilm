package id.ac.amikom.katalogfilm;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Interface {

    public static String API_KEY = "110e0a9fb92aefec6eb897cc1b88af62";

    @GET("popular?api_key="+API_KEY)
    Call<Film> getPopular();

    @GET("top_rated?api_key="+API_KEY)
    Call<Film> getTopRated();


}
