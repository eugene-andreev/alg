package my.alg;

import java.util.Arrays;

// http://e-maxx.ru/algo/z_function
public class ZFunction {

	public static void main(String args[]) {
		String string = "bbbbacbba";
		zLinear(string);
		zTrivial(string);
	}

	private static void zLinear(String string) {
		char[] s = string.toCharArray();
		int[] z = new int[s.length];

		int n = (int) s.length;
		for (int i = 1, l = 0, r = 0; i < n; ++i) {
			printState(0, i, l, r, z);
			if ((i + z[i - l]) <= r) {
				// this block can be reimplemented with 
				// z[i] = min(r-i, z[i - l]);
				z[i] = z[i - l];
				printState(0, i, l, r, z);
			}
			while (i + z[i] < n && (s[z[i]] == s[i + z[i]])) {
				++z[i];
				printState(1, i, l, r, z);
			}
			if (i + z[i] > r) {
				l = i;
				r = i + z[i];
			}
		}
		System.out.println(Arrays.toString(z));
	}

	private static void zTrivial(String string) {
		char[] s = string.toCharArray();
		int[] z = new int[s.length];

		int n = (int) s.length;
		for (int i = 1; i < n; i++) {
			while (i + z[i] < n && s[z[i]] == s[i + z[i]]) {
				z[i]++;
			}
		}
		System.out.println(Arrays.toString(z));
	}

	private static void printState(int c, int i, int l, int r, int[] z) {
		System.out.println(String.format("(%s): i=%s, L=%s, R=%s, z=%s", c, i, l, r, Arrays.toString(z)));
	}
}
