package com.google.android.gms.samples.vision.barcodereader;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Injections extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    List<String> allInjections;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_injections);

//        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
//        setSupportActionBar(myToolbar);

        listView = (ListView) findViewById(R.id.listView);

        allInjections = new ArrayList<String>();
//        arrayAdapter = new ArrayAdapter<String>();
//        allLivestockAnimalIds.add("kjsdf");
//        allLivestockAnimalIds.add("daf");
//        allLivestockAnimalIds.add("sdsd");
        updateListView();





    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.injection_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {



        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Injection [] injectionClicked = new Injection[1];

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
               injectionClicked[0] = Injection.getInjectionAtPositionInDb(getApplicationContext(), info.position);
            }
        });

        switch (item.getItemId()) {
            case R.id.edit_injection_option:
//                Toast.makeText(getApplicationContext(), "Clicked item: " + info.position + " with text: " + arrayAdapter.getItem(info.position), Toast.LENGTH_SHORT).show();


                // send object to modifyInjection activity

                Intent goToNextActivity = new Intent(getApplicationContext(), ModifyInjection.class);
                goToNextActivity.putExtra("modify_this_injection", injectionClicked[0]);
                startActivity(goToNextActivity);
                arrayAdapter.notifyDataSetChanged();

                return true;
            case R.id.delete_injection_option:
                deleteInjection(injectionClicked[0]);
                return true;

            case R.id.markAsDone_injection_option:
                allInjections.set(info.position, arrayAdapter.getItem(info.position) + " --- done");
                arrayAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_new_injection:
                // User chose the "add" item, go to new activity to add injection
                Intent goToNextActivity = new Intent(getApplicationContext(), AddInjection.class);
                startActivity(goToNextActivity);
                return true;

//            case R.id.action_favorite:
//                // User chose the "Favorite" action, mark the current item
//                // as a favorite...
//                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private void updateListView(){

//        if (listView != null && arrayAdapter != null){
        if (listView != null){

            listView = (ListView) findViewById(R.id.listView);



            allInjections = Injection.getAllInjections(getApplicationContext()); // get all data from db
            //List<String> liveStockBarcodes = new ArrayList<String>(); // storing all animal_ids

//            for (int i = 0; i < allLivestockAnimalIds.size(); i++){
//                liveStockBarcodes.add(allLivestock.get(i).animal_id);
//            }
//
//        liveStockBarcodes.add("lol");
//        liveStockBarcodes.add("lol");
//        liveStockBarcodes.add("lol");
//        liveStockBarcodes.add("lol");

            // populate barcodes into UI listView

            arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, allInjections);
            listView.setAdapter(arrayAdapter);

            registerForContextMenu(listView);

//            arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, allLivestockAnimalIds);
//            listView.setAdapter(arrayAdapter);



//            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//                @Override
//                public void onItemClick(AdapterView<?> parent, final View view,
//                                        int position, long id) {
//                    final String item = (String) parent.getItemAtPosition(position);
//
//                    Toast.makeText(getApplicationContext(), item , Toast.LENGTH_SHORT).show();
//                    // get barcode, given animal_id
//                    Livestock livestock = getLivestock(item);
//
//                    //send this livestock to modifying activity, called Modify
//
//                    Intent goToNextActivity = new Intent(getApplicationContext(), Modify.class);
//                    goToNextActivity.putExtra("modify_this_livestock", livestock);
//                    startActivity(goToNextActivity);
//                    arrayAdapter.notifyDataSetChanged();
//
//
//
////                view.animate().setDuration(2000).alpha(0)
////                        .withEndAction(new Runnable() {
////                            @Override
////                            public void run() {
//////                                list.remove(item);
//////                                arrayAdapter.notifyDataSetChanged();
//////                                view.setAlpha(1);
////
////                                Toast.makeText(getApplicationContext(), item , Toast.LENGTH_SHORT).show();
////
////                            }
////                        });
//                }
//
//            });

        }
    }

    private void deleteInjection(final Injection injectionClicked){

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                FeedReaderDbHelperInjections mDbHelper = new FeedReaderDbHelperInjections(getApplicationContext());
                SQLiteDatabase db = mDbHelper.getWritableDatabase();

                db.delete(FeedReaderContractInjections.FeedEntry.TABLE_NAME, FeedReaderContract.FeedEntry._ID + "=" + injectionClicked.db_id, null);
                db.close();

                Toast.makeText(getApplicationContext(), "Deleted injection from record.", Toast.LENGTH_SHORT).show();

            }
        });

        arrayAdapter.notifyDataSetChanged();
        updateListView();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.injection_top_bar, menu);
        return true;
    }

    @Override
    protected void onResume() {

        super.onResume();

        if (arrayAdapter!=null){
            listView.setAdapter(arrayAdapter);
            arrayAdapter.notifyDataSetChanged();
            updateListView();
        }


    }

}
