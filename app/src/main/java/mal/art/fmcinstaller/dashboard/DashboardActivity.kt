package mal.art.fmcinstaller.dashboard

import android.Manifest
import android.app.admin.DevicePolicyManager
import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.os.Environment.DIRECTORY_DOWNLOADS
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import mal.art.fmcinstaller.databinding.ActivityDashboardBinding
import mal.art.fmcinstaller.util.base.BaseValues
import mal.art.fmcinstaller.util.extensions.install

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // only on rooted devices, don't know how to do it on non-rooted devices programmatically
        try {
            Runtime.getRuntime().exec(BaseValues.String.deviceRootCmd)
        } catch (e: java.lang.Exception) {
            Log.d("TAG_deviceOwner_err", "$e")
        }

        val requiredPermissions = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        binding.installBtn.setOnClickListener {
            val myFile =
                Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS).path + BaseValues.String.appName
            val pkgName = BaseValues.String.pkgName

            try {
                ActivityCompat.requestPermissions(this, requiredPermissions, 0)

                install(this, pkgName, myFile)

            } catch (e: Exception) {
                Log.d("TAG_err", "$e")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val dpm = getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        if (dpm.isDeviceOwnerApp(packageName))
            Log.d("TAG_dev_owner", BaseValues.String.isDeviceOwnerTrue)
        else
            Log.d("TAG_dev_owner", BaseValues.String.isDeviceOwnerFalse)
    }
}