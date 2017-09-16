package com.xstar97.easyutils.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.xstar97.easyutils.mods.EasyActivityMod;
import com.xstar97.easyutils.mods.EasyShellMod;
import com.xstar97.easyutils.sample.R;

import butterknife.ButterKnife;

public class EasyShellModFragment extends BaseFragment
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

        String[] list = getResources().getStringArray(R.array.easy_shell_mod_api_titles);

        initList(list);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id)
    {
        EasyShellMod.shellBuilder easyMod = new EasyShellMod.shellBuilder();
        easyMod.setCommand(EasyShellMod.shellBuilder.deleteCommand + null);
        String delete = "deleting porn folder....";
        switch (pos) {
            case 0:
                toast(delete);
                easyMod.executeProcessCommand();
                break;
            case 1:
                toast(delete);
                easyMod.executeRunTimeCommand();
                break;
            default:
                break;
        }
    }
}