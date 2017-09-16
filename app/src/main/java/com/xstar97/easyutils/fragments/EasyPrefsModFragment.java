package com.xstar97.easyutils.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.xstar97.easyutils.mods.EasyPrefsMod;
import com.xstar97.easyutils.sample.R;

import butterknife.ButterKnife;

public class EasyPrefsModFragment extends BaseFragment
{
    private String TAG = "EasyPrefsModFragment";

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] list = getResources().getStringArray(R.array.easy_prefs_mod_api_titles);

        initList(list);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id)
    {
        EasyPrefsMod.prefsBuilder easyMod = new  EasyPrefsMod.prefsBuilder(getActivity());
        easyMod.setPreference();
        String get = "Getting value  = ";
        String put = "Saving value...";
        String clear = "Clearing value...";
        String clearAll = "Clearing all value...";
        switch (pos) {
            case 0:
                easyMod.setKey("key");
                easyMod.setValue("-1");
                Object obj = easyMod.get();
                toast(get + obj);
                break;
            case 1:
                easyMod.setKey("key");
                easyMod.setValue("-1");
                Object obj2 = easyMod.getAll();
                toast(get + obj2);
                break;
            case 2:
                easyMod.setKey("key");
                easyMod.setValue("value");
                easyMod.put();
                toast(put);
                break;
            case 3:
                easyMod.setKey("key");
                easyMod.clearValue();
                toast(clear);
                break;
            case 4:
                easyMod.clearAll();
                toast(clearAll);
                break;
            default:
                break;
        }
    }
}