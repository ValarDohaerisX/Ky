package com.akz.ky.mapper;

import com.akz.ky.pojo.ManagerPojo;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface ManagerMapper {

    @Insert("<script>insert into manager " +
            "set name = #{m.name}," +
            "password = #{m.password}," +
            "manaType = #{m.manaType}," +
            "manaPemission = #{m.manaPemission}," +
            "appFlag = #{m.appFlag}" +
            "</script>")
//    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn="m.id")
    boolean registerOne(@Param("m") ManagerPojo managerPojo);

//    @Update("<script>update manager set </script>")
//    boolean registerTwo(@Param("m")ManagerPojo managerPojo);

    @Select("<script>select * from manager where name = #{name} and password = #{password}</script>")
    ManagerPojo checkManager(@Param("name") String name, @Param("password") String password);

    @Select("<script>select * from manager where id = #{id} </script>")
    ManagerPojo getById(@Param("id") int id);



    @Update("<script>update manager set " +
            "<if test='m.name!=null'>name = #{m.name}, </if>" +
            "<if test='m.password!=null'>password = #{m.password}, </if>" +
            "<if test='m.manaType!=null'>manaType = #{m.manaType}, </if>" +
            "<if test='m.manaPemission!=null'>manaPemission = #{m.manaPemission},</if> " +
            "<if test='m.appFlag!=null'>appFlag = #{m.appFlag}</if>" +
            "where id = #{m.id}" +
            "</script>")
    boolean update(@Param("m") ManagerPojo managerPojo);

    @Select("<script>select * from manager where name = #{name} </script>")
    ManagerPojo isExist(@Param("name") String name);

    @Select("select * from manager")
    List<ManagerPojo> allManaList();

    @Select("<script>select * from manager where appflag = 0</script>")
    List<ManagerPojo> confirmToRegList();

    @Delete("<script>delete from manager where id = #{id}</script>")
    boolean deleteManaInfo(@Param("id") int id);

    @Select("<script>select * from manager where name like '%${name}%'</script>")
    List<ManagerPojo> getManaByNameLike(@Param("name") String name);
}
