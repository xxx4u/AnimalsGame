package org.aitek.android.animalsgame.view;

import org.aitek.android.animalsgame.R;
import org.aitek.android.animalsgame.utils.ScreenUtils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class AnimalsGameView extends View implements OnTouchListener {

	private Activity animalsGameActivity;
	private Paint paint;

	public AnimalsGameView(Activity animalsGameActivity) {

		super(animalsGameActivity);
		this.animalsGameActivity = animalsGameActivity;
		setOnTouchListener(this);
		paint = new Paint();
	}

	@Override
	protected void onDraw(Canvas canvas) {

		super.onDraw(canvas);
		this.setBackgroundResource(R.drawable.main_background);

		paint.setColor(Color.RED);
		paint.setAntiAlias(true);
		paint.setTypeface(ScreenUtils.getFont(animalsGameActivity));
		paint.setTextSize(ScreenUtils.getHugeFontSize());

		Rect textRect = new Rect();
		paint.getTextBounds(getResources().getString(R.string.app_name), 0, getResources().getString(R.string.app_name).length(), textRect);
		canvas.drawText(getResources().getString(R.string.app_name), (ScreenUtils.getWidth() - textRect.width()) / 2, (ScreenUtils.getHeight() - textRect.height()), paint);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {

		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			int selectedId = -1;

			Intent gameIntent = new Intent();

			gameIntent.setClassName(animalsGameActivity, "org.aitek.android.animalsgame.activity.SoundsActivity");
			animalsGameActivity.startActivity(gameIntent);
		}
		return true;
	}
}
