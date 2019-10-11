package com.example.watchudrive;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.watchudrive.PaginationListener.PAGE_START;

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    View v;
    RecyclerView recyclerView;
    PostRecyclerAdapter postRecyclerAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    private int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private int totalPage = 10;
    private boolean isLoading = false;
    int itemCount = 0;
    String a[],b[],c[],d[];
    int x;
    String url_head = "https://witty-bird-5.localtunnel.me";
    String url_tail = "/api/profile";
    String url = (url_head+url_tail);
    List<PostItem> items = new ArrayList<>();


    public HomeFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.home_fragment,container,false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        swipeRefreshLayout = (SwipeRefreshLayout)v.findViewById(R.id.id_swipe_refresh) ;
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = (RecyclerView)v.findViewById(R.id.id_recycler_view);
        postRecyclerAdapter = new PostRecyclerAdapter(new ArrayList<PostItem>(),getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(postRecyclerAdapter);

        doApiCall();

        recyclerView.addOnScrollListener(new PaginationListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage++;
                doApiCall();
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
        return  v;
    }
    private void doApiCall() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        a = new String[response.length()];
                        b = new String[response.length()];
                        c = new String[response.length()];
                        d = new String[response.length()];
                        x = response.length();

                        for(int i = 0;i<response.length();i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);

                                a[i] = jsonObject.getString("name");
                                b[i] = jsonObject.getString("rank");
                                c[i] = jsonObject.getString("image_url");
                                d[i] = jsonObject.getString("type");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        try {

            for(int i = 0;i<x;i++){
                PostItem postItem = new PostItem();
                postItem.setTitle(a[i]);
                postItem.setDescription((b[i]));
                postItem.setImage_url(url_head+(c[i]));
                postItem.setType((d[i]));
                items.add(postItem);
            }
        }catch (Exception e){

        }

        RequestQueue.getInstance(getContext()).addToRequestque(jsonArrayRequest);

        if (currentPage != PAGE_START) postRecyclerAdapter.removeLoading();
        postRecyclerAdapter.addItems(items);
        swipeRefreshLayout.setRefreshing(false);

        // check weather is last page or not
        if (currentPage < totalPage) {
            postRecyclerAdapter.addLoading();
        } else {
            isLastPage = true;
        }
        isLoading = false;


    }

    @Override
    public void onRefresh() {
        itemCount = 0;
        currentPage = PAGE_START;
        isLastPage = false;
        postRecyclerAdapter.clear();
        doApiCall();
    }

}
