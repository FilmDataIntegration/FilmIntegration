package czw.dao.impl;

import java.math.BigInteger;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import czw.dao.AnalysisDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Repository("analysisDao")
public class AnalysisDaoImpl extends BaseDaoImpl implements AnalysisDao {
	
	public JSONArray getFilmPrice(String film_id, String theater_id, String date){
		JSONArray result = new JSONArray();
		Session session = this.getCurrentSession();
		String sql = "SELECT * "
				   + "FROM ((SELECT film_id, theatre_id, video_hall, time, platform_id, price FROM show_infos WHERE film_id = " + film_id + " AND theatre_id = " + theater_id + " AND DATE_FORMAT(time,'%Y-%m-%d') = '" + date + "') AS t1 INNER JOIN platforms ON t1.platform_id = platforms.id) INNER JOIN films ON t1.film_id = films.id "
				   + "ORDER BY t1.time";
		
		@SuppressWarnings("rawtypes")
		List list = session.createNativeQuery(sql).list();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		for (int i = 0; i < list.size(); i++) {
			JSONObject obj = new JSONObject();
			Object[] os = (Object[]) list.get(i);
			obj.put("film_id", ((BigInteger) os[0]).intValue());
			obj.put("theater_id", ((BigInteger) os[1]).intValue());
			obj.put("hall", os[2]);
			obj.put("time", dateFormat.format(((Date)os[3])));
			obj.put("platform_id", ((BigInteger) os[4]).intValue());
			obj.put("price", os[5]);
			obj.put("platform_name", os[7]);
			obj.put("film_name", os[9]);
			result.add(obj);
		}
		return result;
	}
	
	public JSONArray getFilmInfo(String film_id){
		JSONArray result = new JSONArray();
		Session session = this.getCurrentSession();
		String sql = "SELECT films.id, films.name, platform_films.description, platform_films.score, platform_films.type, platforms.id, platforms.platform "
				   + "FROM films, platform_films, platforms "
				   + "WHERE films.id = " + film_id + " AND platform_films.film_id = films.id AND platform_films.platform_id = platforms.id";
		
		@SuppressWarnings("rawtypes")
		List list = session.createNativeQuery(sql).list();
		
		for (int i = 0; i < list.size(); i++) {
			JSONObject obj = new JSONObject();
			Object[] os = (Object[]) list.get(i);
			obj.put("film_id", ((BigInteger) os[0]).intValue());
			obj.put("film_name", os[1]);
			obj.put("description", os[2]);
			obj.put("score", os[3]);
			obj.put("type", os[4]);
			obj.put("platform_id", ((BigInteger) os[5]).intValue());
			obj.put("platform_name", os[6]);
			result.add(obj);
		}
		return result;
	}
	
	public JSONArray getFilmList(){
		JSONArray result = new JSONArray();
		Session session = this.getCurrentSession();
		String sql = "SELECT * FROM `films` WHERE 1";
		
		@SuppressWarnings("rawtypes")
		List list = session.createNativeQuery(sql).list();
		
		for (int i = 0; i < list.size(); i++) {
			JSONObject obj = new JSONObject();
			Object[] os = (Object[]) list.get(i);
			obj.put("id", ((BigInteger) os[0]).intValue());
			obj.put("name", os[1]);
			obj.put("first_run", ((Date)os[2]).toString());
			obj.put("language", os[3]);
			obj.put("d", ((BigInteger) os[4]).intValue());
			obj.put("area", os[5]);
			obj.put("length", ((BigInteger) os[6]).intValue());
			obj.put("director", os[7]);
			obj.put("main_characters", os[8]);

			result.add(obj);
		}
		return result;
	}
	
	public JSONArray getFilmTheater(String film_id, String date){
		JSONArray result = new JSONArray();
		Session session = this.getCurrentSession();
		String sql = "SELECT theatres.id, theatres.name, theatres.phone, theatres.address, COUNT(*) "
				   + "FROM (SELECT theatre_id FROM show_infos WHERE film_id = " + film_id + " AND DATE_FORMAT(time,'%Y-%m-%d') = '" + date + "') AS t INNER JOIN theatres ON t.theatre_id = theatres.id "
				   + "GROUP BY theatres.id, theatres.name, theatres.phone, theatres.address";
		
		@SuppressWarnings("rawtypes")
		List list = session.createNativeQuery(sql).list();
		
		for (int i = 0; i < list.size(); i++) {
			JSONObject obj = new JSONObject();
			Object[] os = (Object[]) list.get(i);
			obj.put("id", ((BigInteger) os[0]).intValue());
			obj.put("name", os[1]);
			obj.put("phone", os[2]);
			obj.put("address", os[3]);
			obj.put("num", ((BigInteger) os[4]).intValue());

			result.add(obj);
		}
		return result;
	}
}