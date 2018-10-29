package id.ac.amikom.katalogfilm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView mView;
    Adapter adapter;
    List<Result> results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mView = (RecyclerView)findViewById(R.id.filmView);
        mView.setLayoutManager(new GridLayoutManager(MainActivity.this,3));

        filmLoad();

    }

    private void  filmLoad(){
        Interface api = Client.getRetrofit().create(Interface.class);
        Call<Film> call = api.getPopular();
        call.enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {
                Film film = response.body();
                adapter = new Adapter(results);
                adapter.setData(film.getResults());
                mView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Film> call, Throwable t) {

            }
        });
    }

}
