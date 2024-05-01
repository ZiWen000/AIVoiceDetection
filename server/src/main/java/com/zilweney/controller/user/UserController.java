package com.zilweney.controller.user;

import com.zilweney.constant.JwtClaimsConstant;

import com.zilweney.dto.UserDTO;
import com.zilweney.dto.UserLoginDTO;
import com.zilweney.entity.User;
import com.zilweney.properties.JwtProperties;
import com.zilweney.result.PageResult;
import com.zilweney.result.Result;

import com.zilweney.service.UserService;
import com.zilweney.utils.JwtUtil;
import com.zilweney.vo.UserLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户管理
 */
@RestController
@RequestMapping("/user/user")
@Slf4j
@Api(tags = "用户相关接口")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param userLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("用户登录：{}", userLoginDTO);

        User user = userService.login(userLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, user.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .userName(user.getUsername())
                .name(user.getName())
                .token(token)
                .build();

        return Result.success(userLoginVO);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation("用户退出")
    public Result<String> logout() {
        return Result.success();
    }

    /**
     *
     * @param userDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增用户")
    public Result save(@RequestBody UserDTO userDTO){
        System.out.println("当前进程id："+Thread.currentThread().getId());
        log.info("新增用户:{}",userDTO);
        userService.save(userDTO);
        return Result.success();
    }

//    /**
//     * 用户分页查询
//     * @param employeePageQueryDTO
//     * @return
//     */
//    @GetMapping("/page")
//    @ApiOperation("用户分页查询")
//    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO){//因为是Query类型，所以不是JSON格式，不用加上@RequestBody注解
//        log.info("用户分页查询,参数为:{}",employeePageQueryDTO);
//        PageResult pageResult = employeeService.pageQuery(employeePageQueryDTO);
//        return Result.success(pageResult);
//    }


    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询用户信息")
    public Result<User> getById(@PathVariable Long id){
        User user = userService.getById(id);
        return Result.success(user);
    }

//    /**
//     * 编辑用户信息
//     * @param employeeDTO
//     * @return
//     */
//    @PutMapping
//    @ApiOperation("编辑用户信息")
//    public Result update(@RequestBody EmployeeDTO employeeDTO){
//        log.info("编辑用户信息:{}",employeeDTO);
//        employeeService.update(employeeDTO);
//        return Result.success();
//    }
}
