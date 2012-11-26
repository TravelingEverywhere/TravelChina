package com.travel;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class MyHomeActivity extends Activity{
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.myhome_activity);
	}
	
	

}
