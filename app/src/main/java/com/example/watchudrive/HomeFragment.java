package com.example.watchudrive;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.watchudrive.PostItemModel.PostItem;

public class HomeFragment extends Fragment  {

    RecyclerView recyclerView;
    View v;

    public HomeFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v= inflater.inflate(R.layout.home_fragment,container,false);
         recyclerView = (RecyclerView)v.findViewById(R.id.id_recycler_view);

         final PostRecyclerViewAdapter adapter = new PostRecyclerViewAdapter();
         recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

         PostViewModel postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);

         postViewModel.postpagedList.observe(this, new Observer<PagedList<PostItem>>() {
             @Override
             public void onChanged(PagedList<PostItem> postItems) {
                 adapter.submitList(postItems);
             }
         });

         recyclerView.setAdapter(adapter);

        return  v;
    }


}
