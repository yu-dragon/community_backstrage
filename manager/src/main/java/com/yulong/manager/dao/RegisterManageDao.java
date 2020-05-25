package com.yulong.manager.dao;

import com.yulong.manager.entity.AlienUser;
import com.yulong.manager.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;

@Repository
public interface RegisterManageDao {

    @Select("select * from alien_base_info where card_id = #{cardId} and alienName = #{name}")
    public AlienUser checkIsHasAlien(@Param("cardId") String cardId, @Param("name") String name);

    @Insert("insert into user (card_id, name, nick_name, sex, type, password, register_time, health_status, address, phone, age) values " +
            "(#{cardId}, #{name}, #{nickName}, #{sex}, #{isAlien}, #{pwd}, #{date}, #{hea}, #{addr}, #{pho}, #{age})")
    public Integer registerAlien(@Param("name") String name, @Param("nickName") String nickName, @Param("cardId") String cardId,
                              @Param("pwd") String pwd, @Param("pho") String pho,
                              @Param("age") String age, @Param("sex") String sex,
                              @Param("isAlien") String isAlien, @Param("date") Timestamp date,
                              @Param("hea") String hea, @Param("addr") String addr);

}
