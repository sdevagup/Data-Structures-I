                                                      Pravallika Devaguptapu
                                                          ID: 1936201     

package csc402;

import java.util.Random;
import algs13.Queue;
import stdlib.StdOut;

public class ReverseQueue {
	static Queue<Integer> q2 = new Queue<>();
	public static void main(String[] args) {
		Random r = new Random();
		Queue<Integer> q1 = new Queue<>();		
		int randomNumer = 0;
		int minValue = -1000;
		int maxValue = 1000;
		for(int i=0;i<16;i++){
			randomNumer = r.nextInt(maxValue + 1 - minValue) + minValue;
			q1.enqueue(randomNumer);
		}
		StdOut.println("Input Queue: "+q1.toString());
		q2 = reverseQueue(q1);
		StdOut.println("Reversed Queue: "+q2.toString());		
	}

	public static Queue<Integer> reverseQueue(Queue<Integer> q1){
		if(!q1.isEmpty()){
			int temp = q1.dequeue();
			reverseQueue(q1);
			q2.enqueue(temp);
		}
		return q2;
	}
}
