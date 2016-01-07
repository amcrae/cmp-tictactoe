package name.mcrae.andrew.research.frameworkcmp;

import java.util.ArrayList;
import java.util.Collection;

/** Attempt to make a generic mathematical vector class which will work for Integers, Floats, or Doubles.
 *  For some operations it will accept operands of different component types.
 * */
public class Vec2D<E extends Number> extends ArrayList<E> {
	

	/** Add two Numbers together even if they are different Number types, 
	 * returning whatever number subclass best preserves the precision of the inputs. */
	public static <F extends Number, G extends Number> F addHetero(F a, G b) {
		// Adding differently typed numeric vectors seems necessarily more complicated than it should be 
		// considering all the numeric types extend Number.
		//  e.g. Error: "The operator + is undefined for the argument type(s) java.lang.Number, java.lang.Number"
		
		if (a instanceof Integer && b instanceof Integer) {
			return (F) new Integer( a.intValue() + b.intValue() );
		} else if (a instanceof Float || b instanceof Float) {
			return (F) new Float( a.floatValue() + b.floatValue() );
		} else if (a instanceof Double || b instanceof Double) {
			return (F) new Double( a.doubleValue() + b.doubleValue() );
		} else throw new java.lang.IllegalArgumentException("Addition not supported for that Numeric data type");
	}

	/** Multiply two Numbers together even if they are different Number types, 
	 * returning whatever number subclass best preserves the precision of the inputs. */
	public static <F extends Number, G extends Number> F mulHetero(F a, G b) {
		// Adding differently typed numeric vectors seems necessarily more complicated than it should be 
		// considering all the numeric types extend Number.
		//  e.g. Error: "The operator + is undefined for the argument type(s) java.lang.Number, java.lang.Number"
		
		if (a instanceof Integer && b instanceof Integer) {
			return (F) new Integer( a.intValue() * b.intValue() );
		} else if (a instanceof Float || b instanceof Float) {
			return (F) new Float( a.floatValue() * b.floatValue() );
		} else if (a instanceof Double || b instanceof Double) {
			return (F) new Double( a.doubleValue() * b.doubleValue() );
		} else throw new java.lang.IllegalArgumentException("Addition not supported for that Numeric data type");
	}
	
	
	public Vec2D() {
		super(2);
		this.add(null);
		this.add(null);
	}
	
	public Vec2D(E x, E y) {
		this();
		this.set(0, x);
		this.set(1, y);
	}

	public E getX() {
		return this.get(0);
	}

	public E getY() {
		return this.get(1);
	}
	
	public E setX(E x) {
		return this.set(0, x);
	}

	public E setY(E y) {
		return this.set(0, y);
	}

	/** Conventional vector magnitude. */
	public double magnitude() {
		double compsq = 0.0;
		for (int i=0; i<this.size(); i++) {
			compsq = addHetero(compsq, mulHetero(this.get(i),this.get(i))); 
		}
		return Math.sqrt(compsq);	
	}
	
	/** Add this Vec2D to another vector to produce a new vector object. */
	public Vec2D<E> plus(Vec2D<? extends Number> other) {
		Vec2D<E> answer = new Vec2D<E>();
		for (int i=0; i<this.size(); i++) {
			answer.set(i, addHetero(this.get(i), other.get(i)) );
		}
		return answer;	
	}

	public Vec2D<E> scale(Number scalar) {
		Vec2D<E> answer = new Vec2D<E>();
		for (int i=0; i<this.size(); i++) {
			answer.set(i, mulHetero(this.get(i), scalar) );
		}
		return answer;	
	}
	
	public Vec2D<E> minus(Vec2D other) {
		Vec2D<E> op2 = other.scale(-1.0);
		Vec2D<E> answer = this.plus( op2 );
		return answer;	
	}
	
	public Vec2D<Double> normalized() {
		Vec2D<Double> answer = new Vec2D<>();
		Double reciprocal = 1.0 / this.magnitude();
		for (int i=0; i<this.size(); i++) {
			answer.set(i, mulHetero(this.get(i).doubleValue(), reciprocal) );
		}
		return answer;	
	}
	
	/** Mathematical dot product of vectors. */
	public Number dot(Vec2D<? extends Number> other) {
		Number answer = 0;
		for (int i=0; i<this.size(); i++) {
			answer = addHetero(answer, mulHetero(this.get(i), other.get(i) ) );
		}
		return answer;
	}
	
	/** Vector projection of this vector in the direction of other vector. */
	public Vec2D<Double> projection(Vec2D<? extends Number> other) {
		return other.normalized().scale( 
				this.dot(other).doubleValue() *other.magnitude() 
		);
	}
	
	/** Check if a collection of points are all along the same line. */
	public static boolean areCoLinear(Collection<Vec2D<? extends Number>> points, double errorThreshold) {
		if (points.size()<=2) return true;
		Vec2D<?> a=null, b=null, dir=null;
		boolean allSmallErrors = true;
		for (Vec2D<?> p : points) {
			if (a==null) a = p;
			else if (b==null) b = p;
			else {
				if (dir==null) dir=b.minus(a).normalized();
				Vec2D<?> toP = p.minus(a);
				Vec2D<?> proj = a.plus( toP.projection(dir) );
				double dist = p.minus(proj).magnitude();
				allSmallErrors = allSmallErrors && dist < errorThreshold;
			}
		}
		return allSmallErrors;
	}
}
