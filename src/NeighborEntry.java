import org.json.JSONException;
import org.json.JSONObject;

public class NeighborEntry {
	private String name;
	private String line;
	
	public NeighborEntry(JSONObject neighbor) throws JSONException
	{
		this.name = neighbor.getString("name");
		this.line = neighbor.getString("line");
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getLine()
	{
		return line;
	}
}
