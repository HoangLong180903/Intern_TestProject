package hoanglong180903.myproject.internproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hoanglong180903.myproject.internproject.R;
import hoanglong180903.myproject.internproject.adapter.MapAdapter;
import hoanglong180903.myproject.internproject.api.ApiRetrofit;
import hoanglong180903.myproject.internproject.api.ApiService;
import hoanglong180903.myproject.internproject.databinding.ActivityMainBinding;
import hoanglong180903.myproject.internproject.interface_adapter.OnClickItemAdapter;
import hoanglong180903.myproject.internproject.model.Location;
import hoanglong180903.myproject.internproject.model.Root;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    MapAdapter adapter;
    List<Location> locationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        searchMap();
        initVariable();
    }

    private void initVariable() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        binding.mainRcv.setLayoutManager(manager);
        locationList = new ArrayList<>();
        adapter = new MapAdapter(MainActivity.this, locationList);
        binding.mainRcv.setAdapter(adapter);
    }

    private void searchMap() {
        binding.mainEdSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().trim().isEmpty()) {
                    getApi(s.toString());
                } else {
                    getApi("");
                }
            }
        });
    }

    private void getApi(String q) {
        ApiService apiService = ApiRetrofit.getApiService();
        Call<Root> call = apiService.getListMap(q, 4, getResources().getString(R.string.api_key));
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (response.isSuccessful()) {
                    Root root = response.body();
                    locationList = root.getItems();
                    adapter = new MapAdapter(MainActivity.this, locationList);
                    adapter.onClickItemLocation(new OnClickItemAdapter() {
                        @Override
                        public void onClickItemLocation(double lat, double lng) {
                            // find location auto
                            Intent intent = new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("google.navigation:q=" + lat + "," + lng+"&mode=1"));
                            intent.setPackage("com.google.android.apps.maps");
                            if (intent.resolveActivity(getPackageManager()) != null){
                                startActivity(intent);
                            }

//                            Uri gmmIntentUri = Uri.parse("geo:0,0?q=Google+Sydney@"+lat+","+lng+"");
//                            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//                            mapIntent.setPackage("com.google.android.apps.maps");
//                            startActivity(mapIntent);

                        }
                    });
                    binding.mainRcv.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {

            }
        });
    }

}