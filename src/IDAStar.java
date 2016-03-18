import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class IDAStar implements SearchAlgorithm {
	private Graph graph;
	private Station goal;
	private boolean foundSolution;
	private HashSet<Station> visited;
	private ArrayList<Station> order;
	
	public IDAStar(Graph graph)
	{
		this.graph = graph;
	}
	
	private double h(Station node)
	{
		return node.distance(goal) * 0.85;
	}
	
	public void getShortestPath(String start, String finish)
	{
		System.out.println("Starting IDA* algorithm");
		order = new ArrayList<Station>();
		foundSolution = false;
		goal = graph.getStationByName(finish);
		double bound = h(graph.getStationByName(start));
		int finalResult = 740;
		
		for(;;)
		{
			visited = new HashSet<Station>();
			visited.add(graph.getStationByName(start));
			double solution = search(graph.getStationByName(start), 0, bound, null);
			if(foundSolution)
			{
				finalResult += (int)solution;
				break;
			}
			else
				bound = solution;
		}
		
		System.out.println(String.format("Arriving at destination at %02d:%02d", finalResult/60, finalResult%60));
		Collections.reverse(order);
		System.out.println("Order of visiting : ");
		for(Station s : order)
			System.out.print(s.getName() + ", ");
		System.out.println("\nIDA* finished");
	}
	
	private double search(Station node, int currentDist, double bound, String line)
	{
		double f = currentDist + h(node);
		if(f > bound)
			return f;
		if(node.getName().equals(goal.getName()))
		{
			foundSolution = true;
			return currentDist;
		}
		double min = Double.MAX_VALUE;
		for(NeighborEntry ne : node.getNeighbors())
		{
			Station next = graph.getStationByName(ne.getName());
			if(visited.contains(next))
				continue;
			int distance = currentDist + 10;
			if(line != null && !line.equals(ne.getLine()))
				distance += 10;
			visited.add(next);
			double temp = search(next, distance, bound, ne.getLine());
			if(foundSolution)
			{
				order.add(next);
				return temp;
			}
			visited.remove(next);
			min = Math.min(temp, min);
		}
		return min;
	}
}
