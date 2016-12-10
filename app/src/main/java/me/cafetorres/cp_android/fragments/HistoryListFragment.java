package me.cafetorres.cp_android.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.cafetorres.cp_android.Details_cp;
import me.cafetorres.cp_android.R;
import me.cafetorres.cp_android.adapters.CpAdapter;
import me.cafetorres.cp_android.adapters.OnItemClickListener;
import me.cafetorres.cp_android.entities.PostalCode;

/**
 * Created by Carlos on 09/12/2016.
 */

public class HistoryListFragment extends Fragment implements HistoryListFragmentListener, OnItemClickListener {
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    CpAdapter adapter;

    public HistoryListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        initAdapter();
        initRecyclerView();
        return view;

    }

    private void initAdapter() {
        if(adapter == null) {
            adapter = new CpAdapter(getActivity().getApplicationContext(), this);
        }
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void addToList(PostalCode postalCode) {
        adapter.add(postalCode);
    }

    @Override
    public void clearList() {
        adapter.clear();
    }

    @Override
    public void onItemClick(PostalCode postalCode) {
        Intent intent = new Intent(getActivity(), Details_cp.class);
        intent.putExtra("placename", postalCode.getPlacename());
        intent.putExtra("postalcode", postalCode.getPostalcode());

        startActivity(intent);
    }




}
