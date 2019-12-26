package com.example.watchudrive;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.watchudrive.PostItemModel.Comments;
import com.example.watchudrive.PostItemModel.PostItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CommentVieweActivity extends AppCompatActivity implements Serializable {

    RecyclerView recyclerView;
    CommentsAdapter commentsAdapter;
    List<Comments> comments;


    private static String TAG = CommentVieweActivity.class.getSimpleName();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_viever);



        Intent i = getIntent();
        PostItem postItem = (PostItem) i.getParcelableExtra("data");


        recyclerView = findViewById(R.id.recyclerViewId);
        commentsAdapter = new CommentsAdapter(postItem);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(commentsAdapter);


       // Toast.makeText(getApplicationContext(),"xxxxx",Toast.LENGTH_SHORT).show();
    }
}
