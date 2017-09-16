package com.xstar97.easyutils.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.xstar97.easyutils.interfaces.EasyAudioModListener;
import com.xstar97.easyutils.mods.EasyAudioMod;
import com.xstar97.easyutils.sample.R;

import butterknife.ButterKnife;

public class EasyAudioModFragment extends BaseFragment implements EasyAudioModListener
{
    private String TAG = "EasyAudioModFragment";

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] list = getResources().getStringArray(R.array.easy_audio_mod_api_titles);

        initList(list);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id)
    {
        EasyAudioMod.audioBuilder easyMod = new EasyAudioMod.audioBuilder(getActivity());
        easyMod.setListener(this);
        easyMod.setSound("asset");

        switch (pos) {
            case 0:
                easyMod.play();
                break;
            case 1:
                easyMod.stop();
                break;
            default:
                break;
        }
    }

    @Override
    public void isPlaying() {
        toast("media is playing!?");
    }

    @Override
    public void isStopped() {
        toast("media is Stopped!?");
    }
}