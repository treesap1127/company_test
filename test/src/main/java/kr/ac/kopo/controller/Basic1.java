package kr.ac.kopo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.ac.kopo.model.One;
import kr.ac.kopo.model.OneExcel;
import kr.ac.kopo.model.OneFile;
import kr.ac.kopo.service.BasicService;
import kr.ac.kopo.util.Excel;
import kr.ac.kopo.util.Pager;


@Controller
@RequestMapping("basic1")
public class Basic1 {
	@Autowired
	BasicService service;
	
	@GetMapping("/list")
	public String list(Model model,Pager pager) {
		List<One> list =service.list(pager);
		model.addAttribute("list", list);
		return "basic1/list";
	}
	@GetMapping("/add")
	public String add() {
		return "basic1/add";
	}
	@PostMapping("/add")
	public String add(One data,@RequestParam("files") List<MultipartFile> files,RedirectAttributes ra) {
		final String uploadPath = "C:/excel/";
		OneFile filedata=new OneFile();
		service.add(data);
		for(MultipartFile file:files) {
			String filename = file.getOriginalFilename();
			filename.toLowerCase();
			boolean excelcheck =filename.endsWith("xls");// 어떤 파일인지 감시
			if(excelcheck==false) {
				excelcheck =filename.endsWith("xlsx");
			}
			if(file.isEmpty()||!excelcheck){//게시물 등록시 첨부파일 필수로 등록하도록
				ra.addFlashAttribute("fileError", true);
				int num=service.fileitem();
				data.setCode(num);
				service.delete(data.getCode());
				return "redirect:add";
			}
			
			String uuid =UUID.randomUUID().toString();
			System.out.println("파일이름"+filename+"and uuid"+uuid);
			data.setCode(service.fileitem());
			try {
				int rnum= 1;
				
				file.transferTo(new File(uploadPath+uuid+"_"+filename));//파일 저장
				filedata.setFilename(filename);
				filedata.setUUID(uuid);
				service.fileadd(filedata,data);
				filedata.setFilecode(service.filecode());
				List<OneExcel> ExcelList = new ArrayList<OneExcel>();

				// 엑셀의 셀데이터를 가져와서 모델에 담기
				Excel excelUtil=new Excel();
				String dataroad=uploadPath+uuid+"_"+filename;//파일 경로
				List<Map<Integer, Object>> listMap = excelUtil.getListData(file, 1, 3,dataroad); //시작행, 어디까지 있는지 열 

				for (Map<Integer, Object> map : listMap) {
					OneExcel userInfo = new OneExcel();
					
					// 각 셀의 데이터를 VO에 set한다.
					userInfo.setName(map.get(0).toString());
					userInfo.setTel(map.get(1).toString());
					userInfo.setAddress(map.get(2).toString());

					ExcelList.add(userInfo);
				}
				for (OneExcel oneUser : ExcelList){
					oneUser.setRow(rnum);
					oneUser.setFilecode(filedata.getFilecode());
					rnum++;
					System.out.print(oneUser.getRow()+" :row   ");
					System.out.print(oneUser.getFilecode()+" :code   ");
					System.out.print("이름 ->"+oneUser.getName());
					System.out.print("주소 ->"+oneUser.getAddress());
					System.out.println("번호 ->"+oneUser.getTel());
					service.insertfile(oneUser);
				}
		}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:list";
	}
	@GetMapping("/view/{code}")
	public String view(@PathVariable int code, Model model,OneFile onefile) {
		onefile=service.file(code);
		model.addAttribute("item", onefile);
		List<OneExcel> list =service.excelfind(onefile.getFilecode());
		model.addAttribute("list", list);
		return "basic1/view";
	}
	@GetMapping("/update/{code}")
	public String update(@PathVariable int code,Model model) {
		One item = service.item(code);
		model.addAttribute("item",item);
		return "basic1/update";
	}
	@PostMapping("/update/{code}")
	public String update(One data) {
		System.out.println(data.getInfo());
		System.out.println(data.getName());
		service.update(data);
		return "redirect:../list";
	}
	@GetMapping("/delete/{code}")
	public String delete(@PathVariable int code) {
		service.delete(code);
		return "redirect:../list";
	}
	@GetMapping("/excelDownload1/{filecode}")
	public void down(HttpServletResponse response,@PathVariable int filecode) throws Exception {
		OneFile list = service.onefile_fliecode(filecode);// 파일 코드로 파일에 대한 정보 불러오기
		String filename=list.getFilename();	////파일 이름
		
		try {
			String path = "C:\\excel\\"+list.getUUID()+"_"+filename; // 경로에 접근할 때 역슬래시('\') 사용
	        
	        response.setHeader("Content-Disposition", "attachment;filename=" +URLEncoder.encode(filename, StandardCharsets.UTF_8));
	        					//파일 이름 설정
	        @SuppressWarnings("resource")
            FileInputStream fileInputStream = new FileInputStream(path);
              // FileInputStream = 파일을 바이트 단위로 읽어줌
              OutputStream out = response.getOutputStream();   //파일을 바이트 단위로 다운로드 시킴
              
              int read = 0;
                   byte[] buffer = new byte[1024];
                   while ( (read = fileInputStream.read(buffer) ) != -1) { 
                      // 1024바이트씩 계속 읽으면서 outputStream에 저장, -1이 나오면 더이상 읽을 파일이 없음
                       out.write(buffer, 0, read);
                   }
           } catch (Exception e) {
               throw new Exception("download error");
       }
	}
	@GetMapping("/excelDownload2/{filecode}")
	public String exceldown(HttpServletResponse response,@PathVariable int filecode) throws IOException {
		Workbook workbook = new XSSFWorkbook();
			//엑셀 파일을 생성 --> 2007년도 껄로 가장 빠르다함.
        Sheet sheet = workbook.createSheet("게시판");// 1차 시트 생성
        int rowNo = 0;
 
        Row headerRow = sheet.createRow(rowNo++);// 첫 header 생성
        headerRow.createCell(0).setCellValue("이름");
        headerRow.createCell(1).setCellValue("전화번호");
        headerRow.createCell(2).setCellValue("주소");
 
        List<OneExcel> list = service.excelfind(filecode);//엑셀 파일내용 불러오기
        for (OneExcel board : list) {		//파일 내용 board에 담아서 내용 수 만큼 반복
            Row row = sheet.createRow(rowNo++);		//시트한줄 생성.. rowNo-> 행 수
            row.createCell(0).setCellValue(board.getName());
            row.createCell(1).setCellValue(board.getTel());
            row.createCell(2).setCellValue(board.getAddress());
        }
 
        response.setContentType("ms-vnd/excel");//엑셀파일을 처리할 콘텐츠 타입 ************content-type이란 간단히 말해 보내는 자원의 형식을 명시하기 위해 헤더에 실리는 정보
        response.setHeader("Content-Disposition", "attachment;filename=list.xls");// 파일명
 
        workbook.write(response.getOutputStream());// 응답으로 다운로드 시키는 문장
        workbook.close();//workbook 마무리
		return "redirect:.";
	}
}
