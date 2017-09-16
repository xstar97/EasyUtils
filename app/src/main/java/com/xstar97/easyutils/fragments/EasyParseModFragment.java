package com.xstar97.easyutils.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.xstar97.easyutils.mods.EasyParseMod;
import com.xstar97.easyutils.sample.R;

import butterknife.ButterKnife;

public class EasyParseModFragment extends BaseFragment
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

        String[] list = getResources().getStringArray(R.array.easy_parse_mod_api_titles);

        initList(list);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id)
    {
        EasyParseMod.stringUtils easySMod = new EasyParseMod.stringUtils();
        String msg = "Hello World!";
        easySMod.setString(msg);
        switch (pos) {
            case 0:
                Object obj = new EasyParseMod.parseBuilder()
                .setValue("100")
                .to(1)
                .parse();
                int number = (Integer) obj;
                if(number == 100)
                    toast("parse successful: int: " + number);
                else
                    toast("parse failed");
                break;
            case 1:
                String msgEmpty = "";
                easySMod.setString(msgEmpty);
                boolean empty = easySMod.isEmpty();
                if(empty)
                    toast("string is empty");
                else
                    toast("string contains a message: " + msg);
                break;
            case 2:
                boolean digits = easySMod.containsDigit();
                if(digits)
                    toast("string has digits: " + msg);
                else
                    toast("string does NOT contains any digits!");
                break;
            case 3:
                easySMod.setIndexOfChar("l");
                String indexChar = easySMod.lastIndexOfChar();
                toast("Last Index of Char: " + indexChar);
                break;
            case 4:
                easySMod.setIndexOfString("World");
                String indexString = easySMod.lastIndexOfString();
                toast("Last Index of String: " + indexString);
                break;
            case 5:
                easySMod.setString(msg);
                easySMod.setIndexOf(2);
                String indexNumber = easySMod.lastIndexOf();
                toast("Last Index of int: " + indexNumber);
                break;
            default:
                break;
        }
    }
}