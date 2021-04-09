package mal.art.fmcinstaller.util.extensions

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageInstaller
import android.util.Log
import mal.art.fmcinstaller.util.base.BaseValues
import java.io.File

fun install(context: Context, packageName: String, apkPath: String) {

    Log.d("TAG", "installation started")

    val packageInstaller = context.packageManager.packageInstaller

    val params = PackageInstaller.SessionParams(PackageInstaller.SessionParams.MODE_FULL_INSTALL)
    params.setAppPackageName(packageName)

    val sessionId = packageInstaller.createSession(params)
    val session = packageInstaller.openSession(sessionId)

    val out = session.openWrite(packageName, 0, -1)
    val fis = File(apkPath).inputStream()
    fis.copyTo(out)
    session.fsync(out)
    out.close()

    session.commit(createIntentSender(context, sessionId))
}

private fun createIntentSender(context: Context, sessionId: Int): IntentSender {

    val pendingIntent = PendingIntent.getBroadcast(
        context,
        sessionId,
        Intent(BaseValues.String.actionInstallComplete),
        0
    )
    return pendingIntent.intentSender
}
