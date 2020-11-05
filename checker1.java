import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class checker1
{
	public static void main ( String args [])
	{
		BufferedReader br = null;
		RoutingMapTree r = new RoutingMapTree();

		try {
			String actionString;
			br = new BufferedReader(new FileReader("actions.txt"));

			while ((actionString = br.readLine()) != null) {
				// System.out.println(actionString);
				System.out.println(r.performAction(actionString));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		// System.out.println(r.getExchange(0).children.top.id);

		// System.out.println(r.getExchange(1).id);

	}
}