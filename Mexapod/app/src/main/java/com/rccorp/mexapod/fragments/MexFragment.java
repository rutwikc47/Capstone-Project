package com.rccorp.mexapod.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rccorp.mexapod.adapters.MexCursorAdapter;
import com.rccorp.mexapod.database.mex.MexColumns;
import com.rccorp.mexapod.R;
import com.rccorp.mexapod.network.MexSync;

/**
 * Created by Rutwik on 02/05/16.
 */
public class MexFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>,View.OnClickListener{

    Context mContext;
    RecyclerView mexrec;

    public static final String INTENT_ROVER = "intent_rover";

    Activity mActivity;

    MexCursorAdapter mexCursorAdapter;

    Cursor mCursor;

    ProgressBar progressBar;

    private static final int LOADER_ID = 47;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View mexview = inflater.inflate(R.layout.fragment_mexlist, container, false);
        mContext = mexview.getContext();
        mexview.isInEditMode();

        Cursor cursor=getActivity().getContentResolver().query(
                MexColumns.CONTENT_URI,
                null,
                null,
                null,
                null
        );

        progressBar=(ProgressBar)mexview.findViewById(R.id.mex_spinner);

        mexCursorAdapter=new MexCursorAdapter(getActivity(),mCursor);

        mexCursorAdapter.setHasStableIds(false);

        mexrec=(RecyclerView)mexview.findViewById(R.id.meapod_rec);
        mexrec.setLayoutManager(new LinearLayoutManager(mContext));

        progressBar.setVisibility(View.VISIBLE);

        if(savedInstanceState==null){
            getMexData(INTENT_ROVER,"opportunity");
        }else {
            mexCursorAdapter.swapCursor(cursor);
            progressBar.setVisibility(View.INVISIBLE);
            mexrec.setAdapter(mexCursorAdapter);
        }
        return mexview;

    }

    public void getMexData(String key,String rover){
        ConnectivityManager connMgr = (ConnectivityManager) getActivity()
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()){
            Intent mexintent = new Intent(getActivity(), MexSync.class);
            mexintent.putExtra(key,rover);
            getActivity().startService(mexintent);
            MexFragment.this.restartLoader();
            progressBar.setVisibility(View.VISIBLE);
        }else {
            Toast.makeText(mContext,"Sorry No Internet Connection",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("KEY","KEYS");
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity=activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        progressBar.setVisibility(View.VISIBLE);

        return new android.content.CursorLoader(
                mActivity,
                MexColumns.CONTENT_URI,
                null,
                null,
                null,
                null
        );

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if(data.getCount()!=0){
            progressBar.setVisibility(View.INVISIBLE);
            mexCursorAdapter.swapCursor(data);
            mexrec.setAdapter(mexCursorAdapter);
            mexCursorAdapter.notifyDataSetChanged();
        } else {
            restartLoader();
        }
    }

    private void restartLoader(){
        getLoaderManager().restartLoader(LOADER_ID, null, this);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mexCursorAdapter.swapCursor(null);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (menu.size()==0){
            inflater.inflate(R.menu.menu_main, menu);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_curiosity:
                getMexData(INTENT_ROVER,"curiosity");
                break;
            case R.id.action_opportunity:
                getMexData(INTENT_ROVER,"opportunity");
                break;
            default:
                break;
        }
        return true;
    }

}
