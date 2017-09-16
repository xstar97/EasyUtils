package com.xstar97.easyutils.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.xstar97.easyutils.mods.EasyDeviceMod;
import com.xstar97.easyutils.sample.R;

import butterknife.ButterKnife;

public class EasyDeviceModFragment extends BaseFragment
{
    private String TAG = "EasyDeviceModFragment";

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] list = getResources().getStringArray(R.array.easy_device_mod_api_titles);

        initList(list);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id)
    {
        EasyDeviceMod.abiBuilder easyABIMod = new EasyDeviceMod.abiBuilder();
        EasyDeviceMod.deviceBuilder easyDEVMod = new EasyDeviceMod.deviceBuilder();
        EasyDeviceMod.versionBuilder easyVERMod = new EasyDeviceMod.versionBuilder();
        EasyDeviceMod.utilsBuilder easyUTILMod = new EasyDeviceMod.utilsBuilder();

        String geekyABI_x86 = "geeky abi X86 = ";
        String geekyABI_arm = "geeky abi arm = ";
        String geekyABI_arm64 = "geeky abi arm64 = ";

        String deviceSDK = "Device SDK = ";
        String deviceOS = "Device Base OS = ";
        String deviceSDK_DEP = "Device SDK(DEP) = ";
        String device_abi = "Device ABI = ";

        String deviceMan = "Device manufacturer = ";
        String deviceMod = "Device model = ";
        String deviceBoar = "Device board = ";
        String deviceBoot = "Device bootloader = ";
        String deviceBrand = "Device brand = ";
        String deviceDev = "'Device_device' = ";
        String deviceDisp = "Device display = ";
        String deviceFP = "Device FingerPrint = ";
        String deviceRV = "Device RadioVersion = ";
        String deviceRV_DEP = "Device RadioVersion(DEP) = ";
        String deviceHardw = "Device Hardware = ";
        String deviceHost = "Device Host = ";
        String deviceID = "Device ID = ";
        String deviceProd = "Device product = ";
        String deviceTags = "Device tags = ";
        String deviceType = "Device type = ";
        String deviceUser = "Device user = ";
        String deviceTime = "Device time = ";

        switch (pos) {
            case 0:
                String x86 = easyABIMod.X86;
                toast(geekyABI_x86 + x86);
                break;
            case 1:
                String arm = easyABIMod.ARM;
                toast(geekyABI_arm + arm);
                break;
            case 2:
                String arm64 = easyABIMod.ARM64;
                toast(geekyABI_arm64 + arm64);
                break;
            case 3:
                int DEVICE_SDK_INT = easyVERMod.SDK_INT();
                toast(deviceSDK + DEVICE_SDK_INT);
                break;
            case 4:
                String DEVICE_BASE_OS = easyVERMod.BASE_OS();
                toast(deviceOS + DEVICE_BASE_OS);
                break;
            case 5:
                String DEVICE_SDK_DEP = easyVERMod.SDK_DEP();
                toast(deviceSDK_DEP + DEVICE_SDK_DEP);
                break;
            case 6:
                String[] DEVICE_SUPPORTED_ABIS = easyABIMod.SUPPORTED_ABIS();
                for(String s : DEVICE_SUPPORTED_ABIS) {
                    toast(device_abi + s);
                }
                break;
            case 7:
                String[] DEVICE_SUPPORTED_64_BIT_ABIS = easyABIMod.SUPPORTED_64_BIT_ABIS();
                for(String s : DEVICE_SUPPORTED_64_BIT_ABIS) {
                    toast(device_abi + s);
                }
                break;
            case 8:
                String[] DEVICE_SUPPORTED_32_BIT_ABIS = easyABIMod.SUPPORTED_32_BIT_ABIS();
                for(String s : DEVICE_SUPPORTED_32_BIT_ABIS) {
                    toast(device_abi + s);
                }
                break;
            case 9:
                String DEVICE_CPU_ABI2 = easyABIMod.CPU_ABI2();
                toast(device_abi + DEVICE_CPU_ABI2);
                break;
            case 10:
                String DEVICE_CPU_ABI = easyABIMod.CPU_ABI();
                toast(device_abi + DEVICE_CPU_ABI);
                break;
            case 11:
                String DEVICE_MANUFACTURER = easyDEVMod.MANUFACTURER();
                toast(deviceMan + DEVICE_MANUFACTURER);
                break;
            case 12:
                String DEVICE_MODEL = easyDEVMod.MODEL();
                toast(deviceMod + DEVICE_MODEL);
                break;
            case 13:
                String DEVICE_BOARD = easyDEVMod.BOARD();
                toast(deviceBoar + DEVICE_BOARD);
                break;
            case 14:
                String DEVICE_BOOTLOADER = easyDEVMod.BOOTLOADER();
                toast(deviceBoot + DEVICE_BOOTLOADER);
                break;
            case 15:
                String DEVICE_BRAND = easyDEVMod.BRAND();
                toast(deviceBrand + DEVICE_BRAND);
                break;
            case 16:
                String DEVICE_DEVICE = easyDEVMod.DEVICE();
                toast(deviceDev + DEVICE_DEVICE);
                break;
            case 17:
                String DEVICE_DISPLAY = easyDEVMod.DISPLAY();
                toast(deviceDisp + DEVICE_DISPLAY);
                break;
            case 18:
                String DEVICE_FINGERPRINT = easyDEVMod.FINGERPRINT();
                toast(deviceFP + DEVICE_FINGERPRINT);
                break;
            case 19:
                String DEVICE_RADIO_VERSION = easyUTILMod.RADIO_VERSION();
                toast(deviceRV + DEVICE_RADIO_VERSION);
                break;
            case 20:
                String DEVICE_RADIO_DEP = easyUTILMod.RADIO_DEP();
                toast(deviceRV_DEP + DEVICE_RADIO_DEP);
                break;
            case 21:
                String DEVICE_HARDWARE = easyUTILMod.HARDWARE();
                toast(deviceHardw + DEVICE_HARDWARE);
                break;
            case 22:
                String DEVICE_HOST = easyDEVMod.HOST();
                toast(deviceHost + DEVICE_HOST);
                break;
            case 23:
                String DEVICE_ID = easyUTILMod.ID();
                toast(deviceID + DEVICE_ID);
                break;
            case 24:
                String DEVICE_PRODUCT = easyDEVMod.PRODUCT();
                toast(deviceProd + DEVICE_PRODUCT);
                break;
            case 25:
                String DEVICE_TAGS = easyDEVMod.TAGS();
                toast(deviceTags + DEVICE_TAGS);
                break;
            case 26:
                String DEVICE_TYPE = easyDEVMod.TYPE();
                toast(deviceType + DEVICE_TYPE);
                break;
            case 27:
                String DEVICE_USER = easyDEVMod.USER();
                toast(deviceUser + DEVICE_USER);
                break;
            case 28:
                long DEVICE_TIME = easyUTILMod.TIME();
                toast(deviceTime + DEVICE_TIME);
                break;
            default:
                break;
        }
    }
}