package ex.aaronfae.util;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ex.aaronfae.domain.Score;

public class ScoreStringPolisher {

	public static List<Score> polish(String html) {
		Document doc = Jsoup.parse(html);
		// TODO 这里用#divShow1取元素存在Bug：#divShow1有时候并不是成绩列表
		Element divShow1 = doc.getElementById("divShow1");
		if (null == divShow1) {
			return null;
		}

		Elements trElements = divShow1.child(0).child(0).getElementsByTag("tr");

		List<Score> scoreList = new ArrayList<>();
		Score item = null;

		for (int i = 1; i < trElements.size(); i++) {
			Elements tdElement = trElements.get(i).getElementsByTag("td");
			item = new Score(tdElement.get(3).text(), tdElement.get(8).text());
			scoreList.add(item);
		}
		return scoreList;
	}
}
