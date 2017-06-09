package czw.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import czw.service.AnalysisService;
import net.sf.json.JSONArray;

@ParentPackage("json-default")
@Controller
@Namespace("/analysis")
public class AnalysisAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	private JSONArray data;
	
	@Autowired
	private AnalysisService analysisService;
	
	@Action(value = "films", results = { @Result(name = "success", type = "json", params = { "root", "data" }) })
	public String getFilmList() {
		data = analysisService.getFilmList();
		return SUCCESS;
	}
	
	@Action(value = "filmTheater", results = { @Result(name = "success", type = "json", params = { "root", "data" }) })
	public String getFilmTheater() {
		analysisService.getFilmTheater(this.request.getParameter("film"), this.request.getParameter("date"));
		return SUCCESS;
	}
	
	@Action(value = "filmPrice", results = { @Result(name = "success", type = "json", params = { "root", "data" }) })
	public String getFilmPrice() {
		data = analysisService.getFilmPrice(this.request.getParameter("film"), this.request.getParameter("theater"), this.request.getParameter("date"));
		return SUCCESS;
	}
	
	@Action(value = "filmInfo", results = { @Result(name = "success", type = "json", params = { "root", "data" }) })
	public String getFilmInfo() {
		data = analysisService.getFilmInfo(this.request.getParameter("film"));
		return SUCCESS;
	}

	public JSONArray getData() {
		return data;
	}

	public void setData(JSONArray data) {
		this.data = data;
	}
}