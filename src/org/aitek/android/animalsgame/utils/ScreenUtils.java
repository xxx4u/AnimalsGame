package org.aitek.android.animalsgame.utils;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;

public class ScreenUtils {

	static int width, height;
	static Bitmap stretchedDice;
	static Bitmap stretchedFrame;
	static Bitmap stretchedBackground;
	static int normalFontSize;
	static int bigFontSize;
	static private Typeface tf;
	private static int hugeFontSize;

	public static void setCoordinates(int x, int y) {

		width = x;
		height = y;

		normalFontSize = x / 12;
		bigFontSize = x / 9;
		hugeFontSize = x / 5;
	}

	public static int getWidth() {

		return width;
	}

	public static int getHeight() {

		return height;
	}

	public static int getBigFontSize() {

		return bigFontSize;
	}

	public static int getNormalFontSize() {

		return normalFontSize;
	}

	public static float getHugeFontSize() {

		return hugeFontSize;
	}

	public static Typeface getFont(Activity activity) {

		if (tf == null) {
			tf = Typeface.createFromAsset(activity.getAssets(), "fonts/Another_.ttf");
		}

		return tf;
	}

	public static BitmapDrawable getBackground(Resources resources, int mainBackground) {

		if (stretchedBackground == null) {

			Bitmap originalBackground = BitmapFactory.decodeResource(resources, mainBackground);
			stretchedBackground = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);

			Canvas c = new Canvas(stretchedBackground);
			c.drawBitmap(originalBackground, new Rect(0, 0, originalBackground.getWidth(), originalBackground.getHeight()), new Rect(0, 0, width, width), null);
		}

		return new BitmapDrawable(stretchedBackground);
	}
}
