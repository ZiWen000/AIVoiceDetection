package com.zilweney.service;


import com.zilweney.dto.UserDTO;
import com.zilweney.dto.UserLoginDTO;
import com.zilweney.entity.User;
import com.zilweney.result.PageResult;
import com.zilweney.vo.UserLoginVO;

public interface UserService {

    /**
     * 用户登录
     * @param userLoginDTO
     * @return
     */
    User login(UserLoginDTO userLoginDTO);

    /**
     * 新增用户
     * @param userDTO
     */
    void save(UserDTO userDTO);

//    /**
//     * 员工分页查询
//     * @param employeePageQueryDTO
//     * @return
//     */
//    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);


    /**
     * 根据token查询
     * @return
     */
    User getByToken();

    /**
     * 编辑用户信息
     * @param userDTO
     */
    void update(UserDTO userDTO);
}
