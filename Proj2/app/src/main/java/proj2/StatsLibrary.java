package proj2;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;

public class StatsLibrary {

	public double findMean(int[] oddList) {
		// Calculates the mean (average) of the given list
		double sum = 0;
		for (double singleElement : oddList) {
			sum = sum + singleElement;			
		}
		double result = sum/ oddList.length;
		return result;
	}
	
	
	public double findMedian (int[] list) {
		// Calculates the median of the given list
		int n=list.length;
		double median= 0.0;
		Arrays.sort(list);
		if((n/2)%2==0)
		{
			median=(list[n/2] + list[n/2+1])/2;
		}
		else
		{
			median=list[n/2+1];
		}
		return median;
	}

	
	public double findMode (int[] list) {
		// Calculates the mode (most frequent value) of the given list
		int value=0, mcount=0;
		for (int i = 0; i < list.length; ++i) {
			int count = 0;
			for (int j = 0; j < list.length; ++j) {
				if(list[j] == list[i]) ++count;
			}
			if (count > mcount) {
				mcount = count;
				value = list[i];
			}
		}
		return value;
	}
	public double standardDeviation (int[] evenList) {
		// Calculates the standard deviation of the given list
		double sum = 0;	
		double squareSum = 0;
		for (int i=0;i<evenList.length;i++) {
			sum = sum + evenList[i];
		}
			double average = sum/evenList.length;
		for (int i=0;i<evenList.length;i++) {
			squareSum = squareSum + (evenList[i]-average)*(evenList[i]-average);
		}
		return Math.sqrt(squareSum/(evenList.length-1));
		}
	
	public int factorial(int n){
	// Calculates the factorial of a number
	int result = 1;
	if (n < 0) {
	return 0;
	}
	while (n > 1) {
	result *= n;
	n--;
	}
	return result;
	}

	public int permutation(int n, int r){
		// Calculates the permutation of n choose r
		return factorial(n)/factorial(n-r);
	}
	
	public int combinations(int n, int r) {
		// Calculates the combinations of n choose r
	    if(r == 0 || r == n)
	        return 1;
	    return combinations(n-1, r-1) + combinations(n-1, r);
	}
	
	
	public <T> List<T> union(List<T> list1, List<T> list2) {
		// Returns the union of two lists (removes duplicates)
		Set<T> set = new HashSet<T>();

	        set.addAll(list1);
	        set.addAll(list2);

	        return new ArrayList<T>(set);
	    }

	    public <T> List<T> intersection(List<T> list1, List<T> list2) {
	    	// Returns the intersection of two lists (common elements
	    	List<T> list = new ArrayList<T>();

	        for (T t : list1) {
	            if(list2.contains(t)) {
	                list.add(t);
	            }
	        }

	        return list;
	    }
	    public <T> List <T> complement(List<T> list1, List<T> list2) {
	    	// Returns the complement of list1 with respect to list2 (elements in list1 but not in list2)
	    	Set<T> set = new HashSet<T>();

	        set.addAll(list1);
	        set.addAll(list2);
	        for (T t : list1) {
	            if(list2.contains(t)) {
	                set.remove(t);
	            }
	        }
	        return new ArrayList<T>(set);
	    }        
	    
	    public double binomial(int p, int y, double prob){
	    	// Calculates the probability of y successes in p trials with a given probability of success (binomial distribution)
	        double way= combinations(p,y);
	        double prob_each_way = Math.pow(prob,y)*Math.pow((1.0-prob),p-y);
	        return (way*prob_each_way);
	    }
	   
	    public double geometric (int p, double prob) {
	    	// Calculates the probability of getting a success before p failures (geometric distribution)
	    	return 1-Math.pow(1 - prob, p);
    }
	    
	   
	    static Random random = new Random();

	    public int montyHall(boolean playerSwitch) {
	    	// Simulates the Monty Hall problem where a player can choose to switch or not switch doors
	        if (playerSwitch) {

	        	int prizeDoor = random.nextInt(3);
	            int playerChoose = random.nextInt(3);
	            int wrong_door = prizeDoor;
	            while (wrong_door == prizeDoor ||
	                   wrong_door == playerChoose) {
	                wrong_door = random.nextInt(3);
	            }

	            int alternate_door = 3 - (playerChoose + wrong_door);

	            if (alternate_door == prizeDoor) return 1;
	            else return 0;

	        } else {

	        	int prizeDoor = random.nextInt(3);
	            int playerChoose = random.nextInt(3);
	            if (prizeDoor == playerChoose) return 1;
	            else return 0;
	        }

	        
	    }
	    public boolean birthday (int sampleSize) {
	    	// Simulates the birthday problem where it checks if there is a matching birthday in a sample of a given size
	    	ThreadLocalRandom rand = ThreadLocalRandom.current();
			int[] days = new int [365];
			
			for (int i = 0; i < sampleSize; i++) {
				int j = rand.nextInt(days.length);
				days[j]++;
				if (days[j] == 2) {
					return true;
				}
			}
			return false;
	    }
	    
	    public double poisson(int x, double lambda) {
	    	// Calculates the probability of x occurrences in a given interval with a given average rate (Poisson distribution)
	    	return (Math.exp(-lambda) * Math.pow(lambda, x)) / factorial(x);
	    }

	    public double hypergeometric(int K, int n, int k, int x) {
	    	// Calculates the probability of x successes in a sample without replacement from a finite population (hypergeometric distribution)
	    	double numerator = combinations(K, x) * combinations(n - K, k - x);
	        double denominator = combinations(n, k);
	        return numerator / denominator;  
	    }
	    /**
	     * Calculates the range of values within k standard deviations from the mean using Chebyshev's Theorem.
	     *
	     * data       an array of data points
	     * k          the number of standard deviations from the mean
	     * the range of values within k standard deviations from the mean
	     */
	    public double chebyshevRange(int[] data, int k) {
	        int mean = (int) findMean(data);
	        int stdDev = (int) standardDeviation(data);

	        int lowerBound = mean - (k * stdDev);
	        int upperBound = mean + (k * stdDev);

	        return upperBound - lowerBound;
	    }


	    }


	    




