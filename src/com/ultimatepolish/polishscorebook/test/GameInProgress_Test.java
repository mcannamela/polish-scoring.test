package com.ultimatepolish.polishscorebook.test;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.View;
import android.widget.NumberPicker;

import com.ultimatepolish.polishscorebook.GameInProgress;
import com.ultimatepolish.scorebookdb.ActiveGame;
import com.ultimatepolish.scorebookdb.DeadType;

public class GameInProgress_Test extends
		ActivityInstrumentationTestCase2<GameInProgress> {
	private GameInProgress mActivity;
	private ActiveGame ag;
	private NumberPicker mPicker;
	private View btnHigh;
	private View btnHighWide;
	private View btnRight;
	private View btnRightWide;
	private View btnLow;
	private View btnLowWide;
	private View btnLeft;
	private View btnLeftWide;

	public static final int INITIAL_POSITION = 0;

	public GameInProgress_Test() {
		super(GameInProgress.class);
	}

	protected void setUp() throws Exception {
		super.setUp();

		setActivityInitialTouchMode(false);

		Intent intent = new Intent();
		// Long gId = (long) 1;
		// intent.putExtra("GID", gId);

		setActivityIntent(intent);
		mActivity = getActivity();

		mPicker = (NumberPicker) mActivity
				.findViewById(com.ultimatepolish.polishscorebook.R.id.numPicker_catch);
		ag = mActivity.ag;

		btnHigh = mActivity
				.findViewById(com.ultimatepolish.polishscorebook.R.id.gip_button_high);
		btnHighWide = mActivity
				.findViewById(com.ultimatepolish.polishscorebook.R.id.gip_dead_high);
		btnRight = mActivity
				.findViewById(com.ultimatepolish.polishscorebook.R.id.gip_button_right);
		btnRightWide = mActivity
				.findViewById(com.ultimatepolish.polishscorebook.R.id.gip_dead_right);
		btnLow = mActivity
				.findViewById(com.ultimatepolish.polishscorebook.R.id.gip_button_low);
		btnLowWide = mActivity
				.findViewById(com.ultimatepolish.polishscorebook.R.id.gip_dead_low);
		btnLeft = mActivity
				.findViewById(com.ultimatepolish.polishscorebook.R.id.gip_button_left);
		btnLeftWide = mActivity
				.findViewById(com.ultimatepolish.polishscorebook.R.id.gip_dead_left);

	}

	public void testPreConditions() {
		assertTrue(mPicker.getValue() == 1);
	}

	public void testWideThrows() {
		int activeColor = Color.RED;
		int inactiveColor = Color.LTGRAY;

		assertEquals(inactiveColor, getButtonColor(btnHighWide));
		TouchUtils.longClickView(this, btnHigh);
		assertEquals(activeColor, getButtonColor(btnHighWide));
		TouchUtils.clickView(this, btnHigh);
		assertEquals(DeadType.HIGH, ag.getThrow(0).getDeadType());

		TouchUtils.longClickView(this, btnRight);
		assertEquals(activeColor, getButtonColor(btnRightWide));
		TouchUtils.clickView(this, btnRight);
		assertEquals(DeadType.RIGHT, ag.getThrow(1).getDeadType());

		TouchUtils.longClickView(this, btnLow);
		assertEquals(activeColor, getButtonColor(btnLowWide));
		TouchUtils.clickView(this, btnLow);
		assertEquals(DeadType.LOW, ag.getThrow(2).getDeadType());

		TouchUtils.longClickView(this, btnLeft);
		assertEquals(activeColor, getButtonColor(btnLeftWide));
		TouchUtils.clickView(this, btnLeft);
		assertEquals(DeadType.LEFT, ag.getThrow(3).getDeadType());
	}

	public void testFire() {

	}

	public void testSpinnerUI() {

		// mActivity.runOnUiThread(new Runnable() {
		// public void run() {
		// mPicker.requestFocus();
		// mPicker.setValue(INITIAL_POSITION);
		// }
		// });

		// mPicker.performLongClick();

	}

	public int getButtonColor(View view) {
		return ((ColorDrawable) view.getBackground()).getColor();
	}
}
