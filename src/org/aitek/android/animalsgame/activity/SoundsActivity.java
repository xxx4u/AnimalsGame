package org.aitek.android.animalsgame.activity;

import org.aitek.android.animalsgame.utils.ScreenUtils;
import org.aitek.android.animalsgame.view.SoundsView;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;

public class SoundsActivity extends Activity {

	SoundsView soundsView;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		// sets screen coordinates
		Display display = getWindowManager().getDefaultDisplay();
		ScreenUtils.setCoordinates(display.getWidth(), display.getHeight());

		soundsView = new SoundsView(this);
		setContentView(soundsView);
	}

}
