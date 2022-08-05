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
	private String value;// 오늘인지 구분
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

	// 날짜에 관련된 달력정보를 가지는 메서드
	public Map<String, Integer> today_info(DateData dateData) {// 정해진 날짜의 달력 정보..
		// 날짜 캘린더 함수에 삽입.
		Map<String, Integer> today_Data = new HashMap<String, Integer>();
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(dateData.getYear()), Integer.parseInt(dateData.getMonth()), 1);// 캘린더에 정해진 날짜 삽입

		int startDay = cal.getMinimum(Calendar.DATE);// 캘린더에 첫날
		int endDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);// 캘린더에 마지막날
		int start = cal.get(Calendar.DAY_OF_WEEK);// 캘린더 요일-> 시작 하는 만큼 빈 공간 삽입

		Calendar todayCal = Calendar.getInstance();// 오늘
		SimpleDateFormat ysdf = new SimpleDateFormat("yyyy");// 오늘년도
		SimpleDateFormat msdf = new SimpleDateFormat("M");// 오늘월

		int today_year = Integer.parseInt(ysdf.format(todayCal.getTime()));// 핵심 년도 가져오기
		int today_month = Integer.parseInt(msdf.format(todayCal.getTime()));// 핵심 월 가져오기

		int search_year = Integer.parseInt(dateData.getYear()); // 검색 년도
		int search_month = Integer.parseInt(dateData.getMonth()) + 1; // 검색 월
		
		// 검색 월과 오늘 월 체크
		int today =-1;
		if (today_year == search_year && today_month == search_month) {
			SimpleDateFormat dsdf = new SimpleDateFormat("dd");//
			today = Integer.parseInt(dsdf.format(todayCal.getTime()));
		}
		// 오늘날의 날짜는 해당 월과 년 일때만 사용
		search_month = search_month - 1;
		// 이전 년,월 + 다음 년,월
		Map<String, Integer> before_after_calendar = before_after_calendar(search_year, search_month);

		// 캘린더 함수 end
		today_Data.put("start", start);// 시작 요일
		today_Data.put("startDay", startDay);// 시작일
		today_Data.put("endDay", endDay); // 마지막일
		today_Data.put("today", today); // 당일
		today_Data.put("search_year", search_year); // 검색년
		today_Data.put("search_month", search_month + 1); // 검색월
		today_Data.put("before_year", before_after_calendar.get("before_year"));// 전년도
		today_Data.put("before_month", before_after_calendar.get("before_month"));// 전달
		today_Data.put("after_year", before_after_calendar.get("after_year")); // 다음년
		today_Data.put("after_month", before_after_calendar.get("after_month"));// 다음달
		return today_Data;
	}

	// 이전달 다음달 및 이전년도 다음년도 월 0으로 초기화 시키고 연도 내리거나 올림!
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

	public DateData() {// 기본 생성자..
	}

	public List<String> getDayOff() {
		return dayOff;
	}

	public void setDayOff(List<String> dayOff) {
		this.dayOff = dayOff;
	}

}
