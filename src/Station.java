import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Station {
	private String name;
	private double latitude;
	private double longitude;
	private ArrayList<NeighborEntry> neighbors;
	
	public Station(JSONObject station) throws JSONException
	{
		this.name = station.getString("name");
		this.latitude = station.getDouble("latitude");
		this.longitude = station.getDouble("longitude");
		neighbors = new ArrayList<NeighborEntry>();
		JSONArray neighborsList = station.getJSONArray("neighbors");
		for(int i = 0 ; i < neighborsList.length() ; ++i)
			neighbors.add(new NeighborEntry(neighborsList.getJSONObject(i)));
	}
	
	public String getName()
	{
		return name;
	}
	
	public ArrayList<NeighborEntry> getNeighbors()
	{
		return neighbors;
	}
	
	public double distance(Station rhs)
	{
		double p = Math.PI / 180.00;
		double a = 0.5 - Math.cos((rhs.latitude - this.latitude) * p)/2 + 
				Math.cos(this.latitude * p) * Math.cos(rhs.latitude * p) * 
				(1 - Math.cos((rhs.longitude - this.longitude) * p))/2;
		return 12742 * Math.asin(Math.sqrt(a));
	}
}
