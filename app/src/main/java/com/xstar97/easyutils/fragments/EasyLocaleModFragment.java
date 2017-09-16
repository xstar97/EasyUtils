package com.xstar97.easyutils.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.xstar97.easyutils.interfaces.EasyLocaleModListener;
import com.xstar97.easyutils.mods.EasyLocaleMod;
import com.xstar97.easyutils.sample.R;

import butterknife.ButterKnife;

public class EasyLocaleModFragment extends BaseFragment implements EasyLocaleModListener
{
    private String TAG = "EasyLocaleModFragment";

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] list = getResources().getStringArray(R.array.easy_locale_mod_api_titles);

        initList(list);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id)
    {
        EasyLocaleMod.localeBuilder easyMod = new EasyLocaleMod.localeBuilder(getActivity());
        easyMod.setListener(this);
        String locale = "es";

        String def = "Default locale has been set!";
        String setLocale = "locale has been set to Spanish(es)!";
        String deviceLocale = "Device language code is ";
        String appLocale = "App language code is ";

        switch (pos) {
            case 0:
                easyMod.setDefaultLocale();
                toast(def);
                break;
            case 1:
                easyMod.setLocaleCode(locale);
                easyMod.setLocale();
                toast(setLocale);
                break;
            case 2:
                String code = easyMod.getDeviceLanguageCode();
                toast(deviceLocale + code);
                break;
            case 3:
                String lang = easyMod.getAppLanguageCode();
                toast(appLocale + lang);
                break;
            default:
                break;
        }
    }

    @Override
    public void onLocaleChanged() {
        toast("locale " + "changed");
    }
}