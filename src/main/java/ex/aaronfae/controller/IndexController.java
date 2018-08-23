package ex.aaronfae.controller;

import java.util.List;

import org.apache.http.Header;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ex.aaronfae.domain.Score;
import ex.aaronfae.util.ScoreUtil;

@Controller
public class IndexController {

	private Header cookie = null;
	private String url = null;

	@RequestMapping(value = { "", "/", "/index" })
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	@ResponseBody
	public List<Score> doLogin(String xh, String mm) {
		cookie = ScoreUtil.login(xh, mm);
		url = "http://61.142.33.204/xscj_gc.aspx?xh=" + xh;
		return ScoreUtil.getSemesterScore(url, cookie, "2017-2018", "2");
	}

	@RequestMapping(value = "/getScore")
	@ResponseBody
	public List<Score> getScore() {
		List<Score> score = ScoreUtil.getSemesterScore(url, cookie, "2017-2018", "1");
		return score;
	}
}
