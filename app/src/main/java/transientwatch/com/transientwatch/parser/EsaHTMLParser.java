package transientwatch.com.transientwatch.parser;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import transientwatch.com.transientwatch.Model.Transient;

public class EsaHTMLParser implements Parser {
	private final String MAIN_PAGE_URL = "http://integral.esac.esa.int/bexrbmonitor/webpage_oneplot.php";
	private final int MAIN_PAGE_TABLE_NUMBER = 0;
	private final int NAME_TABLE_NUMBER = 3;
	
	ArrayList<Transient> TABLE_DATA = new ArrayList<Transient>();
	
	
	public Elements fetch_html_table(String url, int id) throws IOException {
		Document doc = Jsoup.connect(url).get();
		Element data_table = doc.select("table").get(id);
		Elements all_rows = data_table.select("tr");
		return all_rows;
	}
	
	public void parse_html_table() throws IOException {
		String url = "http://integral.esac.esa.int/bexrbmonitor/webpage_oneplot.php";
		
		/**
		 * Table:
		 * Name - Right Ascension - Declination - Orbital Period - MAXI - SWIFT - FERMI - PLOT 
		 */
		Elements all_rows = fetch_html_table(MAIN_PAGE_URL, MAIN_PAGE_TABLE_NUMBER);
		
		for (int row = 1; row < all_rows.size(); row++) {
			
			Element tuple = all_rows.get(row);
			Elements attributes = tuple.select("td");
			String link = attributes.get(0).childNode(0).attr("href");
//			System.out.println("internal link = " + link);

//			Elements name_page_table = fetch_html_table(link, NAME_TABLE_NUMBER);
//			Element tuple2 = name_page_table.get(0);
//			Elements name_row = tuple2.select("td");
//			String transient_type = name_row.get(0).text();
//			System.out.println("Internal Result = " + transient_type);
			/**
			 * Object for every row representing a transient
			 */
			Transient obj = new Transient();

			/**
			 * Transient attributes
			 */
			obj.setName(attributes.get(0).text());
//            obj.setType(transient_type);
			System.out.println(obj.getName());
			obj.setRight_ascention(attributes.get(1).text());
			obj.setDeclination(attributes.get(2).text());
			obj.setOrbital_period(attributes.get(3).text());

			obj.setMAXI_prob_change(attributes.get(4).text());
			System.out.println(obj.getMAXI_prob_change());
			obj.setMAXI_average_flux(attributes.get(5).text());
			obj.setMAXI_data(attributes.get(6).text());

			obj.setSWIFT_BAT_prob_change(attributes.get(7).text());
			obj.setSWIFT_BAT_average_flux(attributes.get(8).text());
			obj.setSWIFT_BAT_data(attributes.get(9).text());

			obj.setFERMI_GBM_prob_change(attributes.get(10).text());
			obj.setFERMI_GBM_average_flux(attributes.get(11).text());
			obj.setFERMI_GBM_data(attributes.get(12).text());

			TABLE_DATA.add(obj);
		}
	}

	public ArrayList<Transient> getData() throws IOException {
        this.parse_html_table();
        ArrayList<Transient> copy = new ArrayList<Transient>(TABLE_DATA);

		return copy;
	}
	
}
