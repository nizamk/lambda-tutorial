package functional.concurrency;

/**
 * Created by nizamuddin on 10/04/2015.
 */
public class WebCrawler {
	public String downloadFrom(String site) {
		try {
			System.out.println("Downloading site");
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Content from " + site;
	}

	private void parse(String xml) {

	}
}
