package com.zilweney.mapper;

import com.github.pagehelper.Page;
import com.zilweney.annotation.AutoFill;
import com.zilweney.enumeration.OperationType;
import com.zilweney.annotation.AutoFill;
import com.zilweney.entity.User;
import com.zilweney.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    @Select("select * from user where username = #{username}")
    User getByUsername(String username);

    /**
     * 插入用户数据
     * @param user
     */
    @AutoFill(value = OperationType.INSERT)
    @Insert("insert into user (name, username, sex,  password, create_time, update_time) " +
    "values "+
    "(#{name},#{username},#{sex},#{password},#{createTime},#{updateTime})")
    void insert(User user);

//    /**
//     * 员工分页查询
//     *
//     * @return
//     */
//    Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 根据主键动态修改属性
     * @param user
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(User user);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Select("select * from user where id = #{id}")
    User getById(Long id);
}
