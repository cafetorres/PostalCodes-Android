package me.cafetorres.cp_android.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.cafetorres.cp_android.R;
import me.cafetorres.cp_android.entities.PostalCode;

/**
 * Created by Carlos on 09/12/2016.
 */

public class CpAdapter extends RecyclerView.Adapter<CpAdapter.ViewHolder>{
    private Context context;
    private List<PostalCode> dataset;
    private OnItemClickListener onItemClickListener;


    public CpAdapter(Context context, List<PostalCode> dataset, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.dataset = dataset;
        this.onItemClickListener = onItemClickListener;
    }

    public CpAdapter(Context context, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.dataset = new ArrayList<PostalCode>();
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PostalCode element = dataset.get(position);
        String place = element.getPostalcode()+":" + element.getPlacename();
        holder.txtContent.setText(place);
        holder.setOnItemClickListener(element, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void add(PostalCode postalCode) {
        dataset.add(0, postalCode);
        notifyDataSetChanged();
    }

    public void clear() {
        dataset.clear();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.txtContent)
        TextView txtContent;

        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        public void setOnItemClickListener(final PostalCode element, final OnItemClickListener onItemClickListener){
            itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(element);
            }
            });
        }
    }
}
