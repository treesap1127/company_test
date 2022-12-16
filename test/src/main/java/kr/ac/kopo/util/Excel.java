package kr.ac.kopo.util;

import java.io.File;
import java.io.IOException;
import java.util.*;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

public class Excel {
//	@Autowired
//	BasicService service;
	// 엑셀파일의 데이터 목록 가져오기
		public List<Map<Integer, Object>> getListData(MultipartFile file, int startRowNum, int columnLength,String dataroad) {

			List<Map<Integer, Object>> excelList = new ArrayList<Map<Integer, Object>>();//return 데이터
	//		List<OneExcel> excelList = new ArrayList<OneExcel>();//return 데이터

				Workbook workbook = null;
				try {
					workbook =WorkbookFactory.create(new File(dataroad));
	
					// 첫번째 시트
					Sheet sheet = workbook.getSheetAt(0);
	
					int rowIndex = 0;
					int columnIndex = 0;

					// 첫번째 행(0)은 컬럼 명이기 때문에 두번째 행(1) 부터 검색
					for (rowIndex = startRowNum; rowIndex < sheet.getLastRowNum() + 1; rowIndex++) {
						Row row = sheet.getRow(rowIndex);

						// 빈 행은 Skip
						if (row.getCell(0) != null && !row.getCell(0).toString().isEmpty()) {

							Map<Integer, Object> item = new HashMap<Integer, Object>();
//							OneExcel item = new OneExcel();
							int cells = columnLength;
							
							

							for (columnIndex = 0; columnIndex <= cells; columnIndex++) {
								Cell cell = row.getCell(columnIndex);
								item.put(columnIndex, getCellValue(cell));
								
							}
							excelList.add(item);
/*
							for (columnIndex = 0; columnIndex <= cells; columnIndex++) {
								Cell cell = row.getCell(columnIndex);
								
								if (columnIndex==0) {
									item.setName(getCellValue(cell));
									System.out.println("무엇을 찍어 내는지0"+getCellValue(cell));
								}
								else if (columnIndex==1) {
									item.setTel(getCellValue(cell));
									System.out.println("무엇을 찍어 내는지1"+getCellValue(cell));
								}
								else if (columnIndex==2) {
									item.setAddress(getCellValue(cell));
									System.out.println("무엇을 찍어 내는지2"+getCellValue(cell));
								}
							}
							excelList.add(item);
							System.out.println(rowIndex);
							item.setRow(rowIndex);
							service.insertfile(item);
*/
						}
					}
	
				} catch (InvalidFormatException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return excelList;
			
		}
		
	@SuppressWarnings("deprecation")
	public String getCellValue(Cell cell) {//cell 타입 분류

		String value = "";
		
		if(cell == null){
			return value;
		}

		if(cell.getCellType()==Cell.CELL_TYPE_STRING) {
			value=cell.getStringCellValue();
		}
		else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC) {
			value = (int) cell.getNumericCellValue() + "";
		}
		
		return value;
	}

	
}
