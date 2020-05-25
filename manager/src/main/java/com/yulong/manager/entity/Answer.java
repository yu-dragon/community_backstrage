package com.yulong.manager.entity;

import java.sql.Timestamp;

public class Answer {

    private Integer id;
    private Integer user_id;
    private String content;
    private Timestamp comm_time;
    private Timestamp update_time;
    private Long question_id;
    private Long agree;
    private Long like;
    private Long collect;
    private Integer status;
    private String extend;

    //用户昵称
    private String nickName;

    public Timestamp getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Timestamp update_time) {
        this.update_time = update_time;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getComm_time() {
        return comm_time;
    }

    public void setComm_time(Timestamp comm_time) {
        this.comm_time = comm_time;
    }

    public Long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Long question_id) {
        this.question_id = question_id;
    }

    public Long getAgree() {
        return agree;
    }

    public void setAgree(Long agree) {
        this.agree = agree;
    }

    public Long getLike() {
        return like;
    }

    public void setLike(Long like) {
        this.like = like;
    }

    public Long getCollect() {
        return collect;
    }

    public void setCollect(Long collect) {
        this.collect = collect;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", content='" + content + '\'' +
                ", comm_time=" + comm_time +
                ", update_time=" + update_time +
                ", question_id=" + question_id +
                ", agree=" + agree +
                ", like=" + like +
                ", collect=" + collect +
                ", status=" + status +
                ", extend='" + extend + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
