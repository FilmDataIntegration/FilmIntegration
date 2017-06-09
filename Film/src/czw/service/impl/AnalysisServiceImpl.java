package czw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import czw.dao.AnalysisDao;
import czw.service.AnalysisService;
import net.sf.json.JSONArray;

@Service("analysisService")
public class AnalysisServiceImpl implements AnalysisService {
	@Autowired
	private AnalysisDao analysisDao;
	
	public JSONArray getFilmPrice(String film_id, String theater_id, String date){
		return analysisDao.getFilmPrice(film_id, theater_id, date);
	}
	
	public JSONArray getFilmInfo(String film_id){
		return analysisDao.getFilmInfo(film_id);
	}
	
	public JSONArray getFilmList(){
		return analysisDao.getFilmList();
	}
	
	public JSONArray getFilmTheater(String film_id, String date){
		return analysisDao.getFilmTheater(film_id, date);
	}
}
