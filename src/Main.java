import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
	public static void main(String[] args) {
		String rawJSON = null;
		try {
			rawJSON = new String(Files.readAllBytes(Paths.get("resources/stations.json")));
		} catch (IOException e) {
			System.out.println("Couldn't open stations file.");
			System.out.println(e.getMessage());
		}
		
		Graph graph = new Graph(rawJSON);
		
		SearchAlgorithm algorithm = new UniformCost(graph);
		SearchAlgorithm algorithm2 = new IDAStar(graph);
		
		algorithm.getShortestPath("Ealing Broadway", "West Hampstead");
		System.out.println("\n\n");
		algorithm2.getShortestPath("Ealing Broadway", "West Hampstead");
	}
}
