package com.xstar97.easyutils.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.xstar97.easyutils.mods.EasyNetworkMod;
import com.xstar97.easyutils.sample.R;

import butterknife.ButterKnife;

public class EasyNetworkModFragment extends BaseFragment
{
    private String TAG = "EasyNetworkModFragment";

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] list = getResources().getStringArray(R.array.easy_network_mod_api_titles);

        initList(list);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id)
    {
        EasyNetworkMod.wifiBuilder easyWMod = new EasyNetworkMod.wifiBuilder(getActivity());
        EasyNetworkMod.bluBuilder easyBWMod = new EasyNetworkMod.bluBuilder(getActivity());
        String online = "Do I have online access: ";
        String wifi = "Do I have wifi enabled: ";
        String blu = "Do I have bluetooth enabled: ";
        switch (pos) {
            case 0:
                boolean isConnected = easyWMod.isConnected();
                toast(online + isConnected);
                break;
            case 1:
                boolean isWifiEnabled = easyWMod.isWifiEnabled();
                toast(wifi + isWifiEnabled);
                break;
            case 2:
               boolean isBluEnabled = easyBWMod.isBluEnabled();
                toast(blu + isBluEnabled);
                break;
            default:
                break;
        }
    }
}