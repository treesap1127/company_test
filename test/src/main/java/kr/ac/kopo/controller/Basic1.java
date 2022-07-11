package kr.ac.kopo.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.kopo.model.One;
import kr.ac.kopo.model.OneFile;
import kr.ac.kopo.service.BasicService;
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
	public String add(One data,@RequestParam("files") List<MultipartFile> files) {
		final String uploadPath = "C:/photo/";
		service.add(data);
		OneFile filedata=new OneFile();
		for(MultipartFile file:files) {
			String filename = file.getOriginalFilename();
			String uuid =UUID.randomUUID().toString();
			System.out.println("파일이름"+filename+"and   uuid"+uuid);
			int num=service.fileitem();
			data.setCode(num);
			try {
				file.transferTo(new File(uploadPath+uuid+"_"+filename));
				filedata.setFilename(filename);
				filedata.setUUID(uuid);
				service.fileadd(filedata,data);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		
		return "redirect:list";
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
}
