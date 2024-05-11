package com.zilweney.controller.user;

import com.zilweney.dto.VoiceDTO;
import com.zilweney.dto.VoicePageQueryDTO;
import com.zilweney.entity.Voice;
import com.zilweney.result.PageResult;
import com.zilweney.result.Result;
import com.zilweney.service.VoiceService;
import com.zilweney.vo.ChartVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/user/voice")
@Api(tags = "语音数据相关接口")
@Slf4j
public class VoiceController {

    @Autowired
    private VoiceService voiceService;

    /**
     * 上传并处理语音文件
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result handleFileUpload(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            // 保存文件到服务器的某个目录
            //TODO 如果没有文件夹，则创建文件夹
            //TODO 将文件名更改为UUID
            String destPath = "D:/桌面/test/" + file.getOriginalFilename();
            file.transferTo(new File(destPath));
            log.info("检测到文件上传：{}",file.getOriginalFilename());

            //新增语音数据
            VoiceDTO voiceDTO = VoiceDTO.builder().filename(file.getOriginalFilename()).build();//获取文件名
            voiceService.save(voiceDTO);
            return Result.success();
        } catch (Exception e) {
            message =  file.getOriginalFilename() + "上传失败！"  + " => " + e.getMessage();
            log.info(message);
            return Result.error(message);
        }

    }

    /**
     * 语音数据分页查询
     * @param voicePageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("语音数据分页查询")
    public Result<PageResult> page(VoicePageQueryDTO voicePageQueryDTO){//因为是Query类型，所以不是JSON格式，不用加上@RequestBody注解
        log.info("语音数据分页查询,参数为:{}",voicePageQueryDTO);
        PageResult pageResult = voiceService.pageQuery(voicePageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 统计图表数据查询
     * @return
     */
    @GetMapping("/display")
    @ApiOperation("统计图表数据查询")
    public Result<ChartVO> display(){
        log.info("统计图表数据查询");
        ChartVO chartVO = voiceService.displayQuery();
        return Result.success(chartVO);
    }
}
