package com.xstar97.easyutils.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.xstar97.easyutils.interfaces.EasySocialModListener;
import com.xstar97.easyutils.mods.EasySocialMod;
import com.xstar97.easyutils.sample.R;

import butterknife.ButterKnife;

public class EasySocialModFragment extends BaseFragment implements EasySocialModListener
{
    private String TAG = "EasySocialModFragment";

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] list = getResources().getStringArray(R.array.easy_social_mod_api_titles);

        initList(list);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id)
    {
        EasySocialMod.socialBuilder easyMod = new EasySocialMod.socialBuilder(getActivity());
        easyMod.setListener(this);
        String fbPage = "Open Facebook Page";
        String fbPro = "Open Facebook Profile";
        String fbMessenger = "Open Messenger User Chat";

        String gPlusPro = "Open Google Plus Profile";
        String gPlusCom = "Open Google Plus Community";

        String youtubePro = "Open YouTube Profile";
        String youtubeVid = "Open YouTube Video";
        String youtubeChan = "Open YouTube Channel";
        String socialApp = "Open Custom Social App";
        String rateApp = "Rate App";

        switch (pos) {
            case 0:
                easyMod.setUri("");
                easyMod.openFacebookPage();
                toast(fbPage);
                break;
            case 1:
                easyMod.setUri("");
                easyMod.openFacebookProfile();
                toast(fbPro);
                break;
            case 2:
                easyMod.setUri("");
                easyMod.openFacebookMessengerUserChat();
                toast(fbMessenger);
                break;
            case 3:
                easyMod.setUri("");
                easyMod.openGooglePlusProfile();
                toast(gPlusPro);
                break;
            case 4:
                easyMod.setUri("");
                easyMod.openGooglePlusCommunity();
                toast(gPlusCom);
                break;
            case 5:
                easyMod.setUri("");
                easyMod.openYouTubeProfile();
                toast(youtubePro);
                break;
            case 6:
                easyMod.setUri("");
                easyMod.openYouTubeVideo();
                toast(youtubeVid);
                break;
            case 7:
                easyMod.setUri("");
                easyMod.openYouTubeChannel();
                toast(youtubeChan);
                break;
            case 8:
                easyMod.setUri("");
                easyMod.setPKG("");
                easyMod.openSocialApp();
                toast(socialApp);
                break;
            case 9:
                easyMod.setPKG("");
                easyMod.rateApp();
                toast(rateApp);
                break;
            default:
                break;
        }
    }

    @Override
    public void onSuccess(String appID) {
        toast("appID: " + appID);
    }

    @Override
    public void onFail(String appID, boolean isConnected, boolean isAppInstalled) {
        toast("appID: " + appID + "\n" + "online: " + isConnected + "\n" + "installed: " + isAppInstalled);
    }
}