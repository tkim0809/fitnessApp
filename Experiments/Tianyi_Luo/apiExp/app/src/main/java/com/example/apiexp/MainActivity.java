package com.example.apiexp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.annotations.SerializedName;

import kotlin.jvm.internal.SerializedIr;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView bodytext = findViewById(R.id.apiText);
        TextView textId = findViewById(R.id.textId);
        TextView textTitle = findViewById(R.id.textTitle);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PostApi apiClient = retrofit.create(PostApi.class);
        apiClient.getFirstPost().enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                bodytext.setText(response.body().getText());

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }
}
interface PostApi{
    @GET("posts/1")
    Call<Post> getFirstPost();
}
class Post{
    private int userId;
    private int id;
    private String title;
    @SerializedName("body")
    private String Text;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }
}