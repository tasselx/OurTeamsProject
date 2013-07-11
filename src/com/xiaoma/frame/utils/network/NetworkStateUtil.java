package com.xiaoma.frame.utils.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.widget.Toast;

import com.xiaoma.frame.R;

/**
 * @Title: NetworkState.java
 * @Package com.lvguo.frame.utils.network
 * @Description: ����״̬������
 * @author XiaoMa
 */
public class NetworkStateUtil
{
    
    /*
     * ��������Ƿ�����
     */
    @SuppressWarnings({"static-access"})
    public static void checkNetWork(Context c)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager)c.getSystemService(c.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null)
        {
            if (info.getState() == State.CONNECTED)
            {
            }
            else
            {
                Toast.makeText(c, c.getString(R.string.network_disconnected), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(c, c.getString(R.string.not_has_network), Toast.LENGTH_SHORT).show();
        }
    }
}
