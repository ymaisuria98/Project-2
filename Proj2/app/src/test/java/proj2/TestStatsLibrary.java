package proj2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
   *
   * Author: Yash Maisuria
   * Date: 1/26/23
   *
*/

public class TestStatsLibrary {
	/**
	   * Main method
	*/
	public static void main (String[] args) { 
		StatsLibrary test = new StatsLibrary();
		// Example of finding the mean of an array of numbers
		int[] someNumbers = {1,2,3,4};
		 // 1 + 2 + 3 + 4 = 10/4 = Expected result 2.5
		double result = test.findMean(someNumbers);
		System.out.println("Average of input " + result);
	
		// Example of using various statistical functions on evenList and oddList arrays
		int[] evenList = { 38, 56, 18, 20, 12, 44 };
		int[] oddList = { 5, 21, 49, 41, 31, 19 };

		System.out.print("Even List: ");
		for (int i = 0; i < evenList.length; i++)
			System.out.print(evenList[i] + " ");
		System.out.println("\nMean: " + (test.findMean(evenList)));
		System.out.println("Median: " + (test.findMedian(evenList)));
		System.out.println("Mode: " + test.findMode(evenList));
		System.out.println("Standard Deviation: " + test.standardDeviation(evenList));

		System.out.print("\nOdd List: ");
		for (int i = 0; i < oddList.length; i++)
			System.out.print(oddList[i] + " ");
		System.out.println("\nMean: " + (test.findMean(oddList)));
		System.out.println("Median: " + (test.findMedian(oddList)));
		System.out.println("Mode: " + test.findMode(oddList));
		System.out.println("Standard Deviation: " + test.standardDeviation(oddList));

		// Example of combining two arrays into a single array and performing statistical calculations on it
		int[] wholeList = new int[evenList.length + oddList.length];
		System.arraycopy(evenList, 0, wholeList, 0, evenList.length);
		System.arraycopy(oddList, 0, wholeList, evenList.length, oddList.length);

		System.out.print("\nWhole List: ");
		for (int i = 0; i < wholeList.length; i++)
			System.out.print(wholeList[i] + " ");
		System.out.println("\nMean: " + (test.findMean(wholeList)));
		System.out.println("Median: " + (test.findMedian(wholeList)));
		System.out.println("Mode: " + test.findMode(wholeList));
		System.out.println("Standard Deviation: " + test.standardDeviation(wholeList));

		// Example of using permutation and combination functions
		Scanner scanner = new Scanner(System.in);
		System.out.print("\nEnter Total Amount In Set: ");
		int n = scanner.nextInt();
		System.out.print("Enter Amount in each Sub-Set: ");
		int r = scanner.nextInt();
		System.out.println("Permutation: (" + n + ", " + r + ") = " + test.permutation(n, r));
		System.out.println("Combinations: (" + n + ", " + r + ") = " + test.combinations(n, r));

		// Example of using set operations on two lists
		List<String> list1 = new ArrayList<String>(Arrays.asList("A", "B", "C"));
		List<String> list2 = new ArrayList<String>(Arrays.asList("B", "C", "D", "E", "F"));

		System.out.println("\nList 1:" + list1);
		System.out.println("List 2:" + list2);
		System.out.println("Intersection: " + new StatsLibrary().intersection(list1, list2));
		System.out.println("Union: " + new StatsLibrary().union(list1, list2));
		System.out.println("Complement: " + new StatsLibrary().complement(list1, list2));

		// Example of using probability and statistical distributions (Binomial and Geometric)
		Scanner scanner1 = new Scanner(System.in);
		System.out.print("\nEnter Number of Trials: ");
		int p = scanner.nextInt();
		System.out.print("Enter Successes: ");
		int y = scanner.nextInt();
		System.out.print("Enter Probability of Success: ");
		double prob = scanner.nextDouble();
		System.out.println("Binomial: (" + p + ", " + y + ", " + prob + ") = " + test.binomial(p, y, prob));
		System.out.println("Geometric: (X < " + p + ") = " + test.geometric(p, prob));

		// Example of simulating Monty Hall problem and birthday paradox
		int times = 100000;
		int winsWithNoSwitch = 0, winsWithSwitch = 0;
		for (int x = 1; x <= times; x++) {
		    winsWithNoSwitch += test.montyHall(false);
		    winsWithSwitch += test.montyHall(true);
		}

		System.out.println("\nWin Chance With Switching: " + winsWithSwitch * 100.0 / times);
		System.out.println("Win Chance Without Switching: " + winsWithNoSwitch * 100.0 / times);

		int numPeople = 30;
		int trial = 1000000;
		int match = 0;
		for (int i = 0; i < trial; i++) {
		    if (test.birthday(numPeople)) {
		        match++;
		    }
		}
		System.out.println("\nSampling Birthdays Number Of People: " + numPeople);
		double probability = (double) match / trial * 100;
		System.out.println("Probability Of A Birthday Match: " + probability);

		// Example of using Poisson and Hypergeometric distributions
		int poissonX = 2; // Number of events
		double poissonLambda = 1.5; // Mean
		double poissonResult = test.poisson(poissonX, poissonLambda);
		System.out.println("Poisson Distribution:");
		System.out.println("P(X = " + poissonX + " | lambda = " + poissonLambda + ") = " + poissonResult);

		int hypergeometricK = 5; // Number of successes in the population
		int hypergeometricN = 10; // Population size
		int hypergeometricKSample = 3; // Sample size
		int hypergeometricX = 2; // Number of successes in the sample
		double hypergeometricResult = test.hypergeometric(hypergeometricK, hypergeometricN, hypergeometricKSample, hypergeometricX);
		System.out.println("Hypergeometric Distribution:");
		System.out.println("P(X = " + hypergeometricX + " | K = " + hypergeometricK + ", N = " + hypergeometricN +
		        ", k = " + hypergeometricKSample + ") = " + hypergeometricResult);
		
		// Define an array of data points
		int[] data = {10, 12, 14, 16, 18, 20, 22};

		// Specify the number of standard deviations from the mean
		int k = 2;

		// Calculate the range of values within k standard deviations from the mean using Chebyshev's Theorem
		double range = test.chebyshevRange(data, k);

		// Print the result
		System.out.println("Chebyshev's Theorem:");
		System.out.println("Range within " + k + " standard deviations: " + range);

	}
}
    
