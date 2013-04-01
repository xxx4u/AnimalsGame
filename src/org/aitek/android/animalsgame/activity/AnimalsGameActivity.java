package org.aitek.android.animalsgame.activity;

import org.aitek.android.animalsgame.utils.ScreenUtils;
import org.aitek.android.animalsgame.view.AnimalsGameView;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;

public class AnimalsGameActivity extends Activity {

	AnimalsGameView animalsGameView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		// sets screen coordinates
		Display display = getWindowManager().getDefaultDisplay();
		ScreenUtils.setCoordinates(display.getWidth(), display.getHeight());

		animalsGameView = new AnimalsGameView(this);
		setContentView(animalsGameView);
	}

}