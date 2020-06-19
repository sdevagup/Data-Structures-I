package csc402;

import java.util.Random;
import algs13.Queue;
import stdlib.StdOut;


public class SingleLineSimulation {


	public static void main(String[] args) {
		Random r = new Random();		

		Queue<Integer> q = new Queue<Integer>();

		final int clockLimit = 3600; // After this time, customers will not be enqueued. 
		final int serviceTime = 150;  // Each customer requires this amount of time to be served.
		final int arrivalProbability = 30; // This is the probability that a customer arrives at any given second.
		final int servers = 4;


		int serverWaitTime = 0;
		int totalWaitTime = 0;
		int customerCount = 0;

		// Run the simulation as long as the clock has not reached its limit.
		for (int currentTime=0; currentTime < clockLimit ; currentTime++) {
			// If we're still under the clock limit, ask
			// "Did a customer arrived?"  If so, put his arrival time into the queue.
			int customerArrivedProbability = r.nextInt(arrivalProbability);
			if (customerArrivedProbability == 0) {				
				// If a customer arrived, enqueue his arrival time.
				q.enqueue(currentTime);
			}
			for(int server=0; server < servers ; server++){
				// Is the server available?
				if (serverWaitTime == 0) { 
					// Yes, it's available.  Is a customer waiting in the queue?
					if (!q.isEmpty()) { 
						// Yes, there's a customer waiting to be served
						int arrivalTime = q.dequeue();

						//Computing the customer's wait time.
						int waitTime = currentTime - arrivalTime;

						//Compute the customer's total wait time.
						totalWaitTime += waitTime;

						//incrementing number of customers served.
						customerCount++;

						//Setting server's wait time to 150.
						serverWaitTime = serviceTime;
					}
				} else {
					// No, the server is not available.  
					// Continue serving the customer who is there.
					serverWaitTime--;
				}
			}			
		}
		StdOut.println("***********************************");
		StdOut.println("Total wait time: "+totalWaitTime);
		StdOut.println("Customers served: " + customerCount);
		StdOut.println("Average wait time: " + (double) totalWaitTime/customerCount + " seconds.");
		StdOut.println("Number of customers still in queue: "+q.size());

	}

}
