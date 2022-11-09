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
	private String index(Model model, HttpServletRequest request, @RequestParam(required = false) String year,
			@RequestParam(required = false) String month) {
		DateData dateData = new DateData();
		dateData.setYear(year);
		dateData.setMonth(month);

		Calendar cal = Calendar.getInstance();// �삤�뒛�궇
		DateData calendarData = null;// 寃��깋�씪

		try {
			if (dateData.getYear().isEmpty() || dateData.getMonth().isEmpty()) {// �썡怨� �뀈以� �븯�굹�씪�룄 類대㉨�쑝硫� �삤�뒛�궇
				
			}
		} catch (Exception e) {
			dateData = new DateData(String.valueOf(cal.get(Calendar.YEAR)), String.valueOf(cal.get(Calendar.MONTH)),
					String.valueOf(cal.get(Calendar.DATE)), null);
		}
		

		dateData.setDayOff(service.day_list());// dayoff_result濡� 湲곌컙�뱾 媛��졇�샂
		ArrayList<Integer> dayoff_result = new ArrayList<Integer>();// 怨듯쑕�씪 �씪�옄 �떞�쓣 �삁�젙
		List<String> dayOff = dateData.getDayOff(); // �뿬湲곕뒗 怨듯쑕�씪 �쟾泥�

		int search_year = Integer.parseInt(dateData.getYear()); // 寃��깋 �뀈
		int search_month = Integer.parseInt(dateData.getMonth()) + 1; // 寃��깋 �썡
		dayoff_result.add(-1);
		for (int l = 0; l < dayOff.size(); l++) {
			String year_ = dateData.getDayOff().get(l).substring(0, 4);
			String month_ = dateData.getDayOff().get(l).substring(5, 7);
			String day_ = dateData.getDayOff().get(l).substring(8, 10);

			int dayoff_year = Integer.parseInt(year_);// �뀈 媛��졇�삤湲�
			int dayoff_month = Integer.parseInt(month_);// �썡 媛��졇�삤湲�

			if (dayoff_month == search_month && dayoff_year == search_year) {

				dayoff_result.add(Integer.parseInt(day_));
			} // �븯�굹�뵫 dayoff_result媛� �뱾�뼱媛꾨떎.
		}

		// 寃��깋 �궇吏� end

		Map<String, Integer> today_info = dateData.today_info(dateData);
		List<DateData> dateList = new ArrayList<DateData>();

		// �떎吏덉쟻�씤 �떖�젰 �뜲�씠�꽣 由ъ뒪�듃�뿉 �뜲�씠�꽣 �궫�엯 �떆�옉.
		// �씪�떒 �떆�옉 �씤�뜳�뒪源뚯� �븘臾닿쾬�룄 �뾾�뒗 �뜲�씠�꽣 �궫�엯
		for (int i = 1; i < today_info.get("start"); i++) {
			calendarData = new DateData(null, null, null, null);
			dateList.add(calendarData);
		}

		// �궇吏� ++ �삤�뒛�씤吏�
		for (int i = today_info.get("startDay"); i <= today_info.get("endDay"); i++) {
			for (int a : dayoff_result) {
				if (i == today_info.get("today")) {// �삤�뒛�쓣 �몴�떆 �븯湲� �쐞�븳 date
					calendarData = new DateData(String.valueOf(dateData.getYear()), String.valueOf(dateData.getMonth()),
							String.valueOf(i), "today");
					break;
				}

				else if (i == a) {// �삤�뒛�쓣 �몴�떆 �븯湲� �쐞�븳 date
					calendarData = new DateData(String.valueOf(dateData.getYear()), String.valueOf(dateData.getMonth()),
							String.valueOf(i), "dayoff");
					break;
				} else {
					calendarData = new DateData(String.valueOf(dateData.getYear()), String.valueOf(dateData.getMonth()),
							String.valueOf(i), "normal_date");
				}
			}
			dateList.add(calendarData);
		}

		// �떖�젰 鍮덇납 鍮� �뜲�씠�꽣濡� �궫�엯
		int index = 7 - dateList.size() % 7;

		if (dateList.size() % 7 != 0) {

			for (int i = 0; i < index; i++) {
				calendarData = new DateData(null, null, null, null);
				dateList.add(calendarData);
			}
		}
		// 諛곗뿴�뿉 �떞�쓬
		model.addAttribute("dateList", dateList); // 媛곴컖�쓽 �궇吏쒕�� �빐�떦�븯�뒗 �쓽誘�
		model.addAttribute("today_info", today_info); // �떖�젰�쓽 ���븳 �젙蹂�

		return "index";
	}

	// �쑀�슚�꽦 寃��궗 �븯硫� �븞���꽌! POST�옉 GET �몮 �떎 �뱾�뼱�삤�룄濡�
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