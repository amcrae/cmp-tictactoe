package amcrae.research.frameworkcmp.test;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import amcrae.research.frameworkcmp.Vec2D;
import junit.framework.TestCase;

public class Vec2DTest extends TestCase {

	@Test
	public void testIntAdd1() {
		Vec2D<Integer> v1 = new Vec2D<Integer>(1,2);
		Vec2D<Integer> v2 = new Vec2D<Integer>(2,3);
		Vec2D<Integer> expected = new Vec2D<Integer>(3,5);
		Vec2D<Integer> actual = v1.plus(v2);
		assertEquals("Integer addition mismatch", expected, actual);
		System.out.println(actual);
	}

	@Test
	public void testIntAdd2() {
		Vec2D<Integer> v1 = new Vec2D<Integer>(1,2);
		Vec2D<Integer> v2 = new Vec2D<Integer>(-2,-3);
		Vec2D<Integer> expected = new Vec2D<Integer>(-1,-1);
		Vec2D<Integer> actual = v1.plus(v2);
		assertEquals("Integer addition mismatch", expected, actual);
		System.out.println(actual);
	}
	
	@Test
	public void testIntDotOrth() {
		Vec2D<Integer> v1 = new Vec2D<Integer>(4,0);
		Vec2D<Integer> v2 = new Vec2D<Integer>(0,-2);
		Number expected = 0;
		Number actual = v1.dot(v2);
		assertEquals("The dot product of orthogonal Integer vectors should be zero.", expected, actual);
		System.out.println(actual);
	}

	@Test
	public void testIntDotSame() {
		Vec2D<Integer> v1 = new Vec2D<Integer>(2,0);
		Vec2D<Integer> v2 = new Vec2D<Integer>(2,0);
		Number expected = 4;
		Number actual = v1.dot(v2);
		assertEquals("The dot product of Integer vectors should be 4.", expected, actual);
		System.out.println("testIntDotSame=" + actual);
	}

	
	@Test
	public void testIntNorm() {
		Vec2D<Integer> v1 	    = new Vec2D<Integer>(2,0);
		Vec2D<Double> expected = new Vec2D<>(1.0,0.0);
		Vec2D<Double> actual = v1.normalized();
		assertEquals("The normalised Integer vector should be unit-X.", expected, actual);
	}
	
	
	@Test
	public void testDoubleDotOrth() {
		Vec2D<Double> v1 = new Vec2D<>(1.0,+1.0);
		Vec2D<Double> v2 = new Vec2D<>(1.0,-1.0);
		Number expected = 0.0;
		Number actual = v1.dot(v2);
		assertEquals("The dot product of orthogonal Double vectors should be zero.", expected, actual);
		System.out.println(actual);
	}

	@Test
	public void testDoubleDot45() {
		double cos45 = Math.cos(Math.PI/4); //45 degrees
		Vec2D<Double> v1 = new Vec2D<>(cos45,cos45);
		Vec2D<Double> v2 = new Vec2D<>(0.000,1.000);
		Number expected = cos45;
		Number actual = v1.dot(v2);
		assertEquals("The dot product of unit Double vectors 45 degrees apart should be cos(45deg).", expected, actual);
		System.out.println(actual);
	}
	
	@Test 
	public void testIntColinearity() {
		Vec2D<Integer> v1 = new Vec2D<>(11,1);
		Vec2D<Integer> v2 = new Vec2D<>(12,2);
		Vec2D<Integer> v3 = new Vec2D<>(15,5);
		Collection<Vec2D<?>> points = new ArrayList<>();
		points.add(v1);
		points.add(v2);
		points.add(v3);
		boolean expected = true;
		boolean actual = Vec2D.areCoLinear(points, 0.01);
		assertEquals("The three integer vectors should be colinear.", expected, actual);
		System.out.println(actual);
	}

	@Test 
	public void testIntNonColinearity() {
		Vec2D<Integer> v1 = new Vec2D<>(11,1);
		Vec2D<Integer> v2 = new Vec2D<>(18,2);
		Vec2D<Integer> v3 = new Vec2D<>(15,5);
		Collection<Vec2D<?>> points = new ArrayList<>();
		points.add(v1);
		points.add(v2);
		points.add(v3);
		boolean expected = false;
		boolean actual = Vec2D.areCoLinear(points, 0.01);
		assertEquals("The three integer vectors should not be colinear.", expected, actual);
		System.out.println(actual);
	}

}
