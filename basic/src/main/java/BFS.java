

import java.util.Arrays;
import java.util.Stack;

public class BFS {
	private static int[][] g = new int[][] { 
		{ 1    }, // 0
		{ 2, 3 }, // 1
		{      }, // 2
		{ 4    }, // 3
		{ 5, 6 }, // 4
		{      }, // 5
		{ 7    }, // 6
		{ 3, 8 }, // 7
		{      }  // 8
	};
	// 0 - white, 1 - grey, 2 - black
	private static int color[] = new int[g.length];
	private static Stack<Integer> stack = new Stack<>();

	public static void main(String args[]) {
		System.out.println(dfs(0));
	}

	private static boolean dfs(int v) {
		color[v] = 1;
		System.out.println("visit " + v);
		stack.push(v);
		for (int x = 0; x < g[v].length; x++) {
			int u = g[v][x];
			if (color[u] == 0) {
				if (dfs(u)) {
					return true;
				}
			}
			if (color[u] == 1) {
				System.out.println("already visited " + u);
				System.out.println("path " + Arrays.toString(color));
				System.out.println("stack " + stack);
				System.out.println("cycle " + stack.subList(stack.indexOf(u), stack.size()));
				return true;
			}
		}
		color[v] = 2;
		stack.pop();
		System.out.println("exit " + v);
		return false;
	}
}
