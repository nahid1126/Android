package com.example.retrofit_2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.retrofit_2.Model.Comment;
import com.example.retrofit_2.Model.Post;
import com.example.retrofit_2.api.ApiClient;
import com.example.retrofit_2.api.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView;
    TextView textViewTwo;
    TextView textViewThree;
    Button btnPost;
    Button btnComments;
    Button btnPostRequ;

    ApiInterface apiInterface;
    private final int RESPONSE_OK = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.txtShow);
        textViewTwo = findViewById(R.id.txtShowTwo);
        textViewThree = findViewById(R.id.txtShowThree);
        btnComments = findViewById(R.id.getComments);
        btnPost = findViewById(R.id.getPost);
        btnPostRequ = findViewById(R.id.postRequ);


        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        btnPost.setOnClickListener(this);
        btnComments.setOnClickListener(this);
        btnPostRequ.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.getPost) {
           /* Toast.makeText(this, "Post Button clicked", Toast.LENGTH_SHORT).show();
            Call<List<Post>> call = apiInterface.getPosts(4, "id", "desc");
            call.enqueue(new Callback<List<Post>>() {
                @Override
                public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                    if (response.isSuccessful()) {
                        List<Post> posts = response.body();

                        Toast.makeText(MainActivity.this, "" + posts.size(),
                                Toast.LENGTH_SHORT).show();
                        for (Post post : posts) {
                            String content = "";
                            content += "User ID: " + post.getUserId() + "\n";
                            content += "ID: " + post.getId() + "\n";
                            content += "Title: " + post.getTitle() + "\n";
                            content += "Text: " + post.getBody() + "\n\n";

                            textView.append(content);
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Failed" + response.message(),
                                Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<Post>> call, Throwable t) {
                    textView.setText(t.getMessage());
                }
            });*/
            Post post = new Post(10, null, "put text");
            Call<Post> call = apiInterface.putPost(6, post);

            call.enqueue(new Callback<Post>() {
                @Override
                public void onResponse(Call<Post> call, Response<Post> response) {

                    if (response.isSuccessful() && response.code() == RESPONSE_OK) {
                        Post putPost = response.body();

                        String content = "";
                        content += "Code: " + response.code();
                        content += "User ID: " + putPost.getUserId() + "\n";
                        content += "ID: " + putPost.getId() + "\n";
                        content += "Title: " + putPost.getTitle() + "\n";
                        content += "Text: " + putPost.getBody() + "\n\n";

                        textView.append(content);
                    }
                }

                @Override
                public void onFailure(Call<Post> call, Throwable t) {

                }
            });
        } else if (v.getId() == R.id.getComments) {
            Toast.makeText(this, "Comment Button clicked", Toast.LENGTH_SHORT).show();
            Call<List<Comment>> call = apiInterface.getComments();
            call.enqueue(new Callback<List<Comment>>() {
                @Override
                public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                    if (response.isSuccessful()) {
                        List<Comment> comments = response.body();

                        Toast.makeText(MainActivity.this, "" + comments.size(),
                                Toast.LENGTH_SHORT).show();
                        for (Comment comment : comments) {
                            String content1 = "";
                            content1 += "Post ID: " + comment.getPostId() + "\n";
                            content1 += "ID: " + comment.getId() + "\n";
                            content1 += "Name: " + comment.getName() + "\n";
                            content1 += "Email: " + comment.getEmail() + "\n";
                            content1 += "Text: " + comment.getText() + "\n\n";

                            textViewTwo.append(content1);
                        }

                    } else {
                        Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT)
                                .show();
                    }
                }

                @Override
                public void onFailure(Call<List<Comment>> call, Throwable t) {
                    textViewTwo.setText(t.getMessage());
                }
            });
        } else {
            Post post = new Post(26,
                    "Post Request", "This is post Request");

            Call<Post> call = apiInterface.createPosts(post);
            call.enqueue(new Callback<Post>() {
                @Override
                public void onResponse(Call<Post> call, Response<Post> response) {
                    Post postRequ = response.body();

                    String content = "";
                    content += "Code: " + response.code() + "\n";
                    content += "User ID: " + postRequ.getUserId() + "\n";
                    content += "ID: " + postRequ.getId() + "\n";
                    content += "Title: " + postRequ.getTitle() + "\n";
                    //content += "Email: " + comment.getEmail() + "\n";
                    content += "Text: " + postRequ.getBody() + "\n\n";

                    textViewThree.append(content);

                }

                @Override
                public void onFailure(Call<Post> call, Throwable t) {

                }
            });
        }
    }
}