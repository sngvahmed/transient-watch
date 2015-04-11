package transientwatch.com.transientwatch.parser;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.select.Elements;

import transientwatch.com.transientwatch.Model.Transient;

public interface Parser {
	public ArrayList<Transient> getData() throws IOException;
}
