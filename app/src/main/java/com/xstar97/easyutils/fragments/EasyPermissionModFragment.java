package com.xstar97.easyutils.fragments;


import android.Manifest;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.xstar97.easyutils.mods.EasyPermissionMod;
import com.xstar97.easyutils.sample.R;

import butterknife.ButterKnife;

public class EasyPermissionModFragment extends BaseFragment
{
    private String TAG = "EasyPermissionModFragment";

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] list = getResources().getStringArray(R.array.easy_permission_mod_api_titles);

        initList(list);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id)
    {
        EasyPermissionMod.permissionBuilder easyMod = new EasyPermissionMod.permissionBuilder(getActivity());
        easyMod.setPermissionName(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        String isGranted = "Is permission granted = ";
        switch (pos) {
            case 0:
                boolean perm = easyMod.isPermissionGranted();
                toast(isGranted + perm);
                break;
            default:
                break;
        }
    }
}