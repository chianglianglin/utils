package com.example.demo;


import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class UtilsTest {
	class SimpleBaseEntity implements Serializable {

		private static final long serialVersionUID = 1L;

		/**
		 * 创建时间
		 */
		// '資料創建時間'

		private Date createTime;

		/**
		 * 创建者
		 */
		// '資料創建人員'
		private String createBy;

	}
	public class TaskJobDetail extends SimpleBaseEntity {
		/**
		 *
		 */
		private static final long serialVersionUID = 1L;
		
		
		private Integer id;


		private String city;


		private Integer statusCode;

		private Long size;

		private Date connectTime;

		private Date doneTime;

		public TaskJobDetail() {
		}

		public TaskJobDetail(Integer id, String city, Integer statusCode, Long size, Date connectTime, Date doneTime) {
			this.id = id;
			this.city = city;
			this.statusCode = statusCode;
			this.size = size;
			this.connectTime = connectTime;
			this.doneTime = doneTime;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public Integer getStatusCode() {
			return statusCode;
		}

		public void setStatusCode(Integer statusCode) {
			this.statusCode = statusCode;
		}

		public Long getSize() {
			return size;
		}

		public void setSize(Long size) {
			this.size = size;
		}

		public Date getConnectTime() {
			return connectTime;
		}

		public void setConnectTime(Date connectTime) {
			this.connectTime = connectTime;
		}

		public Date getDoneTime() {
			return doneTime;
		}

		public void setDoneTime(Date doneTime) {
			this.doneTime = doneTime;
		}
	}

	public class TaskJobDetailMap extends SimpleBaseEntity {
		/**
		 *
		 */
		private static final long serialVersionUID = 1L;


		private Integer id;


		private String city;


		private Map statusMap;

		private Long size;

		private Date connectTime;

		private Date doneTime;

	}

	@Test
	public void gsonTest(){

		TaskJobDetail sefs = new TaskJobDetail(1, "sefs", 1, 1l, new Date(), new Date());
		System.out.println("GsonUtils.toJson(sefs,true) = " + GsonUtils.toJson(sefs, false));
		TaskJobDetail wfwe = GsonUtils.fromJson(GsonUtils.toJson(sefs, false),TaskJobDetail.class);
		System.out.println("sfwewfwfw");
	}

	@Test
	public void stringTest(){
		System.out.println("StringUtils.format(\"this is {} for {}\", \"a\", \"b\") = " + StringUtils.format("this is {} for {}", "a", "b"));
	}

	@Test
	public void fileTest(){
		System.out.println("TextUtil.getRecordFilePath() = " + TextUtil.getRecordFilePath());
		TextUtil.read("ip集合.txt");
		TextUtil.write("test01","wefwefwfwfwfewfw\nwewfwfwfwef\n22222222\n");
	}

	@Test
	public void digitTest(){
		System.out.println("Integer.toBinaryString(192) = " + Integer.valueOf(Integer.toBinaryString(192)));
		Integer integer = Integer.valueOf(Integer.toBinaryString(192));
		System.out.println("integer = " + integer);
		int i = integer&11111111;
		int i2 = 192&255;
		System.out.println("i2 = " + i2);
		System.out.println("i = " + i);
	}
}
