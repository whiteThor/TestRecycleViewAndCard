package android.ruben.com.adaptar;

import android.ruben.com.model.DataModel;
import android.ruben.com.testrecycleviewandcard.MainActivity;
import android.ruben.com.testrecycleviewandcard.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    ArrayList<DataModel> mDataModels;

    public CustomAdapter(ArrayList<DataModel> dataModels) {
        mDataModels = dataModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout,parent,false);
        view.setOnClickListener(MainActivity.myOnClickListener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.bind(position);

    }

    @Override
    public int getItemCount() {
        return mDataModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView mTextViewName;
        TextView mTextViewVersion;
        ImageView mImageViewIcon;


        public ViewHolder(View itemView) {
            super(itemView);

            mTextViewName = itemView.findViewById(R.id.textViewName);
            mTextViewVersion = itemView.findViewById(R.id.textViewVersion);
            mImageViewIcon = itemView.findViewById(R.id.imageView);
        }

        public void bind(int pos){
            DataModel dataModel = mDataModels.get(pos);

            mTextViewVersion.setText(dataModel.getVersion());
            mTextViewName.setText(dataModel.getName());
            mImageViewIcon.setImageResource(dataModel.getImage());

        }

    }
}
