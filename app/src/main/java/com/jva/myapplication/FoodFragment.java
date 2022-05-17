package com.jva.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class FoodFragment extends Fragment {


    List<String> foodList;

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public FoodFragment() {
        // Required empty public constructor
    }


    public static FoodFragment newInstance(String param1, String param2) {
        FoodFragment fragment = new FoodFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setHasOptionsMenu(true);
        /// Facem o lista cu date mock
        foodList = new ArrayList<>();
        foodList.add("Reteta gustoasa");
        foodList.add("Prajitura mandarine");
        foodList.add("Gulas fara probleme");
        foodList.add("Reteta tort grecesc");
        foodList.add("Reteta chec pufos");
        foodList.add("Reteta chec nepufos");
        foodList.add("Reteta comandata de pe Glovo");
        foodList.add("Reteta buna");
        foodList.add("Ciocolata pane");
        foodList.add("Pui rotisat incet");
        foodList.add("Vita incredibila");
        foodList.add("Specialitate Vegana");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View inflater1 = inflater.inflate(R.layout.fragment_food, container, false);

        // Salvam recyclerview-ul din fragment_food.xml in variabila recyclerView
        recyclerView = inflater1.findViewById(R.id.recyclerView);

        // Declaram adaptor si in constructor punem lista mock
        recyclerAdapter = new RecyclerAdapter(foodList);

        /// Setam recyclerView-ul sa aiba adaptorul declarat mai sus
        recyclerView.setAdapter(recyclerAdapter);

        /// Adaugam linii intre elemente
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(inflater1.getContext(),DividerItemDecoration.VERTICAL);

        recyclerView.addItemDecoration(dividerItemDecoration);


        return inflater1;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        inflater.inflate(R.menu.main_menu,menu);

        MenuItem item = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) item.getActionView();
        /// Cand scriem in searchview
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ///Cand se schimba text-ul , apelam filtering din adaptor
                recyclerAdapter.getFilter().filter(newText);
                return false;
            }
        });


        super.onCreateOptionsMenu(menu, inflater);
    }
}