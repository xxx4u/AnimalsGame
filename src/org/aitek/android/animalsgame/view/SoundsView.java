package org.aitek.android.animalsgame.view;

import java.util.HashMap;
import java.util.Map;

import org.aitek.android.animalsgame.R;
import org.aitek.android.animalsgame.utils.Animal;
import org.aitek.android.animalsgame.utils.ScreenUtils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class SoundsView extends View implements OnTouchListener {

	private int maxCols;
	private int maxRows;
	private int widthSpacing = 10;
	private int heightSpacing = 10;
	private int frameWidth;
	private int frameHeight;

	private Map<Animal, Bitmap> animalBitmaps = new HashMap<Animal, Bitmap>();
	private Paint paint;

	public SoundsView(Activity soundsActivity) {

		super(soundsActivity);
		setOnTouchListener(this);
		paint = new Paint();
		paint.setStrokeWidth(3);
		setGrid();

		for (Animal animal : Animal.values()) {

			Log.d("AG", "creating bitmap for " + animal);
			int res = this.getResources().getIdentifier(animal.toString().toLowerCase(), "drawable", "org.aitek.android.animalsgame");
			Bitmap b = BitmapFactory.decodeResource(getResources(), res);
			animalBitmaps.put(animal, Bitmap.createScaledBitmap(b, frameWidth, frameHeight, false));
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {

		setGrid();

		super.onDraw(canvas);
		this.setBackgroundResource(R.drawable.sounds_background);

		int row = 0;
		int col = -1;

		for (Animal animal : Animal.values()) {
			if (col == maxCols - 1) {
				col = -1;
				row++;
			}
			col++;
			draw(canvas, animal, row, col);
		}
		Log.d("FARM", "screen width =" + ScreenUtils.getWidth() + "  height=" + ScreenUtils.getHeight());

	}

	/**
	 * 
	 */
	private void setGrid() {

		if (ScreenUtils.getWidth() > ScreenUtils.getHeight()) {
			maxCols = 6;
			maxRows = 5;
		}
		else {
			maxCols = 4;
			maxRows = 6;
		}
		frameWidth = ScreenUtils.getWidth() / maxCols + widthSpacing;
		frameHeight = ScreenUtils.getHeight() / maxRows + heightSpacing;
		Log.d("AG", "width=" + ScreenUtils.getWidth() + " height=" + ScreenUtils.getHeight() + " framewidth=" + frameWidth + " frameHeight=" + frameHeight);
	}

	private void draw(Canvas canvas, Animal animal, int row, int col) {

		int x = frameWidth * col;
		int y = frameHeight * row;

		canvas.drawRect(new Rect(x - 2, y - 2, x + frameWidth - widthSpacing + 2, y + frameHeight - heightSpacing + 2), paint);
		canvas.drawBitmap(Bitmap.createScaledBitmap(animalBitmaps.get(animal), frameWidth - widthSpacing, frameHeight - heightSpacing, false), x, y, paint);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {

		Log.d("FARM", "nTouch");
		int tappedRow = (int) (event.getY() / frameHeight);
		int tappedCol = (int) (event.getX() / frameWidth);

		Log.d("FARM", "event x= " + event.getX() + " tappedRow " + tappedRow);
		Log.d("FARM", "event y= " + event.getY() + " tappedCol " + tappedCol);
		int index = tappedRow + maxRows * tappedRow + tappedCol;
		int res = this.getResources().getIdentifier(Animal.values()[index].toString().toLowerCase(), "raw", "org.aitek.android.animalsgame");

		Log.d("FARM", "starting mplayer: index=" + index + " res= " + res);

		try {
			MediaPlayer mPlayer = MediaPlayer.create(getContext(), res);
			mPlayer.start();
		}
		catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}
}
