package com.example.radarchart;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_main);
		setContentView(R.layout.activity_main);
		/*
		 * RadarChart c = new RadarChart(this); c.createChart(new int[] { 70,
		 * 100, 60, 100, 100, 100, 50, 80 }); setContentView(c);
		 */
		
		findViewById(R.id.btnExec).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				try {

					EditText text = (EditText) findViewById(R.id.editCoordinate);

					String[] cord = text.getText().toString().split(",", 0);
					float[] fcord = new float[cord.length];
					for (int i = 0; i < cord.length; i++) {
						fcord[i] = Float.parseFloat(cord[i].trim());
					}

					RadarChart c = (RadarChart) findViewById(R.id.radarChart);
					// c.createChart(new float[] { 50, 70, 60, 60, 100,
					// 90, 50, 80 });
					c.createChart(fcord);
					c.setRatio(3);
					c.show();
				} catch (Exception e) {
					Toast.makeText(MainActivity.this, "”’l‚Ì“ü—Í‚ª•s³‚Å‚·",
							Toast.LENGTH_LONG).show();
				}
			}
		});

	}

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
