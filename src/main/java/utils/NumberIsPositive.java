package utils;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class NumberIsPositive extends TypeSafeMatcher<Integer> {

	@Override
	public void describeTo(Description description) {
		// TODO Auto-generated method stub
		description.appendText("expected positive number");
	}

	@Override
	protected boolean matchesSafely(Integer item) {
		// TODO Auto-generated method stub
		return item>0;
	}
	
	public static Matcher<Integer> numberPositive(){
		return new NumberIsPositive();
	}

}
