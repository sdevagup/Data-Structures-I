package csc402;

import java.util.Random;

import stdlib.StdOut;
import stdlib.StdRandom;
import stdlib.Stopwatch;

public class TimeSorts {
	public static void main(String[] args) {
		Integer[] array;
		Random r = new Random();

		StdOut.println("-- Results from Time sorts --");
		StdOut.printf("%7s\t%12s\t%5s%12s\n", "Length", "Insertion", "Selection","Quicksort");
		for (int i = 7; i <= 17; i++) {
			double insertionEndTime;
			double selectionEndTime;
			double quickEndTime;
			int arraySize = power(2, i);
			array = new Integer[arraySize];
			fillArrayRandom(array, r);

			Stopwatch insertionSortTime = new Stopwatch();
			insertionSort(array);
			insertionEndTime = insertionSortTime.elapsedTime();

			Stopwatch selectionSortTime = new Stopwatch();
			selectionSort(array);
			selectionEndTime = selectionSortTime.elapsedTime();

			Stopwatch quickSortTime = new Stopwatch();
			quickSort(array);
			quickEndTime = quickSortTime.elapsedTime();

			StdOut.printf("%6s\t%10s\t%7s\t%12s\n", arraySize, insertionEndTime, selectionEndTime, quickEndTime);
		}
	}

	// insertion sort
	public static <T extends Comparable<? super T>> void insertionSort(T[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
				exch(a, j, j-1);
			}
		}
	}

	// selection sort
	public static <T extends Comparable<? super T>> void selectionSort(T[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			int min = i;
			for (int j = i+1; j < N; j++) {
				if (less(a[j], a[min])) min = j;
			}
			if (i!=min)
				exch(a, i, min);
		}
	}

	// quicksort the array
	public static <T extends Comparable<? super T>> void quickSort(T[] a) {
		StdRandom.shuffle(a);
		quickSort(a, 0, a.length - 1);
	}

	// quicksort the subarray from a[lo] to a[hi]
	private static <T extends Comparable<? super T>> void quickSort(T[] a, int lo, int hi) {
		if (hi <= lo) return;
		int j = partition(a, lo, hi);
		quickSort(a, lo, j-1);
		quickSort(a, j+1, hi);
	}

	// partition the subarray a[lo .. hi] by returning an index j
	// so that a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
	private static <T extends Comparable<? super T>> int partition(T[] a, int lo, int hi) {
		int i = lo;
		int j = hi + 1;
		T v = a[lo];
		while (true) {

			// find item on lo to swap
			while (less(a[++i], v))
				if (i == hi) break;

			// find item on hi to swap
			while (less(v, a[--j]))
				if (j == lo) break;      // redundant since a[lo] acts as sentinel

			// check if pointers cross
			if (i >= j) break;

			exch(a, i, j);
		}

		// put v = a[j] into position
		exch(a, lo, j);

		// with a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
		return j;
	}

	// is v < w ?
	private static <T extends Comparable<? super T>> boolean less(T v, T w) {
		return (v.compareTo(w) < 0);
	}

	// exchange a[i] and a[j]
	private static <T> void exch(T[] a, int i, int j) {
		T swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	private static void fillArrayRandom(Integer[] a, Random r) {
		for (int i = 0; i < a.length; i++) {
			a[i] = r.nextInt();
		}
	}

	private static int power(int n, int e) {
		if (e == 0) return 1;
		if (e % 2 == 0) return square(power(n, e/2));
		return n*power(n, e-1);
	}

	private static int square(int n) {
		return n*n;
	}
}
