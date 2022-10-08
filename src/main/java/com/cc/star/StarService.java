package com.cc.star;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StarService {
	
	@Autowired
	private StarMapper starMapper;
	
	//根据CardId查找
	List<Star> findByCardId(int cardid){
		return starMapper.findByCardId(cardid);
	}
	
	//新增一件
	void createStar(String name,int cardid) {
		Star star = new Star();
		star.setName(name);
		star.setCardid(cardid);
		//获取当前日期
		Date date = new Date();
		star.setCreateDate(date);
		starMapper.save(star);
	}
	
	//根据name删除
	void deleteByName(String name) {
		starMapper.deleteByName(name);
	}
	
	//根据name修改
	void updateByName(String nameold,String name,int cardid) {
		starMapper.updateByName(nameold,name,cardid);
	}
	
	//根据日期区间查询
	List<Star> findByDate(Date startDate, Date endDate){
		return starMapper.findByDate(startDate, endDate);
	}
}
