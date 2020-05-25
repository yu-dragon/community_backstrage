package com.yulong.manager.entity;

/**
 * @author 赵玉龙
 */
public class UserBase {

    private Long card_id;
    private String name;
    private String sex;
    private String default_password;
    private Integer is_first_login;
    private Integer status;
    private String extend;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIs_first_login() {
        return is_first_login;
    }

    public void setIs_first_login(Integer is_first_login) {
        this.is_first_login = is_first_login;
    }

    public Long getCard_id() {
        return card_id;
    }

    public void setCard_id(Long card_id) {
        this.card_id = card_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDefault_password() {
        return default_password;
    }

    public void setDefault_password(String default_password) {
        this.default_password = default_password;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    @Override
    public String toString() {
        return "UserBase{" +
                "card_id=" + card_id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", default_password='" + default_password + '\'' +
                ", is_first_login=" + is_first_login +
                ", status=" + status +
                ", extend='" + extend + '\'' +
                '}';
    }
}
