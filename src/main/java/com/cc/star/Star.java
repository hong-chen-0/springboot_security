package com.cc.star;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name = "star")  // 该注解声明一个实体类，与数据库中的表对应
@Table(uniqueConstraints = {
	@UniqueConstraint(name = "unique_name", columnNames = "name"),
	@UniqueConstraint(name = "unique_cardid", columnNames = "cardid")
})  //唯一约束
public class Star {
	
    @Id   // 表明id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   //  自动生成
    private int id ;
    
    private String name ;
    
    private int cardid ;
    
    private java.util.Date createDate ;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCardid() {
		return cardid;
	}
	public void setCardid(int cardid) {
		this.cardid = cardid;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date date) {
		this.createDate = date;
	}
}
