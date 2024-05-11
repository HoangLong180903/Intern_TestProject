package hoanglong180903.myproject.internproject.api;

import java.util.List;

import hoanglong180903.myproject.internproject.model.Root;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/v1/geocode")
    Call<Root> getListMap(@Query("q") String q,
                          @Query("limit") int li,
                          @Query("apiKey") String key);


}
