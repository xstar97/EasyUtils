package com.xstar97.easyutils.fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.xstar97.easyutils.mods.EasyActivityMod;
import com.xstar97.easyutils.sample.R;
import butterknife.ButterKnife;

public class EasyActivityModFragment extends BaseFragment
        {
private String TAG = "EasyActivityModFragment";

@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
        }

@Override
public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] list = getResources().getStringArray(R.array.easy_activity_mod_api_titles);

        initList(list);
        }

@Override
public void onItemClick(AdapterView<?> parent, View view, int pos, long id)
        {
        EasyActivityMod easyMod = new EasyActivityMod(getActivity());
        String restart = "Restarting app...";
        String recreate = "recreating...";
        switch (pos) {
        case 0:
        toast(restart);
        easyMod.restartApp();
        break;
        case 1:
        toast(recreate);
        easyMod.recreate();
        break;
default:
        break;
        }
        }
        }