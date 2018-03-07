package androidhelper.bluetoothhelper;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;

/**
 * Created by skunnummal on 3/1/2018.
 */

public class BluetoothHelper {
    // EXTRA string to send on to mainactivity
    public static String EXTRA_DEVICE_ADDRESS = "device_address";

    // Member fields
    private BluetoothAdapter mBtAdapter;

    public void Init(Context context){
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();
    }
}
