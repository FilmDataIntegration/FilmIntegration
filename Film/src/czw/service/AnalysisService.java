package czw.service;

import net.sf.json.JSONArray;

public interface AnalysisService {
	public JSONArray getFilmPrice(String film_id, String theater_id, String date);

	public JSONArray getFilmInfo(String film_id);
	
	public JSONArray getFilmList();
	
	public JSONArray getFilmTheater(String film_id, String date);
}
