package com.cc.star;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface StarMapper extends JpaRepository<Star,Integer>{
	
	//根据CardId查找
	@Query(value="select * from star s where s.cardid = :cardid",nativeQuery = true)
	List<Star> findByCardId(int cardid);

	//新增一件
	//...
	
	//根据name删除
	@Modifying
	@Transactional
	@Query(value="delete from star s where s.name = :name",nativeQuery = true)
	void deleteByName(String name);
	
	//根据name更新
	@Modifying
	@Transactional
	@Query(value="update star s set s.name = :name,s.cardid = :cardid where s.name = :nameold",nativeQuery = true)
	void updateByName(String nameold, String name, int cardid);
	
	//根据日期区间查询
	@Query(value="select * from star s where s.create_date between :startDate and :endDate",nativeQuery = true)
	List<Star> findByDate(Date startDate, Date endDate);
}
