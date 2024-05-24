package com.example.bdp139.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bdp139.FormActivity;
import com.example.bdp139.R;
import com.example.bdp139.adapter.CongViecAdapter;
import com.example.bdp139.database.Database;
import com.example.bdp139.model.CongViec;

import java.util.ArrayList;
import java.util.List;

public class FragmentSearch extends Fragment implements CongViecAdapter.CongViecItemListener {
    SearchView searchView;
    Spinner spinner;
    Button searchButton;
    ListView listView;
    List<CongViec> congViecs = new ArrayList<>();
    Database db;
    CongViecAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        searchButton = v.findViewById(R.id.searchButton);
        spinner = v.findViewById(R.id.spinner);
        searchView = v.findViewById(R.id.searchView);
        listView = v.findViewById(R.id.listView);

        db = new Database(getContext());
        adapter = new CongViecAdapter(getContext(), congViecs);
        adapter.setCongViecItemListener(this);
        listView.setAdapter(adapter);

        //Search Button
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = String.valueOf(searchView.getQuery());
                int tinhtrangKey = (int) spinner.getSelectedItemId();

                if (tinhtrangKey == 0) {
                    congViecs.clear();
                    congViecs.addAll(db.searchByKey(key));
                } else {
                    congViecs.clear();
                    congViecs.addAll(db.searchByKey(key, tinhtrangKey-1));
                }
                adapter.notifyDataSetChanged();
            }
        });

        return v;
    }

    @Override
    public void onItemClicked(View v, int position) {
        Intent intent = new Intent(getContext(), FormActivity.class);
        CongViec congViec = congViecs.get(position);
        intent.putExtra("congviec", congViec);
        startActivity(intent);
    }

    //Load lại fragment sau khi add
    @Override
    public void onResume() {
        super.onResume();
        congViecs.clear();
        adapter.notifyDataSetChanged();
    }
}
