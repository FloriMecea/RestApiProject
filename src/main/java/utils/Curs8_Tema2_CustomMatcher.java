package utils;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class Curs8_Tema2_CustomMatcher extends TypeSafeMatcher<String> {

	@Override
	public void describeTo(Description description) {
		// TODO Auto-generated method stub
		description.appendText("Cargo capacity este daca are o capacitate mai mare decat 25 de milioane");
	}

	@Override
	protected boolean matchesSafely(String item) {
		// TODO Auto-generated method stub
		Integer var=Integer.parseInt(item);
		System.out.println("cargo capacity is: " + var );
		return var>25000000;
	}

	public static Matcher<String> cargoCapacity(){
		return new Curs8_Tema2_CustomMatcher();
	}
}
