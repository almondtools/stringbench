package com.almondtools.stringbench;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import junit.framework.ComparisonFailure;

public class CompareResultNotAccepted implements TestRule {

	private CompareResultNotAccepted() {
	}
	
	public static CompareResultNotAccepted compare() {
		return new CompareResultNotAccepted();
	}
	@Override
	public Statement apply(final Statement base, Description description) {
		return new Statement() {
			
			@Override
			public void evaluate() throws Throwable {
				try {
					base.evaluate();
				} catch (ResultNotAcceptedException e) {
					throw new ComparisonFailure("unexpected result: ", e.getExpected().toString(), e.getActual().toString());
				}
				
			}
		};
	}
	
}
