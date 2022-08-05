package kr.ac.kopo.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.kopo.model.DateData;
import kr.ac.kopo.service.dayService;
import kr.ac.kopo.test.Test2Service;
import kr.ac.kopo.test.TestService;
import kr.ac.kopo.test.TestServiceExtends;

@Controller
public class RootController {
	@Autowired
	dayService service;
	@Autowired
	TestService testExtends;
	@Autowired
	Test2Service testinterface;
	
	@RequestMapping("/")
	private String index(Model model, HttpServletRequest request, @RequestParam (required = false) String year,@RequestParam (required = false) String month) {
		DateData dateData=new DateData();
		dateData.setYear(year);
		dateData.setMonth(month);
		
		Calendar cal = Calendar.getInstance();// 오늘날
		DateData calendarData = null;// 검색일
		
		try {
			if (dateData.getYear().isEmpty() || dateData.getMonth().isEmpty()) {// 월과 년중 하나라도 뺴먹으면 오늘날
				dateData = new DateData(String.valueOf(cal.get(Calendar.YEAR)), String.valueOf(cal.get(Calendar.MONTH)),
					String.valueOf(cal.get(Calendar.DATE)), null);
			}
		} catch (NullPointerException e) {
			dateData = new DateData(String.valueOf(cal.get(Calendar.YEAR)), String.valueOf(cal.get(Calendar.MONTH)),
					String.valueOf(cal.get(Calendar.DATE)), null);
		}
		
		
		
		dateData.setDayOff(service.day_list());// dayoff_result로 기간들 가져옴
		ArrayList<Integer> dayoff_result=new ArrayList<Integer>();//공휴일 일자 담을 예정
		List<String> dayOff=dateData.getDayOff();		//여기는 공휴일 전체
		
		int search_year = Integer.parseInt(dateData.getYear());  // 검색 년
		int search_month = Integer.parseInt(dateData.getMonth()) + 1; // 검색 월
		dayoff_result.add(-1);
		for (int l=0;l<dayOff.size();l++) {
			String year_=dateData.getDayOff().get(l).substring(0, 4);
			String month_=dateData.getDayOff().get(l).substring(5, 7);
			String day_=dateData.getDayOff().get(l).substring(8, 10);
			
			int dayoff_year = Integer.parseInt(year_);// 년 가져오기
			int dayoff_month = Integer.parseInt(month_);// 월 가져오기

			if (dayoff_month == search_month && dayoff_year==search_year) {
				
				dayoff_result.add(Integer.parseInt(day_));
			}//하나씩 dayoff_result가 들어간다.
		}
		
		// 검색 날짜 end

		Map<String, Integer> today_info = dateData.today_info(dateData);
		List<DateData> dateList = new ArrayList<DateData>();

		// 실질적인 달력 데이터 리스트에 데이터 삽입 시작.
		// 일단 시작 인덱스까지 아무것도 없는 데이터 삽입
		for (int i = 1; i < today_info.get("start"); i++) {
			calendarData = new DateData(null, null, null, null);
			dateList.add(calendarData);
		}

		// 날짜 ++ 오늘인지
		for (int i = today_info.get("startDay"); i <= today_info.get("endDay"); i++) {
			for(int a:dayoff_result) {
				if (i == today_info.get("today")) {// 오늘을 표시 하기 위한 date
					calendarData = new DateData(String.valueOf(dateData.getYear()), String.valueOf(dateData.getMonth()),
							String.valueOf(i), "today");
							break;
				} 
	
				else if (i == a) {// 오늘을 표시 하기 위한 date
					calendarData = new DateData(String.valueOf(dateData.getYear()), String.valueOf(dateData.getMonth()),String.valueOf(i), "dayoff");
					break;
				} 
				else {
					calendarData = new DateData(String.valueOf(dateData.getYear()), String.valueOf(dateData.getMonth()),
					String.valueOf(i), "normal_date");
				}
			}
			dateList.add(calendarData);
			}

		// 달력 빈곳 빈 데이터로 삽입
		int index = 7 - dateList.size() % 7;

		if (dateList.size() % 7 != 0) {

			for (int i = 0; i < index; i++) {
				calendarData = new DateData(null, null, null, null);
				dateList.add(calendarData);
			}
		}
		// 배열에 담음
		model.addAttribute("dateList", dateList); // 각각의 날짜를 해당하는 의미
		model.addAttribute("today_info", today_info); // 달력의 대한 정보

		return "index";
	}

	// 유효성 검사 하면 안와서! POST랑 GET 둘 다 들어오도록
	@RequestMapping(value = "/top_menu", method = { RequestMethod.GET, RequestMethod.POST })
	public String top() {
		return "/topmenu";
	}
	
	@RequestMapping("/test")
	public String test() {
//		testExtends.test1();
//		testExtends.test4();

		testinterface.test();
		testinterface.testtesttest();
		return "index";
	}
}