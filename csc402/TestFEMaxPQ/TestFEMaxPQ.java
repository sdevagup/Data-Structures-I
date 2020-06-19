                                                    Pravallika Devaguptapu
                                                          ID: 1936201  

package csc402;

import java.util.Random;

import stdlib.StdOut;

public class TestFEMaxPQ {

	public static void main(String[] args) {
		int[] arrayLengths = new int[]{1000,2000,4000,8000};
		Random r = new Random();
		for(int i=0; i<arrayLengths.length;i++){
			int inputLen = arrayLengths[i];
			Integer[] inputArray = new Integer[inputLen];
			fillArrayRandom(inputArray, r);
			FEMaxPQ<Integer> maxPQObj = new FEMaxPQ<Integer>();
			//FEMaxPQ maxPQObj = new FEMaxPQ(inputLen);
			for(int j=0;j<inputArray.length;j++){
				maxPQObj.insert(inputArray[j]);
			}
			if(!maxPQObj.isEmpty()){
				while(maxPQObj.size() >0){
					maxPQObj.delMax();
				}
			}
			StdOut.println("------------------------------------------------");
			StdOut.println("Size of Array is : "+ inputLen);
			StdOut.println("Sink count is : "+ maxPQObj.getSinkCounter());
			StdOut.println("Swim count is : "+ maxPQObj.getSwimCounter());
			StdOut.println("nlog(n) for input array "+inputLen+" is : "+ inputLen*Math.log(inputLen));

		}
	}

	private static void fillArrayRandom(Integer[] a, Random r) {
		for (int i = 0; i < a.length; i++) {
			a[i] = r.nextInt();
		}
	}
}
