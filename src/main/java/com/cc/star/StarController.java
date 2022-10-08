package com.cc.star;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StarController {

	@Autowired
	private StarService starService;
	
	//根据CardId查找
	@RequestMapping("/getByCardId")
	public List<Star> getByCardId(int cardid) {
		System.out.println("开始获取...");
		return starService.findByCardId(cardid);
	}
	
	//新增一件
	@RequestMapping("/createStar")
	public void createStar(String name,int cardid) {
		starService.createStar(name, cardid);
	}
	
	//根据name删除
	@RequestMapping("/deleteByName")
	public void deleteByName(String name) {
		starService.deleteByName(name);
	}
	
	//根据name修改
	@RequestMapping("/updateByName")
	public void updateByName(String nameold,String name,int cardid) {
		starService.updateByName(nameold,name,cardid);
	}
	
	//根据日期区间查询
	@RequestMapping("/findByDate")
	public List<Star> findByDate(Date startDate, Date endDate){
		System.out.println("开始获取...");
		return starService.findByDate(startDate, endDate);
	}
	
	//返回通用类型响应体
	@RequestMapping("/anyObject")
	public ResponseEntity<String> anyObject(){
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Custom-Header", "foo");

	    return new ResponseEntity<>("返回通用类型响应体", headers, HttpStatus.OK);
	}
	
	@RequestMapping("/anyObject2")
	public String anyObject2(){
		return "返回通用类型响应体2";
	}
}
