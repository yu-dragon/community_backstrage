package com.yulong.manager.entity;

public class User {
    private Integer id;
    private String username;
    private String password;
    private String card_id;
    private String phone;
    private Integer age;
    private String sex;
    private String img_head;
    private String address;
    private Integer type;
    private Integer health_status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getImg_head() {
        return img_head;
    }

    public void setImg_head(String img_head) {
        this.img_head = img_head;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getHealth_status() {
        return health_status;
    }

    public void setHealth_status(Integer health_status) {
        this.health_status = health_status;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", card_id='" + card_id + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", img_head='" + img_head + '\'' +
                ", address='" + address + '\'' +
                ", type=" + type +
                ", health_status=" + health_status +
                '}';
    }
}
