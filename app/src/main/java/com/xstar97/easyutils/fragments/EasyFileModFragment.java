package com.xstar97.easyutils.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.xstar97.easyutils.mods.EasyFileMod;
import com.xstar97.easyutils.sample.R;

import java.io.File;

import butterknife.ButterKnife;

public class EasyFileModFragment extends BaseFragment
{
    private String TAG = "EasyFileModFragment";

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] list = getResources().getStringArray(R.array.easy_file_mod_api_titles);

        initList(list);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id)
    {
        EasyFileMod.utilsBuilder easyUtilsMod = new EasyFileMod.utilsBuilder();
        EasyFileMod.filesBuilder easyFileMod = new EasyFileMod.filesBuilder(getActivity());
        String createTemp = "created tmp file = ";
        String fileExist = "file exist = ";

        String moveFrom = "move dir from ";

        String canWrite = "Is External Storage Writable = ";
        String canRead = "Is External Storage Readable = ";
        String getCache = "Get Cache Dir = ";

        switch (pos) {
            case 0:
                easyUtilsMod.setFile(easyFileMod.getCacheDir());
                easyUtilsMod.setTempFile("tmp", ".txt");
                File tmpFile = easyUtilsMod.createTempFile();
                toast(createTemp + tmpFile.getAbsolutePath());
                break;
            case 1:
                File tmpDirOld = new File("");
                File tmpDirNew = new File("");
                easyUtilsMod.setFile(tmpDirOld);
                easyUtilsMod.setNewFilePath(tmpDirNew);
                easyUtilsMod.moveFile();
                toast(moveFrom + tmpDirOld + " to " + tmpDirNew);
                break;
            case 2:
                easyUtilsMod.setFile(null);
                easyUtilsMod.deleteOnExit();
                toast(fileExist + easyFileMod.getCacheDir());
                break;
            case 3:
                easyFileMod.setFile(easyFileMod.getCacheDir());
                boolean del = easyUtilsMod.delete();
                toast(fileExist + del);
                break;
            case 4:
                easyUtilsMod.setFile(easyFileMod.getCacheDir());
                easyUtilsMod.deleteByCommand();
                toast(fileExist + easyFileMod.getCacheDir());
                break;
            case 5:
                File mkdir = new File("");
                easyUtilsMod.setFile(mkdir);
                easyUtilsMod.mkDir();
                toast("make dir: " + mkdir);
                break;
            case 6:
                File mkdirs = new File("");
                easyUtilsMod.setFile(mkdirs);
                easyUtilsMod.mkDirs();
                toast("make dirs: " + mkdirs);
                break;
            case 7:
                boolean isWritable = easyFileMod.isExternalStorageWritable();
                toast(canWrite + isWritable);
                break;
            case 8:
                boolean isReadable = easyFileMod.isExternalStorageReadable();
                toast(canRead + isReadable);
                break;
            case 9:
                toast(getCache + easyFileMod.getCacheDir());
                break;
            default:
                break;
        }
    }
}