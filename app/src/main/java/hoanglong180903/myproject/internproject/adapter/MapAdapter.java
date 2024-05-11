package hoanglong180903.myproject.internproject.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hoanglong180903.myproject.internproject.R;
import hoanglong180903.myproject.internproject.interface_adapter.OnClickItemAdapter;
import hoanglong180903.myproject.internproject.model.Location;

public class MapAdapter extends RecyclerView.Adapter<MapAdapter.MyViewHolder>  {
    Context mContext;
    List<Location> locationList;

    OnClickItemAdapter onClickItemAdapter;

    public MapAdapter(Context mContext, List<Location> locationList) {
        this.mContext = mContext;
        this.locationList = locationList;
    }

    public void onClickItemLocation(OnClickItemAdapter onClickItemAdapter){
        this.onClickItemAdapter = onClickItemAdapter;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_map,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Location item = locationList.get(position);
        holder.item_tvLocation.setText(""+item.getTitle());
        holder.item_imgGoMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItemAdapter.onClickItemLocation(item.getPosition().getLat(),item.getPosition().getLng());
                holder.item_imgGoMap.setBackgroundColor(Color.parseColor("#F5F5F5"));
            }
        });
    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    public final class MyViewHolder extends RecyclerView.ViewHolder {
        TextView item_tvLocation;
        LinearLayout item_ln;
        ImageView item_imgGoMap;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_tvLocation = itemView.findViewById(R.id.item_tvLocation);
            item_ln = itemView.findViewById(R.id.item_ln);
            item_imgGoMap= itemView.findViewById(R.id.item_imgGoMap);
        }
    }
}
