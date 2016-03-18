import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UniformCost implements SearchAlgorithm {
	private Graph graph;
	
	public UniformCost(Graph graph)
	{
		this.graph = graph;
	}
	
	public void getShortestPath(String start, String finish)
	{
		System.out.println("Starting UniformCost algorithm");
		Station startStation = graph.getStationByName(start);
		
		HashMap<Station, String> line = new HashMap<Station, String>();
		HashMap<Station, Integer> distance = new HashMap<Station, Integer>();
		HashMap<Station, Station> source = new HashMap<Station, Station>();
		
		for(Station station : graph.getStations())
			distance.put(station, Integer.MAX_VALUE);
		distance.put(startStation, 0);
		
		int finalResult = 740;
		
		while(!distance.isEmpty())
		{
			Map.Entry<Station, Integer> nextEntry = null;
			for(Map.Entry<Station, Integer> entry : distance.entrySet())
				if(nextEntry == null || (entry.getValue() < nextEntry.getValue()))
					nextEntry = entry;
			
			if(nextEntry.getKey().getName().equals(finish))
			{
				finalResult += nextEntry.getValue();
				break;
			}
			distance.remove(nextEntry.getKey());
			
			for(NeighborEntry neighbor : nextEntry.getKey().getNeighbors())
			{
				if(!distance.containsKey(graph.getStationByName(neighbor.getName())))
					continue;
								
				Integer newDistance = nextEntry.getValue() + 10;
				if(line.get(nextEntry.getKey()) != null && !line.get(nextEntry.getKey()).equals(neighbor.getLine()))
					newDistance += 10;
								
				if(newDistance < distance.get(graph.getStationByName(neighbor.getName())))
				{
					source.put(graph.getStationByName(neighbor.getName()), nextEntry.getKey());
					distance.put(graph.getStationByName(neighbor.getName()), newDistance);
					line.put(graph.getStationByName(neighbor.getName()), neighbor.getLine());
				}
			}
		}
		
		ArrayList<Station> order = new ArrayList<Station>();
		Station temp = graph.getStationByName(finish);
		while(!temp.getName().equals(start))
		{
			order.add(temp);
			temp = source.get(temp);
		}
		Collections.reverse(order);
		
		System.out.println(String.format("Arriving at destination at %02d:%02d", finalResult/60, finalResult%60));
		System.out.println("Order of visiting:");
		for(Station s : order)
			System.out.print(s.getName() + ", ");
		System.out.println("\nUniformCost finished");
	}
}
