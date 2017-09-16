package com.xstar97.easyutils.fragments;

import android.support.v4.app.Fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.xstar97.easyutils.mods.EasyLogMod;
import com.xstar97.easyutils.sample.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

public class BaseFragment extends Fragment implements AdapterView.OnItemClickListener
{
    private String TAG = "BaseFragment";
    public Unbinder unbinder;

    @BindView(R.id.api_list_listView)
    ListView api_list_listView;

    public void initList(String[] list){
        final List<String> api_list = new ArrayList<>(Arrays.asList(list));

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>
                (getActivity(), android.R.layout.simple_list_item_1, api_list);

        api_list_listView.setAdapter(arrayAdapter);
        api_list_listView.setOnItemClickListener(this);
    }

    public void toast(String t){
        Toast.makeText(getActivity(), t, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
        new EasyLogMod.logBuilder()
                .setTag("onItemClick")
                .setLog("pos: " + pos + "\n" + "id: " + id)
                .debug();
    }
}