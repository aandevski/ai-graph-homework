import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Graph {
	private ArrayList<Station> stations;
	
	public Graph(String jsonFile)
	{
		JSONTokener tokener = new JSONTokener(jsonFile);
		stations = new ArrayList<Station>();
		try 
		{
			JSONArray stationList = new JSONObject(tokener).getJSONArray("stations");
			for(int i = 0 ; i < stationList.length() ; ++i)
				stations.add(new Station(stationList.getJSONObject(i)));
		} 
		catch (JSONException e) 
		{
			System.out.println("Invalid JSON file");
			System.out.println(e.getMessage());
		}
	}
	
	public Station getStationByName(String name)
	{
		for(Station station : stations)
			if(station.getName().equals(name))
				return station;
		return stations.get(0);
	}
	
	public ArrayList<Station> getStations()
	{
		return stations;
	}
}
