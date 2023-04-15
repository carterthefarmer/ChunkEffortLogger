/* 
 * Author: William Atkin
 * Group: W23
 * Description: A basic Effort Calculating algorithm, as discussed in the Detailed Design and Risk Prototypes
 * 				deliverable. 
 */

package application;

public class EffortCalc {
	
	public static double effortCalculation(double totalClockTime, double breakTime) {
		
		double effortCalc;
		final double storypointPeriod = 30.0;	// a storypoint period is 30 minutes of effort
		
		effortCalc = (totalClockTime) / storypointPeriod;
		
		return effortCalc;
	}

}
