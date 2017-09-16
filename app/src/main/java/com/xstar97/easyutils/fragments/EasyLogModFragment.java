package com.xstar97.easyutils.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.xstar97.easyutils.mods.EasyLogMod;
import com.xstar97.easyutils.sample.R;

import butterknife.ButterKnife;

public class EasyLogModFragment extends BaseFragment
{
    private String TAG = "EasyLogModFragment";

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] list = getResources().getStringArray(R.array.easy_log_mod_api_titles);

        initList(list);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id)
    {
        EasyLogMod.logBuilder easyMod = new EasyLogMod.logBuilder();
        String ver = "verbose logging";
        String deb = "debug logging";
        String inf = "info logging";
        String war = "warn logging";
        String err = "error logging";

        switch (pos) {
            case 0:
                easyMod.setTag(TAG);
                easyMod.setLog(ver);
                easyMod.verbose();
                toast(ver);
                break;
            case 1:
                easyMod.setTag(TAG);
                easyMod.setLog(deb);
                easyMod.debug();
                toast(deb);
            break;
            case 2:
                easyMod.setTag(TAG);
                easyMod.setLog(inf);
                easyMod.info();
                toast(inf);
            break;
            case 3:
                easyMod.setTag(TAG);
                easyMod.setLog(war);
                easyMod.warn();
                toast(war);
                break;
            case 4:
                easyMod.setTag(TAG);
                easyMod.setLog(err);
                easyMod.error();
                toast(err);
                break;
            default:
                break;
        }
    }
}