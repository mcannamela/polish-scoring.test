package com.ultimatepolish.polishscorebook.test;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewPager;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;

import com.ultimatepolish.polishscorebook.GameInProgress;
import com.ultimatepolish.polishscorebook.ThrowTableFragment;
import com.ultimatepolish.scorebookdb.ActiveGame;
import com.ultimatepolish.scorebookdb.enums.DeadType;
import com.ultimatepolish.scorebookdb.enums.RuleType;
import com.ultimatepolish.scorebookdb.enums.ThrowType;
import com.ultimatepolish.scorebookdb.rulesets.RuleSet;

public class GameInProgress_Test extends
		ActivityInstrumentationTestCase2<GameInProgress> {
	private GameInProgress mActivity;
	private ActiveGame ag;
	private RuleSet ruleSet;

	private NumberPicker mPicker;

	private ImageButton btnHigh;
	private ImageView vwDeadHigh;
	private ImageButton btnRight;
	private ImageView vwDeadRight;
	private ImageButton btnLow;
	private ImageView vwDeadLow;
	private ImageButton btnLeft;
	private ImageView vwDeadLeft;
	private ImageButton btnStrike;
	private ImageButton btnTrap;
	private ImageButton btnShort;
	private ImageButton btnPole;
	private ImageButton btnCup;
	private ImageButton btnBottle;

	private ViewPager vp;
	private List<ThrowTableFragment> fragmentArray = new ArrayList<ThrowTableFragment>(
			0);

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
		ruleSet = RuleType.RS00; // TODO: will have to make tests for each
									// ruleset

		btnHigh = (ImageButton) mActivity
				.findViewById(com.ultimatepolish.polishscorebook.R.id.gip_button_high);
		vwDeadHigh = (ImageView) mActivity
				.findViewById(com.ultimatepolish.polishscorebook.R.id.gip_dead_high);
		btnRight = (ImageButton) mActivity
				.findViewById(com.ultimatepolish.polishscorebook.R.id.gip_button_right);
		vwDeadRight = (ImageView) mActivity
				.findViewById(com.ultimatepolish.polishscorebook.R.id.gip_dead_right);
		btnLow = (ImageButton) mActivity
				.findViewById(com.ultimatepolish.polishscorebook.R.id.gip_button_low);
		vwDeadLow = (ImageView) mActivity
				.findViewById(com.ultimatepolish.polishscorebook.R.id.gip_dead_low);
		btnLeft = (ImageButton) mActivity
				.findViewById(com.ultimatepolish.polishscorebook.R.id.gip_button_left);
		vwDeadLeft = (ImageView) mActivity
				.findViewById(com.ultimatepolish.polishscorebook.R.id.gip_dead_left);

		btnStrike = (ImageButton) mActivity
				.findViewById(com.ultimatepolish.polishscorebook.R.id.gip_button_strike);
		btnTrap = (ImageButton) mActivity
				.findViewById(com.ultimatepolish.polishscorebook.R.id.gip_button_trap);
		btnShort = (ImageButton) mActivity
				.findViewById(com.ultimatepolish.polishscorebook.R.id.gip_button_short);
		btnPole = (ImageButton) mActivity
				.findViewById(com.ultimatepolish.polishscorebook.R.id.gip_button_pole);
		btnCup = (ImageButton) mActivity
				.findViewById(com.ultimatepolish.polishscorebook.R.id.gip_button_cup);
		btnBottle = (ImageButton) mActivity
				.findViewById(com.ultimatepolish.polishscorebook.R.id.gip_button_bottle);

		vp = (ViewPager) mActivity
				.findViewById(com.ultimatepolish.polishscorebook.R.id.viewPager_throwsTables);
	}

	public void testPreConditions() {
		assertTrue(mPicker.getValue() == 1);
	}

	public void testFire() {
		TouchUtils.clickView(this, btnPole);
		TouchUtils.clickView(this, btnStrike);
		TouchUtils.clickView(this, btnPole);
		TouchUtils.clickView(this, btnStrike);
		TouchUtils.clickView(this, btnPole);
		TouchUtils.clickView(this, btnStrike);
		// TODO: need to make some assertions here
	}

	public void testWideThrows() {
		int activeColor = Color.RED;
		int inactiveColor = Color.LTGRAY;

		assertEquals(inactiveColor, getButtonColor(vwDeadHigh));
		TouchUtils.longClickView(this, btnHigh);
		assertEquals(activeColor, getButtonColor(vwDeadHigh));
		TouchUtils.clickView(this, btnHigh);
		assertEquals(DeadType.HIGH, ag.getThrow(0).deadType);
		assertTrue("dead high invalid", ruleSet.isValid(ag.getThrow(0)));

		TouchUtils.longClickView(this, btnRight);
		assertEquals(activeColor, getButtonColor(vwDeadRight));
		TouchUtils.clickView(this, btnRight);
		assertEquals(DeadType.RIGHT, ag.getThrow(1).deadType);
		assertTrue(ruleSet.isValid(ag.getThrow(2)));

		TouchUtils.longClickView(this, btnLow);
		assertEquals(activeColor, getButtonColor(vwDeadLow));
		TouchUtils.clickView(this, btnLow);
		assertEquals(DeadType.LOW, ag.getThrow(2).deadType);
		assertTrue(ruleSet.isValid(ag.getThrow(3)));

		TouchUtils.longClickView(this, btnLeft);
		assertEquals(activeColor, getButtonColor(vwDeadLeft));
		TouchUtils.clickView(this, btnLeft);
		assertEquals(DeadType.LEFT, ag.getThrow(3).deadType);
		assertTrue(ruleSet.isValid(ag.getThrow(4)));
	}

	public void testTraps() {
		// test a trap throw
		TouchUtils.clickView(this, btnTrap);
		assertEquals(2, btnTrap.getDrawable().getLevel());
		TouchUtils.clickView(this, btnStrike);
		// TODO: how to go back to a previous throw to check UI?
		assertEquals(ThrowType.TRAP, ag.getThrow(0).throwType);
		assertTrue("trap invalid", ruleSet.isValid(ag.getThrow(0)));

		// test a redeemed trap throw
		TouchUtils.clickView(this, btnTrap);
		assertEquals(2, btnTrap.getDrawable().getLevel());
		TouchUtils.clickView(this, btnBottle);
		assertEquals(ThrowType.TRAP_REDEEMED, ag.getThrow(1).throwType);
		assertTrue("redeemed trap invalid", ruleSet.isValid(ag.getThrow(1)));
	}

	public int getButtonColor(View view) {
		return ((ColorDrawable) view.getBackground()).getColor();
	}
}
