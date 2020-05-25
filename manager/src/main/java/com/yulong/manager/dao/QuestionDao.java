package com.yulong.manager.dao;

import com.yulong.manager.entity.Question;
import com.yulong.manager.entity.UserFollowed;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

public interface QuestionDao {

    /**
     * 发表问题
     * @param que
     * @return true/false
     */
    @Insert("insert into question (title, user_id, description, comm_time) values" +
            "(#{que.title}, #{que.user_id}, #{que.description}, #{que.comm_time})")
    public Integer postQuestion(@Param("que") Question que);


    @Select("select * from question where rank <> -1 and status = 1")
    public List<Question> getHotQuesList();

    @Select("select id,title,description,user_id from question where id = #{id} and status = 1")
    public Question getQuestionById(@Param("id") Long id);

    @Select("select * from user_followed where user_id = #{userId} and question_id =#{qId}")
    public Question getQuestionByQidandUserId(@Param("qId") String qId, @Param("userId") String userId);

    @Update("update question set followed = (followed + #{count}) where id = #{qId}")
    public Integer updateFollowed(@Param("qId") String qId, @Param("count") Integer count);

    @Insert("insert into user_followed (user_id, question_id, first_followed, status) values" +
            "(#{userId}, #{qId}, #{firstTime}, #{status})")
    public Integer insertUserFollowed(@Param("userId") String userId, @Param("qId") String qId,
                              @Param("status") String status, @Param("firstTime") Timestamp firstTime);

    @Update("update user_followed set followed_time=#{follTime}, status=#{status} where user_id = #{userId} and question_id = #{qId}")
    public Integer updateUserFollowed(@Param("userId") String userId, @Param("qId") String qId,
                                           @Param("status") String status, @Param("follTime") Timestamp follTime);

    @Select("select * from user_followed where user_id = #{userId} and question_id = #{qId}")
    public UserFollowed getIsFollowed(@Param("qId") String qId, @Param("userId") String userId);


}
