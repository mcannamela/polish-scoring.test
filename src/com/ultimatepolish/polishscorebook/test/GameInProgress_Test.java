package com.ultimatepolish.polishscorebook.test;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ImageButton;
import android.widget.NumberPicker;

import com.ultimatepolish.polishscorebook.GameInProgress;

public class GameInProgress_Test extends
		ActivityInstrumentationTestCase2<GameInProgress> {
	private GameInProgress mActivity;
	private NumberPicker mPicker;
	private ImageButton btnHigh;

	public static final int ADAPTER_COUNT = 9;
	public static final int INITIAL_POSITION = 0;

	public GameInProgress_Test() {
		super(GameInProgress.class);
	}

	protected void setUp() throws Exception {
		super.setUp();

		setActivityInitialTouchMode(false);

		Intent intent = new Intent();
		Long gId = (long) 1;
		intent.putExtra("GID", gId);

		setActivityIntent(intent);
		mActivity = getActivity();

		mPicker = (NumberPicker) mActivity
				.findViewById(com.ultimatepolish.polishscorebook.R.id.numPicker_catch);
		btnHigh = (ImageButton) mActivity
				.findViewById(com.ultimatepolish.polishscorebook.R.id.gip_button_high);

	}

	public void testPreConditions() {
		assertTrue(mPicker.getValue() == 1);
	}

	public void testHighThrow() {

		mActivity.runOnUiThread(new Runnable() {
			public void run() {
				btnHigh.performLongClick();
				btnHigh.performClick();
			}
		});

	}

	public void testSpinnerUI() {

		// mActivity.runOnUiThread(new Runnable() {
		// public void run() {
		// mPicker.requestFocus();
		// mPicker.setValue(INITIAL_POSITION);
		// }
		// });

		mPicker.performLongClick();

	}
}
