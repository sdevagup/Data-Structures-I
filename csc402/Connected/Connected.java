                                                    Pravallika Devaguptapu
                                                          ID: 1936201  
package csc402;

import java.util.Arrays;

import algs15.QuickUnionUF;
import algs15.UF;
import stdlib.StdIn;
import stdlib.StdOut;

public class Connected {

	public static void main(String[] args) {
		String[] cities;
		StdIn.fromFile("data/citieslist.txt");
		cities = StdIn.readAllStrings();
		UF unionFind = new QuickUnionUF(cities.length);
		StdIn.fromFile("data/connections.txt");
		while (!StdIn.isEmpty()) {
			int p = Arrays.asList(cities).indexOf(StdIn.readString());
			int q = Arrays.asList(cities).indexOf(StdIn.readString());
			unionFind.union(p, q);
		}
		StdIn.fromFile ("data/checkconnection.txt");
		while(!StdIn.isEmpty()){
			int p = Arrays.asList(cities).indexOf(StdIn.readString());
			int q = Arrays.asList(cities).indexOf(StdIn.readString());
			String connectedStatus = unionFind.connected(p, q)==true?"connected by rail":"not connected by rail";
			StdOut.println("["+cities[p] + " " + cities[q]+"] are "+connectedStatus);			
		}

	}
	
}
