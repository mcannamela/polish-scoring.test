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
import com.ultimatepolish.scorebookdb.enums.ThrowResult;
import com.ultimatepolish.scorebookdb.enums.ThrowType;

public class RuleSet01_Test extends
		ActivityInstrumentationTestCase2<GameInProgress> {
	private GameInProgress mActivity;
	private ActiveGame ag;

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

	public RuleSet01_Test() {
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
		// mActivity.ag.ruleSet = RuleType.RS01;
		ag = mActivity.ag;

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

	public void testPreconditions() {
		assertEquals("Wrong ruleset", 1, ag.ruleSet.getId());
		assertTrue(mPicker.getValue() == 1);
	}

	public void testFire() {
		// remember that fire counts are initial counts
		TouchUtils.clickView(this, btnPole); // id 0
		checkFireCounts(0, 0, 0, "");

		TouchUtils.clickView(this, btnStrike); // id 1
		checkFireCounts(1, 1, 0, "");

		TouchUtils.clickView(this, btnPole); // id 2
		checkFireCounts(2, 1, 0, "");

		TouchUtils.clickView(this, btnStrike); // id 3
		checkFireCounts(3, 2, 0, "");

		TouchUtils.clickView(this, btnPole); // id 4
		checkFireCounts(4, 2, 0, "");

		TouchUtils.clickView(this, btnRight); // id 5
		checkFireCounts(5, 3, 0, "");
		checkThrow(5, ThrowType.FIRED_ON, ThrowResult.NA, DeadType.ALIVE, "");

		TouchUtils.clickView(this, btnPole); // id 6
		checkFireCounts(6, 3, 0, "");
		checkThrow(6, ThrowType.POLE, ThrowResult.NA, DeadType.ALIVE, "");
		checkInitScore(6, 0, 0, "");

		TouchUtils.clickView(this, btnStrike); // id 7
		checkFireCounts(7, 4, 0, "");
		checkThrow(7, ThrowType.FIRED_ON, ThrowResult.NA, DeadType.ALIVE, "");
		checkInitScore(7, 2, 0, "");

	}

	public void testWideThrows() {
		int activeColor = Color.RED;
		int inactiveColor = Color.LTGRAY;

		assertEquals(inactiveColor, getButtonColor(vwDeadHigh));
		TouchUtils.longClickView(this, btnHigh);
		assertEquals(activeColor, getButtonColor(vwDeadHigh));
		TouchUtils.clickView(this, btnHigh);
		assertEquals(DeadType.HIGH, ag.getThrow(0).deadType);
		assertTrue("dead high invalid", ag.ruleSet.isValid(ag.getThrow(0)));

		TouchUtils.longClickView(this, btnRight);
		assertEquals(activeColor, getButtonColor(vwDeadRight));
		TouchUtils.clickView(this, btnRight);
		assertEquals(DeadType.RIGHT, ag.getThrow(1).deadType);
		assertTrue(ag.ruleSet.isValid(ag.getThrow(2)));

		TouchUtils.longClickView(this, btnLow);
		assertEquals(activeColor, getButtonColor(vwDeadLow));
		TouchUtils.clickView(this, btnLow);
		assertEquals(DeadType.LOW, ag.getThrow(2).deadType);
		assertTrue(ag.ruleSet.isValid(ag.getThrow(3)));

		TouchUtils.longClickView(this, btnLeft);
		assertEquals(activeColor, getButtonColor(vwDeadLeft));
		TouchUtils.clickView(this, btnLeft);
		assertEquals(DeadType.LEFT, ag.getThrow(3).deadType);
		assertTrue(ag.ruleSet.isValid(ag.getThrow(4)));
	}

	public void testTraps() {
		// test a trap throw
		TouchUtils.clickView(this, btnTrap);
		assertEquals(2, btnTrap.getDrawable().getLevel());
		TouchUtils.clickView(this, btnStrike);
		// TODO: how to go back to a previous throw to check UI?
		assertEquals(ThrowType.TRAP, ag.getThrow(0).throwType);
		assertTrue("trap invalid", ag.ruleSet.isValid(ag.getThrow(0)));

		// test a redeemed trap throw
		TouchUtils.clickView(this, btnTrap);
		assertEquals(2, btnTrap.getDrawable().getLevel());
		TouchUtils.clickView(this, btnBottle);
		assertEquals(ThrowType.TRAP_REDEEMED, ag.getThrow(1).throwType);
		assertTrue("redeemed trap invalid", ag.ruleSet.isValid(ag.getThrow(1)));
	}

	public int getButtonColor(View view) {
		return ((ColorDrawable) view.getBackground()).getColor();
	}

	public void checkThrow(int throwIdx, int expThrowType, int expThrowResult,
			int expDeadType, String errMsg) {
		assertEquals(errMsg + " (" + throwIdx + "), wrong throwType",
				expThrowType, ag.getThrow(throwIdx).throwType);
		assertEquals(errMsg + " (" + throwIdx + "), wrong throwResult",
				expThrowResult, ag.getThrow(throwIdx).throwResult);
		assertEquals(errMsg + " (" + throwIdx + "), wrong deadType",
				expDeadType, ag.getThrow(throwIdx).deadType);
	}

	public void checkThrowSpecial(int throwIdx, String errMsg) {
		// TODO: add checks for special marks
	}

	public void checkFireCounts(int throwIdx, int expP1Cnt, int expP2Cnt,
			String errMsg) {
		int p1Cnt;
		int p2Cnt;
		if (throwIdx % 2 == 0) {
			// P1 is offense for even idx
			p1Cnt = ag.getThrow(throwIdx).offenseFireCount;
			p2Cnt = ag.getThrow(throwIdx).defenseFireCount;
		} else {
			// P2 is offense for odd idx
			p1Cnt = ag.getThrow(throwIdx).defenseFireCount;
			p2Cnt = ag.getThrow(throwIdx).offenseFireCount;
		}
		assertEquals(errMsg + "(" + throwIdx + "), wrong P1 fire count",
				expP1Cnt, p1Cnt);
		assertEquals(errMsg + "(" + throwIdx + "), wrong P2 fire count",
				expP2Cnt, p2Cnt);
	}

	public void checkInitScore(int throwIdx, int expP1Score, int expP2Score,
			String errMsg) {
		int p1Score;
		int p2Score;
		if (throwIdx % 2 == 0) {
			// P1 is offense for even idx
			p1Score = ag.getThrow(throwIdx).initialOffensivePlayerScore;
			p2Score = ag.getThrow(throwIdx).initialDefensivePlayerScore;
		} else {
			p1Score = ag.getThrow(throwIdx).initialDefensivePlayerScore;
			p2Score = ag.getThrow(throwIdx).initialOffensivePlayerScore;
		}
		assertEquals(errMsg + "(" + throwIdx + "), wrong P1 score", expP1Score,
				p1Score);
		assertEquals(errMsg + "(" + throwIdx + "), wrong P2 score", expP2Score,
				p2Score);
	}
}
