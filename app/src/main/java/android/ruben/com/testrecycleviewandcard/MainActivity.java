package android.ruben.com.testrecycleviewandcard;

        import android.content.Context;
        import android.ruben.com.adaptar.CustomAdapter;
        import android.ruben.com.adaptar.MyData;
        import android.ruben.com.model.DataModel;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.DefaultItemAnimator;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static RecyclerView mRecyclerView;
    static RecyclerView.Adapter mAdapter;
    static ArrayList<DataModel> mDataModels;
    static ArrayList<Integer> removedItems;
    public static MainActivity.MyOnClickListener myOnClickListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDataModels= new ArrayList<DataModel>();
        myOnClickListener = new MyOnClickListener(this);
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

        removedItems = new ArrayList<>();

    }

    public static class MyOnClickListener implements View.OnClickListener{

        private final Context mContext;

        public MyOnClickListener(Context context) {
            mContext = context;
        }

        @Override
        public void onClick(View v) {

            remove(v);

        }

        private void remove(View v) {
            int selectedItemPos = mRecyclerView.getChildAdapterPosition(v);

            RecyclerView.ViewHolder viewHolder = mRecyclerView.findViewHolderForAdapterPosition(selectedItemPos);

            TextView textView = viewHolder.itemView.findViewById(R.id.textViewName);

            String selectedName = textView.getText().toString();
            int selectedIndexId = -1;

            for (int i = 0; i < MyData.nameArray.length; i++) {
                if(selectedName.equalsIgnoreCase(MyData.nameArray[i])) {
                    selectedIndexId = MyData.id_[i];
                    break;
                }
            }
            removedItems.add(selectedIndexId);
            mDataModels.remove(selectedItemPos);
//            mAdapter.notifyDataSetChanged();
            mAdapter.notifyItemRemoved(selectedItemPos);


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId()==R.id.add_item){
            if(removedItems.size()>0) {
                addRemoveItemToList();
            }else{
                Toast.makeText(this, "Nothing to Add", Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }

    private void addRemoveItemToList() {
        int addItemAtListPosition = 3;
        mDataModels.add(addItemAtListPosition, new DataModel(
                MyData.nameArray[removedItems.get(0)],
                MyData.versionArray[removedItems.get(0)],
                MyData.id_[removedItems.get(0)],
                MyData.drawableArray[removedItems.get(0)]
        ));
        mAdapter.notifyItemInserted(addItemAtListPosition);
        removedItems.remove(0);
    }
}
