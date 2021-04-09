package mal.art.fmcinstaller.util.installationreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class InstallReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        Toast.makeText(context, "Installation successful", Toast.LENGTH_SHORT).show()
    }
}