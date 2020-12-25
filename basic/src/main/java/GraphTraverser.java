
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GraphTraverser {
	private static int[][] g = new int[][] { 
			{ 1 }, // 0
			{ 3, 2 }, // 1
			{}, // 2
			{ 4 }, // 3
			{ 5, 6 }, // 4
			{}, // 5
			{ 7 }, // 6
			{ 8 }, // 7
			{} // 8
	};
	// 0 - no, 1 - yes
	private static int discovered[] = new int[g.length];

	public static void main(String args[]) {
		bfs(0);
		discovered = new int[g.length];
		dfs(0);
		discovered = new int[g.length];
		System.out.println("DFS Recursive:");
		dfsRecursive(0);
	}

	private static void bfs(int v) {
		System.out.println("BFS:");
		Queue<Integer> q = new LinkedList<>();
		discovered[v] = 1;
		System.out.println("discovered " + v);
		q.add(v);
		while (!q.isEmpty()) {
			int u = q.poll();
			System.out.println("processing children of " + u);
			for (int x = 0; x < g[u].length; x++) {
				int w = g[u][x];
				if (discovered[w] != 1) {
					discovered[w] = 1;
					System.out.println("discovered " + w);
					q.add(w);
				}
			}
		}
	}

	private static void dfs(int v) {
		System.out.println("DFS:");
		Stack<Integer> q = new Stack<>();
		q.push(v);
		while (!q.isEmpty()) {
			int u = q.pop();
			if (discovered[u] != 1) {
				System.out.println("discovered " + u);
				System.out.println("stack: " + q.toString());
				discovered[v] = 1;
			}
			System.out.println("processing children of " + u);
			for (int x = g[u].length - 1; x >= 0; x--) {
				int w = g[u][x];
				if (discovered[w] != 1) {
					System.out.println("push to stack " + w);
					q.push(w);
					System.out.println("stack: " + q.toString());
				}
			}
		}
	}

	private static void dfsRecursive(int v) {
		discovered[v] = 1;
		System.out.println("discovered " + v);
		for (int x = 0; x < g[v].length; x++) {
			System.out.println("processing children of " + v);
			int u = g[v][x];
			if (discovered[u] == 0) {
				dfsRecursive(u);
			}
		}
		discovered[v] = 2;
	}
}
