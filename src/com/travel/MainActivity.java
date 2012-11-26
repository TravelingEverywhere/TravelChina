package com.travel;




import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;

public class MainActivity extends TabActivity implements CompoundButton.OnCheckedChangeListener{
	//选项卡
	private TabHost tabHost;
	private RadioGroup radioGroup;
	private RadioButton[] radioButtons;
	
	private Intent myHomeIntent;
	private Intent relationIntent;
	private Intent squreIntent;
	private Intent moreIntent;
	
	private int m_iMode = 0;
	
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_activity);
        

        this.init();
        this.setupIntent();
        this.initRadios();
        this.switchMode(0);
	}
	
	
	
	private void init(){
		//初始化Tab
        tabHost = getTabHost();
        //初始化Intent
        myHomeIntent = new Intent(this, MyHomeActivity.class);              
        relationIntent = new Intent(this, RelationActivity.class);       
        squreIntent = new Intent(this, SquareActivity.class);
        moreIntent = new Intent(this, MoreActivity.class);
	}
	
	//安装选项卡
    private void setupIntent()
    {
        tabHost.clearAllTabs();  
        
        String strHome = getString(R.string.main_home);
        tabHost.addTab(tabHost.newTabSpec("myhome_tab").setIndicator(strHome).setContent(myHomeIntent));              
        String strRelation = getString(R.string.main_relation);
        tabHost.addTab(tabHost.newTabSpec("relation_tab").setIndicator(strRelation).setContent(relationIntent));       
        String strSquare = getString(R.string.main_square);
        tabHost.addTab(tabHost.newTabSpec("square_tab").setIndicator(strSquare).setContent(squreIntent));       
        String strMore = getString(R.string.main_more);
        tabHost.addTab(tabHost.newTabSpec("more_tab").setIndicator(strMore).setContent(moreIntent));              
    }
	
    
    
    
    //初始化单选按钮
    private void initRadios(){
    	radioGroup = (RadioGroup)findViewById(R.id.main_radio);
    	radioButtons = new RadioButton[4];
    	
    	for (int i = 0; i < radioButtons.length; i++) {
    		String str = "radio_button" + i;
    		RadioButton localRadioButton = (RadioButton)radioGroup.findViewWithTag(str);
    		radioButtons[i] = localRadioButton;
    		radioButtons[i].setOnCheckedChangeListener(this);
    	}
	  	 
    }

    
    
    private void switchMode(int paramInt) {
        m_iMode = paramInt;
        int i = 0;
        if ((m_iMode < 0) || (m_iMode > 6) || (m_iMode == 1)) {
      	  m_iMode = 0;
        }
        for (;i<4;i++) {
      	  if (paramInt == i) {
      		  radioButtons[i].toggle();
      		  break;
      	  }
        }
      }
    


	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		for (int i=0;i<4;i++) {
			RadioButton rbTmp = radioButtons[i];
			if (buttonView == rbTmp && isChecked) {
				switch(i) {
					case 0:
						tabHost.setCurrentTabByTag("myhome_tab");
						break;
					case 1:
						tabHost.setCurrentTabByTag("relation_tab");
						break;
					case 2:
						tabHost.setCurrentTabByTag("square_tab");
						break;
					case 3:
						tabHost.setCurrentTabByTag("more_tab");
						break;					
				}
				m_iMode = i;
				break;
			}
		}
		
	}
    
    
    
    
    

}
