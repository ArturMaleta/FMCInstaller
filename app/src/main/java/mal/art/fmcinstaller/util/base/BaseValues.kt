package mal.art.fmcinstaller.util.base

class BaseValues {

    interface String{
        companion object {
            const val deviceRootCmd = "dpm set-device-owner mal.art.fmcinstaller/mal.art.fmcinstaller.util.adminreceiver.DevAdminReceiver"
            const val appName = "/app-gms-arm64-dev.apk"
            const val pkgName = "pl.pkobp.iko"
            const val actionInstallComplete = "cm.android.intent.action.INSTALL_COMPLETE"
            const val isDeviceOwnerTrue = "is device owner"
            const val isDeviceOwnerFalse = "not device owner"
        }
    }
}