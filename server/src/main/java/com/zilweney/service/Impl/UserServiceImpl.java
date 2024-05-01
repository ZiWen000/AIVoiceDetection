package com.zilweney.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zilweney.constant.MessageConstant;
import com.zilweney.constant.PasswordConstant;
import com.zilweney.constant.StatusConstant;
import com.zilweney.dto.UserDTO;
import com.zilweney.dto.UserLoginDTO;
import com.zilweney.entity.User;
import com.zilweney.exception.AccountLockedException;
import com.zilweney.exception.AccountNotFoundException;
import com.zilweney.exception.PasswordErrorException;
import com.zilweney.mapper.UserMapper;
import com.zilweney.result.PageResult;
import com.zilweney.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录
     *
     * @param userLoginDTO
     * @return
     */
    public User login(UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        User employee = userMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (employee == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        //对前端明文密码进行md5加密，然后再进行比对
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(employee.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

//        if (employee.getStatus() == StatusConstant.DISABLE) {
//            //账号被锁定
//            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
//        }

        //3、返回实体对象
        return employee;
    }

    /**
     * 新增用户
     * @param userDTO
     */
    public void save(UserDTO userDTO) {
        System.out.println("当前进程id："+Thread.currentThread().getId());
        User user = new User();
        //对象属性拷贝
        BeanUtils.copyProperties(userDTO,user);
        //设置密码,默认密码123456
        user.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));
        //记录当前记录的创建时间和修改时间
//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());
        //设置当前记录创建人id和修改人id

//        employee.setCreateUser(BaseContext.getCurrentId());
//        employee.setUpdateUser(BaseContext.getCurrentId());
        userMapper.insert(user);
    }

//    /**
//     * 分页查询
//     * @param employeePageQueryDTO
//     * @return
//     */
//    public PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO) {
//        //select * from employee limit 0,10
//        //开始分页查询
//        PageHelper.startPage(employeePageQueryDTO.getPage(),employeePageQueryDTO.getPageSize());
//        Page<Employee> page = employeeMapper.pageQuery(employeePageQueryDTO);
//
//        long total = page.getTotal();
//        List<Employee> records = page.getResult();
//        return new PageResult(total,records);
//    }

//    /**
//     * 启用禁用用户账号
//     * @param status
//     * @param id
//     */
//    public void startOrStop(Integer status, Long id) {
//        //update employee set status = ? where id = ?
//        Employee employee = Employee.builder().status(status).id(id).build();
//        employeeMapper.update(employee);
//    }

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    public User getById(Long id) {
        User user = userMapper.getById(id);
        user.setPassword("****");//不让前端查看密码,增加安全性
        return user;
    }

    /**
     * 编辑用户信息
     * @param userDTO
     */
    public void update(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);

//        employee.setUpdateTime(LocalDateTime.now());
//        employee.setUpdateUser(BaseContext.getCurrentId());

        userMapper.update(user);
    }

}
