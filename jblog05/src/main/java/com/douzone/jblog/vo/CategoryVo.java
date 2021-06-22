package com.douzone.jblog.vo;

public class CategoryVo {
	private int no;
	private String name;
	private String desc;
	private String regDate;
	private String blogId;
	private Long count;
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getBlogId() {
		return blogId;
	}
	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", name=" + name + ", desc=" + desc + ", regDate=" + regDate + ", blogId="
				+ blogId + ", count=" + count + "]";
	}
	
}
