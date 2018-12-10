package in.elanic.elanicanandab.network;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import static in.elanic.elanicanandab.util.ConstantStrings.MOBILE_DATA_ENABLE;
import static in.elanic.elanicanandab.util.ConstantStrings.NOT_CONNECT_INTERNET;
import static in.elanic.elanicanandab.util.ConstantStrings.WI_FI_ENABLE;

/**
 * Checking connectivity manager for network state informations Returns the
 * status of connectivity. Progress dialog to use in
 * activities while some process is undergoing
 *
 * @author Anand A <anandabktda@gmail.com>
 */

@SuppressLint("NewApi")
public class NetworkUtil extends Fragment {

    public static int TYPE_NOT_CONNECTED = 0;
    public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;

    /**
     * checking device network state and type of network if connected
     */
    public static int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        assert cm != null;
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;

            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        }
        return TYPE_NOT_CONNECTED;
    }

    /**
     * returning a string value to identify the connection type
     */
    public static String getConnectivityStatusString(Context context) {
        int conn = NetworkUtil.getConnectivityStatus(context);
        String status = null;

        if (conn == NetworkUtil.TYPE_WIFI) {
            status = WI_FI_ENABLE;
        } else if (conn == NetworkUtil.TYPE_MOBILE) {
            status = MOBILE_DATA_ENABLE;
        } else if (conn == NetworkUtil.TYPE_NOT_CONNECTED) {
            status = NOT_CONNECT_INTERNET;
        }
        return status;
    }

    /**
     * Checking network connectivity status of device
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivitymanage = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivitymanage != null;
        return connectivitymanage.getActiveNetworkInfo() != null && connectivitymanage.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}
