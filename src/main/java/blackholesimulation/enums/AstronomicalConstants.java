package blackholesimulation.enums;

public enum AstronomicalConstants {

	/**
	 * <p>
	 * The speed of light (<b>c</b>) is the speed at which light travels in a
	 * vacumn.
	 * </p>
	 * 
	 * <blockquote>
	 * <p>
	 * Real velocity: {@code 299.792.458 m/s}
	 * </p>
	 * <p>
	 * Normalized velocity: {@code 300 millions m/s}
	 * </p>
	 * <blockquote>
	 * 
	 */
//	SPEED_OF_LIGHT = 299792458	
	SPEED_OF_LIGHT(300),

	/**
	 * <p>
	 * The universal gravitational constant (<b>G</b>) is a physical constant
	 * involved in the calculation of gravitational effects in Newton's law of
	 * universal gravitation and in Albert Einstein's general theory of relativity.
	 * </p>
	 * 
	 * <blockquote>
	 * <p>
	 * Real value:
	 * {@code 6.67408 × 10^(-11) m^3/(kg * s^2) ---> 0.0000000000667408 m^3/(kg * s^2)}
	 * </p>
	 * <blockquote>
	 * 
	 */
//	UNIVERSAL_GRAVITATIONAL_CONSTANT = 6.67408*Math.pow(10, -11);
	UNIVERSAL_GRAVITATIONAL_CONSTANT(3.54), 
	
	DELTA_TIME(0.1);

	
	
	private double value;

	
	
	private AstronomicalConstants(double value) {
		this.value = value;
	}
	
	
	
	public double getValue() { return value; }

}
