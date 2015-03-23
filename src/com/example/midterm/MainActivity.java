package com.example.midterm;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import java.text.NumberFormat; // for currency formatting

import android.text.Editable; // for EditText event handling
import android.text.TextWatcher; // EditText listener
import android.widget.EditText; // for bill amount input
import android.widget.SeekBar; // for changing custom tip percentage
import android.widget.SeekBar.OnSeekBarChangeListener; // SeekBar listener
import android.widget.TextView; // for displaying text
import android.content.Intent;

public class MainActivity extends Activity {

	private static final NumberFormat currencyFormat = 
			NumberFormat.getCurrencyInstance();
	private static final NumberFormat integerFormat =
			NumberFormat.getIntegerInstance();
	private double mpg=25.0;
	private double price=2.5;
	private double distance=0;
	private double cost = 0.0;
	
	private TextView distanceAmountView;
	private TextView costAmountView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		distanceAmountView = (TextView) findViewById(R.id.distanceAmountView);
		costAmountView = (TextView) findViewById(R.id.costAmountView);
		
		costAmountView.setText(currencyFormat.format(cost));
		
		updateInfo();
		
		EditText distanceEdit = (EditText) findViewById(R.id.distanceEdit);
		distanceEdit.addTextChangedListener(distanceEditWatcher);
		
		SeekBar mpgSeekBar = 
		         (SeekBar) findViewById(R.id.mpgSeekBar);
		mpgSeekBar.setOnSeekBarChangeListener(mpgSeekBarListener);
		
		SeekBar gasPriceSeekBar = 
		         (SeekBar) findViewById(R.id.gasPriceSeekBar);
		gasPriceSeekBar.setOnSeekBarChangeListener(gasPriceSeekBarListener);
		
		ImageView carImage = (ImageView) findViewById(R.id.carImage);
		carImage.setOnClickListener(carImageListener);
		
		
	}
	private void updateInfo(){
		cost = (price/mpg)*distance;
		costAmountView.setText(currencyFormat.format(cost));
	}
	
	public OnClickListener carImageListener = new OnClickListener() 
	{
		@Override
		public void onClick(View v){
			String urlString = "http://en.wikipedia.org/wiki/Ford_Tempo";
			Intent webIntent = new Intent(Intent.ACTION_VIEW, 
		            Uri.parse(urlString));
			
			startActivity(webIntent);
		}
	};
	private OnSeekBarChangeListener mpgSeekBarListener = 
		      new OnSeekBarChangeListener() 
	{
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) 
		{
			mpg = progress;
			updateInfo(); 
		}
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) 
		{
		}
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) 
		{
		}
	};
	
	private OnSeekBarChangeListener gasPriceSeekBarListener = 
		      new OnSeekBarChangeListener() 
	{
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) 
		{
			price = progress;
			updateInfo(); 
		}
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) 
		{
		}
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) 
		{
		}
	};
	private TextWatcher distanceEditWatcher = new TextWatcher() 
	{
		@Override
		public void onTextChanged(CharSequence s, int start, 
				int before, int count) 
		{         
			try
			{
	            distance = Double.parseDouble(s.toString());
	            }
			catch (NumberFormatException e)
			{
	            distance = 0.0; 
	            }

	         distanceAmountView.setText(integerFormat.format(distance));
	         updateInfo(); 
	    } 

	      @Override
	      public void afterTextChanged(Editable s) 
	      {
	      } 

	      @Override
	      public void beforeTextChanged(CharSequence s, int start, int count,
	         int after) 
	      {
	      }
	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
