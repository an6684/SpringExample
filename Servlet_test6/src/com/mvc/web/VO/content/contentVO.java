package com.mvc.web.VO.content;

import java.util.Date;

public class contentVO {
	int id;
	String title;
	String content;
	Date regdate;
	String useflag;
	String writeid;
	int hit;
	String boardid;
	String filepath;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getUseflag() {
		return useflag;
	}
	public void setUseflag(String useflag) {
		this.useflag = useflag;
	}
	public String getWriteid() {
		return writeid;
	}
	public void setWriteid(String writeid) {
		this.writeid = writeid;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getBoardid() {
		return boardid;
	}
	public void setBoardid(String boardid) {
		this.boardid = boardid;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public contentVO(int id, String title, String content, Date regdate, String useflag, String writeid, int hit,
			String boardid, String filepath) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.useflag = useflag;
		this.writeid = writeid;
		this.hit = hit;
		this.boardid = boardid;
		this.filepath = filepath;
	}
	
	public contentVO() {
		super();
	}
	@Override
	public String toString() {
		return "contentVO [id=" + id + ", title=" + title + ", content=" + content + ", regdate=" + regdate
				+ ", useflag=" + useflag + ", writeid=" + writeid + ", hit=" + hit + ", boardid=" + boardid
				+ ", filepath=" + filepath + "]";
	}
	
	
}
