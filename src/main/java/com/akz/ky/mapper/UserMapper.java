package com.akz.ky.mapper;

import com.akz.ky.pojo.UserPojo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("<script>insert into user " +
            "set userLoginName = #{u.userLoginName}," +
            "userLoginPassword = #{u.userLoginPassword}," +
            "salt = #{u.salt}," +
            "userName = #{u.userName}," +
            "userSex = #{u.userSex}," +
            "userCity = #{u.userCity}," +
            "userContact = #{u.userContact}," +
            "userSignature = #{u.userSignature}," +
            "userSchoolName = #{u.userSchoolName}," +
            "userMajorName = #{u.userMajorName}," +
            "userTagetSchoolName = #{u.userTagetSchoolName}," +
            "userTagetMajorlName = #{u.userTagetMajorlName}," +
            "userIntegral = #{u.userIntegral}," +
            "userLevel = #{u.userLevel}," +
            "lastLoginDate = #{u.lastLoginDate}," +
            "createDate = #{u.createDate}," +
            "createTime = #{u.createTime}," +
            "modifyDate = #{u.modifyDate}," +
            "modifyTime = #{u.modifyTime}" +
            "</script>")
    @Options(useGeneratedKeys = true,keyProperty = "u.userNo",keyColumn="userNo")
    boolean add(@Param("u") UserPojo userPojo);

    @Select("<script>select * from user where id = #{id}</script>")
    UserPojo getById(@Param("id") int id);

    @Select("<script>select * from user where userLoginName = #{name}</script>")
    UserPojo getByName(@Param("name") String name);

    @Select("<script>select * from user where userLoginName = #{name} and  userLoginPassword = #{password}</script>")
    UserPojo checkUser(@Param("name") String name, @Param("password") String password);

    @Select("<script>select * from user</script>")
    List<UserPojo> list();

    @Select("<script>select * from user where 1=1" +
            "<if test='u.schoolname !=null'> and schoolname = #{u.schoolname}</if>" +
            "<if test='u.major !=null'> and name = #{u.major}</if>" +
            "<if test='u.aimSchool !=null'> and name = #{u.aimSchool}</if>" +
            "<if test='u.aimMajor !=null'> and name = #{u.aimMajor}</if>" +
            "<if test='u.userType !=null'> and name = #{u.userType}</if>" +
            "<if test='u.activity !=null'> and name = #{u.activity}</if>" +
            "<if test='u.badge !=null'> and name = #{u.badge}</if>" +
            "</script>")
    List<UserPojo> listByUser(@Param("u") UserPojo userPojo);

    @Update("<script>update user set" +
            "<if test = 'u.userLoginName!=null'> userLoginName = #{u.userLoginName}</if>" +
            "<if test = 'u.userLoginPassword!=null'> userLoginPassword = #{u.userLoginPassword}</if>" +
            "<if test = 'u.salt!=null'> salt = #{u.salt}</if>" +
            "<if test = 'u.realName!=null'> realName = #{u.realName}</if>" +
            "<if test = 'u.sex!=null'> sex = #{u.sex}</if>" +
            "<if test = 'u.mobile!=null'> mobile = #{u.mobile}</if>" +
            "<if test = 'u.schoolname!=null'> schoolname = #{u.schoolname}</if>" +
            "<if test = 'u.grade!=null'> grade = #{u.grade}</if>" +
            "<if test = 'u.major!=null'> major = #{u.major}</if>" +
            "<if test = 'u.aimSchool!=null'> aimSchool = #{u.aimSchool}</if>" +
            "<if test = 'u.aimMajor!=null'> aimMajor = #{u.aimMajor}</if>" +
            "<if test = 'u.userType!=null'> userType = #{u.userType}</if>" +
            "<if test = 'u.userPemission!=null'> userPemission = #{u.userPemission}</if>" +
            "<if test = 'u.signature!=null'> signature = #{u.signature}</if>" +
            "<if test = 'u.activity!=null'> activity = #{u.activity}</if>" +
            "<if test = 'u.badge!=null'> badge = #{u.badge}</if>" +
            " where id = #{u.id}</script>")
    boolean update(@Param("u") UserPojo userPojo);

    @Update("update user set lastLoginDate = #{u.lastLoginDate},modifyDate = #{u.modifyDate},modifyTime=#{u.modifyTime} where userNo = #{u.userNo}")
    boolean updateLoginTime(@Param("u") UserPojo userPojo);

    @Delete("<script>delete from user where id = #{id} </script>")
    boolean delete(@Param("id") int id);

    @Select("select * from user where  userLoginName = #{name}")
    UserPojo isExists(@Param("name")String name);
}
