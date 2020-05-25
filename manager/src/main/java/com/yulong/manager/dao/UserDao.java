package com.yulong.manager.dao;

import com.yulong.manager.entity.AlienUser;
import com.yulong.manager.entity.User;
import com.yulong.manager.entity.UserBase;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * @author 赵玉龙
 */
@Repository
public interface UserDao {

    @Select("select * from user where card_id = #{cardId} and password = #{password}")
    public User getUser(@Param("cardId") String cardId, @Param("password") String password);

    @Select("select * from user where name = #{name}")
    public List<User> getUserByName(@Param("name") String name);

    @Select("select * from user where id = #{id}")
    public User getUserById(@Param("id") Integer id);

    @Select("select * from user where card_id = #{cardId}")
    public User getUserByCardId(@Param("cardId") String cardId);

    @Select("select * from alien_base_info where card_id = #{cardId} and alienName = #{alienName}")
    public AlienUser checkAlienIsExist(@Param("cardId") String cardId, @Param("alienName") String alienName);

    @Select("select * from community_user_info where card_id = #{cardId}")
    public UserBase getUserBase(String cardId);

    @Select("select * from user where card_id = #{card_id} and password = #{password}")
    public User login(@Param("card_id") String card_id, @Param("password") String password);

    @Update("update community_user_info set is_first_login = #{isFirstLogin} where card_id = #{cardId}")
    public Integer updateIsFirstLogin(@Param("cardId") String cardId, @Param("isFirstLogin") Integer isFirstLogin);

    @Insert("insert into user (card_id, name, nick_name, sex, type, password, register_time) values " +
            "(#{cardId}, #{name}, #{nickName}, #{sex}, #{type}, #{password}, #{regTime})")
    public Integer insertUser(@Param("cardId") String cardId,
                              @Param("name") String name,
                              @Param("nickName") String nickName,
                              @Param("sex") String sex,
                              @Param("type") Integer type,
                              @Param("password") String password,
                              @Param("regTime")Date regTime);

}
