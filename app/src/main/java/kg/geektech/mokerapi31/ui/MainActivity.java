package kg.geektech.mokerapi31.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

import kg.geektech.mokerapi31.adapter.MokerAdapter;
import kg.geektech.mokerapi31.data.MokerModel;
import kg.geektech.mokerapi31.databinding.ActivityMainBinding;
import kg.geektech.mokerapi31.network.BuilderRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MokerAdapter.Callback {

    private ActivityMainBinding binding;
    private final MokerAdapter adapter = new MokerAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recyclerView.setAdapter(adapter);

        getPosts();
        //createPosts(mokerModel);
        openSecondActivity();
    }




    private void openSecondActivity() {
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    private void createPosts(MokerModel mokerModel) {

        mokerModel = new MokerModel("post","second",2,4);

        BuilderRetrofit.getInstance().createMokerModel(mokerModel).enqueue(new Callback<MokerModel>() {
            @Override
            public void onResponse(Call<MokerModel> call, Response<MokerModel> response) {

            }

            @Override
            public void onFailure(Call<MokerModel> call, Throwable t) {

            }
        });
    }

    private void getPosts() {
        BuilderRetrofit.getInstance().getPosts().enqueue(new Callback<List<MokerModel>>() {
            @Override
            public void onResponse(Call<List<MokerModel>> call, Response<List<MokerModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("tag", "success " + response.body());
                    adapter.addItems(response.body());
                } else {
                    Log.d("tag", "error " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<MokerModel>> call, Throwable t) {

                Log.d("tag", "failure " + t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void Mokerclick(MokerModel mokerModel) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("id", "" + mokerModel.getId());
        intent.putExtra("title", mokerModel.getTitle());
        intent.putExtra("content", mokerModel.getContent());
        intent.putExtra("user", "" + mokerModel.getUser());
        intent.putExtra("group", "" + mokerModel.getGroup());
        startActivity(intent);
    }
}