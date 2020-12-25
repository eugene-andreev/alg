import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TreeIterator {
	private static int[][] tree = new int[][] { { 1 }, // 0
			{ 3, 2 }, // 1
			{ 4 }, // 2
			{ 9 }, // 3
			{ 5, 6 }, // 4
			{}, // 5
			{ 7 }, // 6
			{ 8 }, // 7
			{}, // 8
			{} // 9
	};

	public static void main(String args[]) {
		System.out.println("DFS ========================");
		treeDFS(0);
		System.out.println("BFS ========================");
		treeBFS(0);
		System.out.println("DFSR =======================");
		treeDFSR(0);
		System.out.println("BFSR =======================");
		treeBFSR(new LinkedList<Integer>(Arrays.asList(0)));
	}

	public static void treeBFS(int v) {
		Queue<Integer> q = new LinkedList<>();
		q.add(v);
		while (!q.isEmpty()) {
			Integer u = q.poll();
			System.out.println(u);
			for (int x = 0; x < tree[u].length; x++) {
				int w = tree[u][x];
				q.add(w);
			}
			System.out.println("Children: " + q);
		}
	}

	public static void treeDFS(int v) {
		Stack<Integer> stack = new Stack<>();
		stack.push(v);
		while (!stack.isEmpty()) {
			Integer u = stack.pop();
			System.out.println(u);
			for (int x = 0; x < tree[u].length; x++) {
				int w = tree[u][x];
				stack.push(w);
			}
			System.out.println("Children: " + stack);
		}
	}

	public static void treeDFSR(int v) {
		System.out.println(v);
		for (int x = 0; x < tree[v].length; x++) {
			treeDFSR(tree[v][x]);
		}
	}

	public static void treeBFSR(Queue<Integer> q) {
		if (q.isEmpty()) {
			return;
		}

		Integer v = q.poll();
		System.out.println(v);
		for (int x = 0; x < tree[v].length; x++) {
			q.add(tree[v][x]);
		}
		treeBFSR(q);
	}
}
