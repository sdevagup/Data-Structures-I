package csc402;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

public class StdIn {
	private static final String CHARSET_NAME = "UTF-8";
	private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\p{javaWhitespace}+");
	private static final Pattern EVERYTHING_PATTERN = Pattern.compile("\\A");
	private static final Locale LOCALE = Locale.US;

	private static Scanner scanner;

	public static String[] readAllStrings() {
		// we could use readAll.trim().split(), but that's not consistent
		// because trim() uses characters 0x00..0x20 as whitespace
		String[] tokens = WHITESPACE_PATTERN.split(readAll());
		if (tokens.length == 0 || tokens[0].length() > 0) {
			return tokens;
		}

		// don't include first token if it is leading whitespace
		String[] decapitokens = new String[tokens.length-1];
		for (int i = 0; i < tokens.length - 1; i++) {
			decapitokens[i] = tokens[i+1];
		}
		return decapitokens;
	}

	public static String readAll() {
		if (!scanner.hasNextLine()) {
			return "";
		}

		String result = scanner.useDelimiter(EVERYTHING_PATTERN).next();
		// not that important to reset delimeter, since now scanner is empty
		scanner.useDelimiter(WHITESPACE_PATTERN); // but let's do it anyway
		return result;
	}

	public static void readFile(File file) {
		if (file == null) {
			throw new IllegalArgumentException("file argument is null");
		}
		try {
			// for consistency with StdIn, wrap with BufferedInputStream instead of use
			// file as argument to Scanner
			FileInputStream fis = new FileInputStream(file);
			scanner = new Scanner(new BufferedInputStream(fis), CHARSET_NAME);
			scanner.useLocale(LOCALE);
		}
		catch (IOException ioe) {
			throw new IllegalArgumentException("Could not open " + file, ioe);
		}
	}

	public static void close() {
		scanner.close();
	}

	public static double[] readAllDoubles() {
		String[] fields = readAllStrings();
		double[] vals = new double[fields.length];
		for (int i = 0; i < fields.length; i++) {
			vals[i] = Double.parseDouble(fields[i]);
		}
		return vals;
	}
}
