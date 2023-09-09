package com.mvc.web.VO.content;

import java.util.Date;

public class CommentVO {
	private int contentId;
	private int commentId;
	private String writeId;
	private Date regdate;
	private String content;

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getWriteId() {
		return writeId;
	}

	public void setWriteId(String writeId) {
		this.writeId = writeId;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public CommentVO(int contentId, int commentId, String writeId, Date regdate, String content) {
		this.contentId = contentId;
		this.commentId = commentId;
		this.writeId = writeId;
		this.regdate = regdate;
		this.content = content;
	}

	public CommentVO() {
	}

	@Override
	public String toString() {
		return "CommentVO [contentId=" + contentId + ", commentId=" + commentId + ", writeId=" + writeId + ", regdate="
				+ regdate + ", content=" + content + "]";
	}

}
