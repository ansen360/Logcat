package com.ansen.logcat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class AlogBootReceiver extends BroadcastReceiver {
	private PreferenceEngine mPrefs;

	@Override
	public void onReceive(Context context, Intent intent) {
		mPrefs = new PreferenceEngine(context);
		if (mPrefs.isNetdebug()) {
			Comm.shell("setprop service.adb.tcp.port 5555\nstop adbd\nstart adbd\n");
		}
		if (mPrefs.isAutosave()) {
			Intent svcIntent = new Intent(context, LogSaveService.class);
			context.startService(svcIntent);
		}
	}
}
