package kr.ac.kopo.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DateData {

	private String year ="";
	private String month ="";
	private String date="";
	private String value;// �삤�뒛�씤吏� 援щ텇
	private List<String> dayOff;
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	// �궇吏쒖뿉 愿��젴�맂 �떖�젰�젙蹂대�� 媛�吏��뒗 硫붿꽌�뱶
	public Map<String, Integer> today_info(DateData dateData) {// �젙�빐吏� �궇吏쒖쓽 �떖�젰 �젙蹂�..
		// �궇吏� 罹섎┛�뜑 �븿�닔�뿉 �궫�엯.
		Map<String, Integer> today_Data = new HashMap<String, Integer>();
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(dateData.getYear()), Integer.parseInt(dateData.getMonth()), 1);// 罹섎┛�뜑�뿉 �젙�빐吏� �궇吏� �궫�엯

		int startDay = cal.getMinimum(Calendar.DATE);// 罹섎┛�뜑�뿉 泥ル궇
		int endDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);// 罹섎┛�뜑�뿉 留덉�留됰궇
		int start = cal.get(Calendar.DAY_OF_WEEK);// 罹섎┛�뜑 �슂�씪-> �떆�옉 �븯�뒗 留뚰겮 鍮� 怨듦컙 �궫�엯
		
		
	   System.out.println(cal.get(Calendar.YEAR));
	   System.out.println(cal.get(Calendar.MONTH));
	   System.out.println(cal.get(Calendar.DATE));

		Calendar todayCal = Calendar.getInstance();// �삤�뒛
		SimpleDateFormat ysdf = new SimpleDateFormat("yyyy");// �삤�뒛�뀈�룄
		SimpleDateFormat msdf = new SimpleDateFormat("M");// �삤�뒛�썡

		int today_year = Integer.parseInt(ysdf.format(todayCal.getTime()));// �빑�떖 �뀈�룄 媛��졇�삤湲�
		int today_month = Integer.parseInt(msdf.format(todayCal.getTime()));// �빑�떖 �썡 媛��졇�삤湲�

		int search_year = Integer.parseInt(dateData.getYear()); // 寃��깋 �뀈�룄
		int search_month = Integer.parseInt(dateData.getMonth()) + 1; // 寃��깋 �썡
		
		// 寃��깋 �썡怨� �삤�뒛 �썡 泥댄겕
		int today =-1;
		if (today_year == search_year && today_month == search_month) {
			SimpleDateFormat dsdf = new SimpleDateFormat("dd");//
			today = Integer.parseInt(dsdf.format(todayCal.getTime()));
		}
		// �삤�뒛�궇�쓽 �궇吏쒕뒗 �빐�떦 �썡怨� �뀈 �씪�븣留� �궗�슜
		search_month = search_month - 1;
		// �씠�쟾 �뀈,�썡 + �떎�쓬 �뀈,�썡
		Map<String, Integer> before_after_calendar = before_after_calendar(search_year, search_month);

		// 罹섎┛�뜑 �븿�닔 end
		today_Data.put("start", start);// �떆�옉 �슂�씪
		today_Data.put("startDay", startDay);// �떆�옉�씪
		today_Data.put("endDay", endDay); // 留덉�留됱씪
		today_Data.put("today", today); // �떦�씪
		today_Data.put("search_year", search_year); // 寃��깋�뀈
		today_Data.put("search_month", search_month + 1); // 寃��깋�썡
		today_Data.put("before_year", before_after_calendar.get("before_year"));// �쟾�뀈�룄
		today_Data.put("before_month", before_after_calendar.get("before_month"));// �쟾�떖
		today_Data.put("after_year", before_after_calendar.get("after_year")); // �떎�쓬�뀈
		today_Data.put("after_month", before_after_calendar.get("after_month"));// �떎�쓬�떖
		return today_Data;
	}

	// �씠�쟾�떖 �떎�쓬�떖 諛� �씠�쟾�뀈�룄 �떎�쓬�뀈�룄 �썡 0�쑝濡� 珥덇린�솕 �떆�궎怨� �뿰�룄 �궡由ш굅�굹 �삱由�!
	private Map<String, Integer> before_after_calendar(int search_year, int search_month) {
		Map<String, Integer> before_after_data = new HashMap<String, Integer>();
		int before_year = search_year;
		int before_month = search_month - 1;
		int after_year = search_year;
		int after_month = search_month + 1;

		if (before_month < 0) {
			before_month = 11;
			before_year = search_year - 1;
		}

		if (after_month > 11) {
			after_month = 0;
			after_year = search_year + 1;
		}

		before_after_data.put("before_year", before_year);
		before_after_data.put("before_month", before_month);
		before_after_data.put("after_year", after_year);
		before_after_data.put("after_month", after_month);

		return before_after_data;
	}
	public DateData(String year, String month, String date, String value) {
		if ((month != null && month != "") && (date != null && date != "")) {
			this.year = year;
			this.month = month;
			this.date = date;
			this.value = value;
		}
	}

	public DateData() {// 湲곕낯 �깮�꽦�옄..
	}

	public List<String> getDayOff() {
		return dayOff;
	}

	public void setDayOff(List<String> dayOff) {
		this.dayOff = dayOff;
	}

}
