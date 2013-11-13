package com.ultimatepolish.polishscorebook.test;

import junit.framework.TestSuite;
import android.test.suitebuilder.TestSuiteBuilder;

public class BigTestSuite extends TestSuite {

	public static TestSuite suite() {
		return new TestSuiteBuilder(BigTestSuite.class)
				.includeAllPackagesUnderHere().build();
		// TestSuite suite = new TestSuite();
		// suite.addTest(new GameInProgress_Test());

		// return suite;
	}

}