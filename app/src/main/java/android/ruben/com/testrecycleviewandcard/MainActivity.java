package android.ruben.com.testrecycleviewandcard;

        import android.ruben.com.adaptar.CustomAdapter;
        import android.ruben.com.adaptar.MyData;
        import android.ruben.com.model.DataModel;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.DefaultItemAnimator;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;

        import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    ArrayList<DataModel> mDataModels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<DataModel> mDataModels= new ArrayList<DataModel>();
        for (int i = 0; i < MyData.nameArray.length; i++) {
            mDataModels.add(new DataModel(MyData.nameArray[i], MyData.versionArray[i], MyData.id_[i],MyData.drawableArray[i]));

        }
        mAdapter = new CustomAdapter(mDataModels);

        mRecyclerView = findViewById(R.id.recycleView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);





        

    }
}
