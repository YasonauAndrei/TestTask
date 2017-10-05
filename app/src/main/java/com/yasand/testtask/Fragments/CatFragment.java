package com.yasand.testtask.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yasand.testtask.Adapters.Adapter;
import com.yasand.testtask.Api.ClickListener;
import com.yasand.testtask.Utils.InternetConnection;
import com.yasand.testtask.Activitys.ItemActivity;
import com.yasand.testtask.Models.Datum;
import com.yasand.testtask.Models.Model;
import com.yasand.testtask.MyApp;
import com.yasand.testtask.R;
import com.yasand.testtask.Utils.RecyclerTouchListener;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user on 04.10.2017.
 */

public class CatFragment extends Fragment {

    RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<Datum> datumList;
    LinearLayoutManager layoutManager;

    static final String DATA="data";
    static final String SCROLL_POSITION="scrollPosition";


    public static CatFragment newInstance(int scrollState, ArrayList<Datum> datum)
    {
        CatFragment catFragment=new CatFragment();
        Bundle args=new Bundle();
        args.putInt(SCROLL_POSITION,scrollState);
        args.putParcelableArrayList(DATA,datum);
        catFragment.setArguments(args);
        return catFragment;
    }

    public int getScrollPosition()
    {
        if(recyclerView!=null) {
            return ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }
        else
            return 0;
    }

    public ArrayList<Datum> getData()
    {
        return datumList!=null?datumList:null;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cat, container, false);

        recyclerView=(RecyclerView)view.findViewById(R.id.rv_cat);
        layoutManager=new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                startActivity(new Intent(getActivity().getApplicationContext(), ItemActivity.class)
                        .putExtra("uri",datumList.get(position).getUrl())
                        .putExtra("title",datumList.get(position).getTitle())
                        .putExtra("name",String.valueOf(position+1))
                );
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        if(getArguments()!=null) {
            datumList = getArguments().getParcelableArrayList(DATA);
            adapter = new Adapter(datumList, getActivity().getBaseContext());
            recyclerView.setAdapter(adapter);
            recyclerView.getAdapter().notifyDataSetChanged();
            recyclerView.scrollToPosition(getArguments().getInt(SCROLL_POSITION));
        }
        else
        {
            fillData();
        }
        return view;
    }



    private void fillData()
    {
        if(InternetConnection.checkConnection(getActivity().getBaseContext())) {

            MyApp.getApi().getList("cat").enqueue(new Callback<Model>() {
                @Override
                public void onResponse(Call<Model> call, Response<Model> response) {
                    final Model model=new Model();
                    if(response.isSuccessful()) {
                        model.setData(response.body().getData());
                        datumList = model.getData();
                        adapter = new Adapter(datumList, getActivity().getBaseContext());
                        recyclerView.setAdapter(adapter);
                        recyclerView.getAdapter().notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<Model> call, Throwable t) {
                    Toast.makeText(getActivity().getBaseContext(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else
        {
            Toast.makeText(getActivity().getBaseContext(),"Check internet connection",Toast.LENGTH_SHORT).show();
        }
    }

}
