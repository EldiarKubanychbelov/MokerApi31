package kg.geektech.mokerapi31.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import kg.geektech.mokerapi31.data.MokerModel;
import kg.geektech.mokerapi31.databinding.ActitvitySecondBinding;
import kg.geektech.mokerapi31.network.BuilderRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity {

    ActitvitySecondBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActitvitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String getTitle = getIntent().getStringExtra("title");
        String getContent = getIntent().getStringExtra("content");
        String getUser = getIntent().getStringExtra("user");
        String getGroup = getIntent().getStringExtra("group");
        String getId = getIntent().getStringExtra("id");

        binding.editTitle.setText(getTitle);
        binding.editContent.setText(getContent);
        binding.editUser.setText(getUser);
        binding.editGroup.setText(getGroup);

        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BuilderRetrofit.getInstance().createMokerModel(new MokerModel(binding.editTitle.getText().toString().trim(), binding.editContent.getText().toString().trim(),
                        Integer.valueOf(binding.editUser.getText().toString().trim()),Integer.valueOf(binding.editGroup.getText().toString().trim()) )).enqueue(new Callback<MokerModel>() {
                    @Override
                    public void onResponse(Call<MokerModel> call, Response<MokerModel> response) {

                    }

                    @Override
                    public void onFailure(Call<MokerModel> call, Throwable t) {

                    }
                });

                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }

        });

        binding.btnUpgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BuilderRetrofit.getInstance().upgrade(""+getId, new MokerModel(binding.editTitle.getText().toString().trim(), binding.editContent.getText().toString().trim(),
                        Integer.valueOf(binding.editUser.getText().toString().trim()),Integer.valueOf(binding.editGroup.getText().toString().trim()))).enqueue(new Callback<MokerModel>() {
                    @Override
                    public void onResponse(Call<MokerModel> call, Response<MokerModel> response) {

                    }

                    @Override
                    public void onFailure(Call<MokerModel> call, Throwable t) {

                    }
                });

                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
