package groep4.a_zalf.Activities;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import groep4.a_zalf.Protocol.ApplicationAdapter;
import groep4.a_zalf.Protocol.GetAllAppsTask;
import groep4.a_zalf.R;

public class Suggesties extends AppCompatActivity {

    private ListView listView;
    private TextView headerText;
    //private Toolbar toolbar;

    private PackageManager packageManager;
    private ApplicationAdapter adapter;
    private ArrayList<ApplicationInfo> applicationInfos;
    private ProgressDialog progressDialog;

    //
    private ArrayList<String> lijstje;
    //

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestie);

        packageManager = getPackageManager();
        applicationInfos = new ArrayList<>();
        lijstje = new ArrayList<>();

        //find View by id
        listView = (ListView) findViewById(R.id.list_view);
        //toolbar = (Toolbar) findViewById(R.id.toolbar);

        //setSupportActionBar(toolbar);

        //show progress dialog
        progressDialog = ProgressDialog.show(this, "Suggesties laden", "");

        //set list view adapter
        LayoutInflater inflater = getLayoutInflater();
        //View header = inflater.inflate(R.layout.layout_lv_header, listView, false);
        //headerText = (TextView) header.findViewById(R.id.text_header);
        //listView.addHeaderView(header, null, false);

        //initializing and set adapter for listview
        adapter = new ApplicationAdapter(this, R.layout.item_listviewsuggestie, applicationInfos);
        listView.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        // Inflate menu to add items to action bar if it is present.
        inflater.inflate(R.menu.menu_suggestie, menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(onQueryTextListener()); // text changed listener
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    private SearchView.OnQueryTextListener onQueryTextListener() {
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //filter adapter and update ListView
                adapter.getFilter().filter(s);

                return false;
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();

        //invoke asynctask
        new GetAllAppsTask(this, applicationInfos, packageManager).execute();
    }


    public void callBackDataFromAsynctask(List<ApplicationInfo> list) {
        applicationInfos.clear();

        for (int i = 0; i < list.size(); i++) {
            applicationInfos.add(list.get(i));
        }

        //headerText.setText("Aantal suggesties (" + applicationInfos.size() + ")");
        adapter.notifyDataSetChanged();
        progressDialog.dismiss();
    }

    public void updateUILayout(String content) {
        headerText.setText(content);
    }
}
