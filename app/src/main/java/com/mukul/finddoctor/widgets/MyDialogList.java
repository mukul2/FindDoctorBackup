package com.mukul.finddoctor.widgets;

import android.app.Activity;
import android.app.Dialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mukul.finddoctor.R;
import com.mukul.finddoctor.adapter.TestListAdapter;
import com.mukul.finddoctor.model.TestModel;
import com.mukul.finddoctor.model.testSelectedModel;

import java.util.ArrayList;
import java.util.List;

import static com.mukul.finddoctor.Data.DataStore.selectedTestIds;
import static com.mukul.finddoctor.Data.DataStore.testModelList;

/**
 * Created by mukul on 3/22/2019.
 */

public class MyDialogList {
    private static MyDialogList myDialogList;
    Activity activity;
    testSelectedListener testSelectListener ;
    List<testSelectedModel> testModelLists=new ArrayList<>();
    public static MyDialogList getInstance() {

        if (myDialogList == null) {
            myDialogList = new MyDialogList();
        }
        return myDialogList;
    }
    public  MyDialogList with(Activity activity) {
        this.activity = activity;
        return this;
    }
    public  MyDialogList setData( List<testSelectedModel> data) {
        this.testModelLists = data;
        return this;
    }
    public  interface testSelectedListener{
        void onDialogCloased( List<String>selectedTest);
    }


    public void showTestList(testSelectedListener listener) {
        this.testSelectListener=listener;

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.dialog_with_recycler);
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.recycler_view);
        TextView tv_done = (TextView) dialog.findViewById(R.id.tv_done);
        for (int i=0;i<testModelList.size();i++){
            testModelList.get(i).setSelected(false);

        }

        final TestListAdapter mAdapter = new TestListAdapter(testModelList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        tv_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String>selectedTest=new ArrayList<>();
                for (int i=0;i<testModelList.size();i++){
                    if (testModelList.get(i).isSelected()){
                        selectedTest.add(testModelList.get(i).getTestModel().getId());
                    }
                }
                selectedTestIds.clear();
                selectedTestIds.addAll(selectedTest);
                testSelectListener.onDialogCloased(selectedTest);

                dialog.dismiss();
            }
        });


    }
}
