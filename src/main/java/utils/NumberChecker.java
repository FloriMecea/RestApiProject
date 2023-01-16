package utils;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class NumberChecker extends TypeSafeMatcher<String> {

	@Override
	public void describeTo(Description description) {
		// se apeleaza doar daca Integer.parseInt(item); da fail
		description.appendText("expected numbers but got :");
	}

	@Override
	protected boolean matchesSafely(String item) {
		// aici este functionalitatea
		
		try {
			Integer.parseInt(item);
			return true;
		}catch(NumberFormatException e) {
			return false;
		}
		
		
	}
	
	public static Matcher<String> numberOnly(){
		return new NumberChecker();
	}

}
