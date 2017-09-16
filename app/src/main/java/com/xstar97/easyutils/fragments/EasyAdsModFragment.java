package com.xstar97.easyutils.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.xstar97.easyutils.enums.AdMethods;
import com.xstar97.easyutils.interfaces.EasyAdsModAsyncTaskListener;
import com.xstar97.easyutils.mods.EasyAdsMod;
import com.xstar97.easyutils.sample.R;

import butterknife.ButterKnife;

public class EasyAdsModFragment extends BaseFragment implements EasyAdsModAsyncTaskListener
{
    private String TAG = "EasyAdsModFragment";

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] list = getResources().getStringArray(R.array.easy_ads_mod_api_titles);

        initList(list);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id)
    {
        switch (pos) {
            case 0:
                EasyAdsMod.AdsBlockedTask easyMod = new EasyAdsMod.AdsBlockedTask();
                easyMod.setListener(this);
                easyMod.setMethod(AdMethods.HostMethod);
                easyMod.execute();
                break;
            default:
                break;
        }
    }

    @Override
    public void areAdsBlocked(boolean result) {
        toast("Are ads blocked = " + result);
    }
}