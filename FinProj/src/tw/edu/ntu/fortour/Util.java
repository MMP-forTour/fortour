package tw.edu.ntu.fortour;

import java.io.File;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

public class Util {
	public static void deleteFile( File file ) {
		if( file.exists() ) file.delete(); 
	}
	
	public static boolean isOnline( final Object obj ) {
		ConnectivityManager cm = (ConnectivityManager) obj;
		NetworkInfo ni = cm.getActiveNetworkInfo();
		
		if( ni != null && ni.isConnectedOrConnecting() ) return true;
		
		return false;
	}
	
	protected class asycIntentProgress extends AsyncTask<Object, Integer, Void> {
		private ProgressDialog mProgressDialog;
		private Context mContext;
		private String mTitle, mMessage;
		
		public asycIntentProgress( Context ctx, final String title, final String message ) {
			mContext = ctx;
			mTitle   = title;
			mMessage = message;
			mProgressDialog = new ProgressDialog( ctx );
		}
		
		@Override
		protected Void doInBackground(Object... obj) {
			mContext.startActivity( (Intent)( obj[0] ) );
			return null;
		}
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
			mProgressDialog = new ProgressDialog( mContext );
			mProgressDialog.setTitle( mTitle );
			mProgressDialog.setMessage( mMessage );
			mProgressDialog.show();
		}
		
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			
			if( mProgressDialog != null ) mProgressDialog.dismiss();
		}
	}
}
