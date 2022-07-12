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

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
	public String down(HttpServletResponse response,@PathVariable int filecode) throws Exception {
		OneFile list = service.onefile_fliecode(filecode);
		String filename=list.getFilename();
		try {
			String path = "C:\\excel\\"+list.getUUID()+"_"+filename; // 경로에 접근할 때 역슬래시('\') 사용
	        
	        response.setHeader("Content-Disposition", "attachment;filename=" +URLEncoder.encode(list.getFilename(), StandardCharsets.UTF_8)); 
	        @SuppressWarnings("resource")
            FileInputStream fileInputStream = new FileInputStream(path);
              // FileInputStream = 파일 연결과 전송 시켜주는것
              OutputStream out = response.getOutputStream();   //다운로드 시켜주는거
              
              int read = 0;
                   byte[] buffer = new byte[1024];
                   while ( (read = fileInputStream.read(buffer) ) != -1) { 
                      // 1024바이트씩 계속 읽으면서 outputStream에 저장, -1이 나오면 더이상 읽을 파일이 없음
                       out.write(buffer, 0, read);
                   }
           } catch (Exception e) {
               throw new Exception("download error");
       }
		return "redirect:list";
	}
	@GetMapping("/excelDownload2/{filecode}")
	public String exceldown(HttpServletResponse response,@PathVariable int filecode) throws IOException {
		Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("게시판");
        int rowNo = 0;
 
        Row headerRow = sheet.createRow(rowNo++);
        headerRow.createCell(0).setCellValue("이름");
        headerRow.createCell(1).setCellValue("전화번호");
        headerRow.createCell(2).setCellValue("주소");
 
        List<OneExcel> list = service.excelfind(filecode);
        for (OneExcel board : list) {
            Row row = sheet.createRow(rowNo++);
            row.createCell(0).setCellValue(board.getName());
            row.createCell(1).setCellValue(board.getTel());
            row.createCell(2).setCellValue(board.getAddress());
        }
 
        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=boardlist.xls");
 
        workbook.write(response.getOutputStream());
        workbook.close();
		return "redirect:.";
	}
}
