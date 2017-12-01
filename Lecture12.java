import java.util.*;
public class Lecture12 {
	//Graph
	//
	public static void main(String[] args) {
		AdjListGraph graph = new AdjListGraph();
		//graph.bfs("A");


		//graph.dfsRecursion("A");
		graph.bfsLevel("A");
		graph.printPath("A", "E");
	}
	
}
	

class Edge {

	public String startNode;
	public String endNode;
	public int weight;

	public Edge(String startNode, String endNode, int weight) {
		this.startNode = startNode;
		this.endNode = endNode;
		this.weight = weight;
	}
}

class Node {
	String name;
	List<Edge> listEdges;
	boolean visited;

	private Node() {}

	public Node(String name) {
		this.name = name;
		listEdges = new ArrayList<>();
	}

	public void addEdge(String endNode, int weight) {
		Edge edge = new Edge(this.name, endNode, weight);
		listEdges.add(edge);
	}

	public List<String> getNeighbors() {
		List<String> list = new ArrayList<>();

		for (int i = 0; i < listEdges.size(); i++) {
			list.add(listEdges.get(i).endNode);
		}

		return list;
	}
}

class AdjListGraph {
	String startNode;

	public HashMap<String, Node> allNodes;

	public AdjListGraph() {
		allNodes = new HashMap<>();
		initialize();
	}

	public void initialize() {
		Node A = new Node("A");
		Node B = new Node("B");
		Node C = new Node("C");
		Node D = new Node("D");
		Node E = new Node("E");
		Node F = new Node("F");
		Node G = new Node("G");

		A.addEdge("B", 1);

		B.addEdge("C", 1);
		B.addEdge("D", 1);
		B.addEdge("E", 1);

		C.addEdge("E", 1);
		D.addEdge("E", 1);
		E.addEdge("F", 1);
		G.addEdge("D", 1);

		allNodes.put("A", A);
		allNodes.put("B", B);
		allNodes.put("C", C);
		allNodes.put("D", D);
		allNodes.put("E", E);
		allNodes.put("F", F);
		allNodes.put("G", G);
	}

	public void resetVisited() {
		for (Map.Entry<String, Node> n : allNodes.entrySet()) {
			n.getValue().visited = false;
		}
	}

	public void setStartNode(String nodeName) {
		if (allNodes.containsKey(nodeName)) {
			this.startNode = nodeName;
		}
	}

	public void bfs(String startNode) {
		setStartNode(startNode);
		resetVisited();

		Queue<Node> queue = new LinkedList<>();
		queue.offer(allNodes.get(startNode));

		while(!queue.isEmpty()) {
			Node tmp = queue.poll();
			if (!tmp.visited) {
				System.out.println(tmp.name + ", ");
				tmp.visited = true;
			} else continue;

			List<String> neighbors = tmp.getNeighbors();
			for (int i = 0; i < neighbors.size(); i++) {
				queue.offer(allNodes.get(neighbors.get(i)));
			}
		}
		System.out.println();
	}

	//need test, what about different levels
	public void bfsLevel(String startNode) {
		setStartNode(startNode);
		resetVisited();

		Queue<Node> queue = new LinkedList<>();
		queue.offer(allNodes.get(startNode));
		queue.offer(null);

		while(!queue.isEmpty()) {
			Node tmp = queue.poll();
			if (tmp != null) {
				if (!tmp.visited) {
					System.out.println(tmp.name + ", ");
					tmp.visited = true;
				} else continue;

				List<String> neighbors = tmp.getNeighbors();
				for (int i = 0; i < neighbors.size(); i++) {
					queue.offer(allNodes.get(neighbors.get(i)));
				}
				queue.offer(null);
			} else {
				System.out.println();
			}
		}
		System.out.println();
	}

	//need iteration
	public void dfs(String startNode) {
		setStartNode(startNode);
		resetVisited();
		Stack<Node> stack = new Stack<>();
		//stack.push()
	}

	public void dfsRecursion(String node) {
		Node startNode = allNodes.get(node);
		if (!startNode.visited) {
			System.out.print(startNode.name + ", ");
			startNode.visited = true;
			List<String> neighbors = startNode.getNeighbors();
			for (int i = 0; i < neighbors.size(); i++) {
				if (!allNodes.get(neighbors.get(i)).visited) {
					dfsRecursion(neighbors.get(i));
				}
			}
			System.out.println();
		}
	}

	//need work
	public boolean isCyclic() {
		return false;
	}

	public boolean isCyclic(String node) {
		Node startNode = allNodes.get(node);

		if (!startNode.visited) {
			System.out.print(startNode.name + ", ");
			startNode.visited = true;
			List<String> neighbors = startNode.getNeighbors();
			for (int i = 0; i < neighbors.size(); i++) {
				if (!allNodes.get(neighbors.get(i)).visited) {
					dfsRecursion(neighbors.get(i));
				}
			}
			System.out.println();
		}
		return false;
	}

	public void printPath(String source, String dest) {
		HashSet<String> visited = new HashSet<>();
		printAllPath(visited, source, dest);
	}

	//dfs
	private void printAllPath(HashSet<String> visited, String curr, String dest) {
		if (visited.contains(dest)) {
			return;
		}
		visited.add(curr);
		if (dest == curr) {
			for (String s : visited) {
				System.out.print(s + ", ");
			}
			System.out.println();
		}
		
		Node node = allNodes.get(curr);
		List<Edge> edges = node.listEdges;
		for (int i = 0; i < edges.size(); i++) {
			if (!visited.contains(edges.get(i).endNode)) {
				printAllPath(visited, edges.get(i).endNode, dest);
			}
		}
		visited.remove(curr);
	}

	//Dijkstra’s shortest path, need test
	public void shortestPath(String startNode) {
		int dis = 0;
		Node curr = allNodes.get(startNode);
		HashMap<Node, Integer> map = new HashMap<>();
		List<Node> unvisited = new ArrayList<>();
		for (Map.Entry<String, Node> entry : allNodes.entrySet()) {
			map.put(entry.getKey(), Integer.MAX_VALUE);
			unvisited.add(entry.getKey());
		}

		map.put(curr, 0);
		int index = 0;
		for (int i = 0; i < unvisited.size(); i++) {
			if (unvisited.get(i).name.equals(curr.name)) {
				index = i;
				break;
			}		
		}
		unvisited.remove(index);

		//Initialize distance from first node 
		for (int i = 0; i < curr.listEdges.size(); i++) {
			Node endNode = allNodes.get(curr.listEdges.get(i).endNode);
			map.put(map.get(endNode), curr.listEdges.get(i).weight));
		}

		while (unvisited.size() != 0) {
			//find smallest map node for curr
			int smallest = Integer.MAX_VALUE;
			Node smallestNode = null;
			for (int i = 0; i < unvisited.size(); i++) {
				if (map.get(unvisited.get(i)) < dis) {
					smallestNode = unvisited.get(i);
					dis = map.get(unvisited.get(i));
				}
			}
			curr = smallestNode;
			//update distance for each in map
			for (int i = 0; i < curr.listEdges.size(); i++) {
				Node endNode = allNodes.get(curr.listEdges.get(i).endNode);
				map.put(map.get(endNode), dis);
			}
			//remove current from unvisited
			for (int i = 0; i < unvisited.size(); i++) {
				if (unvisited.get(i).name.equals(curr.name)) {
					index = i;
					break;
				}		
			}
			unvisited.remove(index);
		}
	}
}