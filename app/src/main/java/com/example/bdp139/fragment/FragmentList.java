package com.example.bdp139.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bdp139.FormActivity;
import com.example.bdp139.R;
import com.example.bdp139.adapter.CongViecAdapter;
import com.example.bdp139.database.Database;
import com.example.bdp139.model.CongViec;

import java.util.List;

public class FragmentList extends Fragment implements CongViecAdapter.CongViecItemListener {
    ListView listView;
    Database db;
    CongViecAdapter adapter;
    List<CongViec> congViecs;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        listView = v.findViewById(R.id.listView);
        db = new Database(getContext());

        congViecs = db.getCongViecs();
        adapter = new CongViecAdapter(getContext(), congViecs);
        adapter.setCongViecItemListener(this);
        listView.setAdapter(adapter);

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
        congViecs.addAll(db.getCongViecs());
        adapter.notifyDataSetChanged();
    }
}
