package com.xstar97.easyutils.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.xstar97.easyutils.mods.EasyAppMod;
import com.xstar97.easyutils.sample.R;

import butterknife.ButterKnife;

public class EasyAppModFragment extends BaseFragment
{
    private String TAG = "EasyAppModFragment";

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] list = getResources().getStringArray(R.array.easy_app_mod_api_titles);

        initList(list);
    }
    private void showImage(Drawable drawable) {
        try {
            Dialog builder = new Dialog(getActivity());
            builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
            builder.getWindow().setBackgroundDrawable(
                    new ColorDrawable(android.graphics.Color.TRANSPARENT));
            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    //nothing;
                }
            });

            ImageView imageView = new ImageView(getActivity());
            imageView.setImageDrawable(drawable);
            builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            builder.show();
        }catch (NullPointerException npe){

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id)
    {
        EasyAppMod.appBuilder easyMod = new EasyAppMod.appBuilder(getActivity());
        String sample = easyMod.appPackageName();
        easyMod.setPkgId(sample);
        String installed = "Is app installed = ";
        String enabled = "Is app enabled = ";
        String verName = "App version name = ";
        String verCode = "App version code = ";
        String samplepkgName = "Sample app Package Name = ";

        switch (pos) {
            case 0:
                easyMod.setDrawable(null);
                Drawable drawable = easyMod.getAppDrawable();
                showImage(drawable);
                break;
            case 1:
                boolean appInstalled = easyMod.isAppInstalled();
                toast(installed + appInstalled);
                break;
            case 2:
                boolean appEnabled = easyMod.isAppEnabled();
                toast(enabled + appEnabled);
                break;
            case 3:
                String appVersionName = easyMod.appVersionName();
                toast(verName + appVersionName);
                break;
            case 4:
                int appVersionCode = easyMod.appVersionCode();
                toast(verCode + appVersionCode);
                break;
            case 5:
                toast(samplepkgName + sample);
                break;
            default:
                break;
        }
    }
}