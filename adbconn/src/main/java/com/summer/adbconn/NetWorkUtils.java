package com.summer.adbconn;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/**
 * Created by LiCai on 2015/12/9.
 */
public class NetWorkUtils {

//    /**
//     * 检查网络是否可用
//     *
//     * @param paramContext
//     * @return
//     */
//    public static boolean checkEnable(Context paramContext) {
//        boolean i = false;
//        NetworkInfo localNetworkInfo = ((ConnectivityManager) paramContext.getSystemService("connectivity"))
//                .getActiveNetworkInfo();
//        if ((localNetworkInfo != null) && (localNetworkInfo.isAvailable()))
//            return true;
//        return false;
//    }

    /**
     * 将IP的整数形式转换成IP形式
     *
     * @param ipInt
     * @return
     */
    public static String int2ip(int ipInt) {
        StringBuilder sb = new StringBuilder();
        sb.append(ipInt & 0xFF).append(".");
        sb.append((ipInt >> 8) & 0xFF).append(".");
        sb.append((ipInt >> 16) & 0xFF).append(".");
        sb.append((ipInt >> 24) & 0xFF);
        return sb.toString();

    }

    /**
     * 获取当前ip地址
     *
     * @param context
     * @return
     */
    public static String getLocalIpAddress(Context context) {
        try {
            //获取wifi服务
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            //判断wifi是否开启
            if(!wifiManager.isWifiEnabled()){
                wifiManager.setWifiEnabled(true);
            }
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int i = wifiInfo.getIpAddress();
            return int2ip(i);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "获取Ip出错了，请保证是wifi情况下，或重新连接！";
        }
    }
}
