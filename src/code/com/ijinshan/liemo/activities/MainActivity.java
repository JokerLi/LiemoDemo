package com.ijinshan.liemo.activities;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.Loader;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.ijinshan.liemo.contentproviders.MyCursorLoader;
import com.ijinshan.liemo.contentproviders.UriMatchHelper;
import com.ijinshan.liemo.controllers.OptionController;
import com.ijinshan.liemo.fragments.TitleFragment;
import com.ijinshan.liemo.views.DragLayout;
import com.ijinshan.liemoapp.R;

import java.util.Vector;

public class MainActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor>{
    private DragLayout mDragLayout;
    private ListView mOptionListView;
    private OptionController mOptionController;
    private ListView mListView;
    private SimpleCursorAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initControllers();
        initDragLayout();
        initListView();
        initFragmentView();
        getLoaderManager().initLoader(1, null, this);
    }

    private void inittest() {
        Log.e("hehehehe", "test" + UriMatchHelper.getElements("content://liemo.test.setting.provider/elements"));
        ContentResolver resolver= getContentResolver();
        Uri uri = Uri.parse("content://liemo.test.setting.provider/elements");
        resolver.registerContentObserver(uri, false, new MyObserver(new Handler(Looper.getMainLooper())));
        resolver.registerContentObserver(uri, false, new MyObserver(new Handler(Looper.getMainLooper())));
        resolver.query(uri, null, null, null, null);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new MyCursorLoader(getApplicationContext());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mAdapter = new SimpleCursorAdapter(MainActivity.this,R.layout.contacts_list_item,
                cursor, new String[]{ContactsContract.Contacts.DISPLAY_NAME},new int[]{android.R.id.text1});
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private class MyObserver extends ContentObserver{

        /**
         * Creates a content observer.
         *
         * @param handler The handler to run {@link #onChange} on, or null if none.
         */
        public MyObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            Log.e("hehehehe", "test" + selfChange);
        }
    }

    private TitleFragment mTitleFragment;
    private void initFragmentView() {
//        FragmentManager manager = getFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//
//        mTitleFragment = new TitleFragment();
//        transaction.replace(R.id.id_title, mTitleFragment);
//        transaction.commit();
    }

    private void initControllers() {
        mOptionController = new OptionController();
    }

    private void initListView(){
        mOptionListView = (ListView) findViewById(R.id.option_listview);
        mOptionController.bindData(mOptionListView);
        mListView = (ListView)findViewById(R.id.content_listview);
    }

    private void initDragLayout() {
        mDragLayout = (DragLayout) findViewById(R.id.main_draglayout);
        mDragLayout.setDragListener(new DragLayout.DragListener() {
            @Override
            public void onOpen() {
            }

            @Override
            public void onClose() {
            }

            @Override
            public void onDrag(float percent) {
            }
        });
    }

}
