package com.yulong.manager.dao;

import com.yulong.manager.entity.Answer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface AnswerDao {

    @Select("select * from answer where question_id = #{questionId}")
    public List<Answer> getAnswerList(@Param("questionId") Long questionId);

    @Insert("insert into answer (question_id, user_id, content, comm_time) values (#{qId}, #{userId}, #{content}, #{commTime})")
    public Integer postAnswer(@Param("qId") String qId, @Param("userId") String userId,
                              @Param("content") String content, @Param("commTime") Timestamp commTime);

    @Update("update question set answer_num = answer_num + 1 where user_id = #{userId} and question_id = #{qId}")
    public Integer updateQuestionAnswerNum(@Param("userId") String userId, @Param("qId") String qId);

    @Update("update answer set content=#{content}, update_time=#{updateTime} where question_id=#{qId} and user_id=#{userId} and status = 1")
    public Integer modifyAnswer(@Param("qId") String qId, @Param("userId") String userId,
                                @Param("content") String content, @Param("updateTime") Timestamp updateTime);

    @Select("select * from answer where user_id = #{id} and question_id = #{qId} and status = 1")
    public Answer isAnsweredByUserId(@Param("id") String id, @Param("qId") String qId);

    @Select("select * from answer where id = #{id} and status = 1")
    public Answer getSingleAnswer(@Param("id") String id);
}
