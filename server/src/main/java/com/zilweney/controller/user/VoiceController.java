package com.zilweney.controller.user;

import com.zilweney.result.Result;
import com.zilweney.service.VoiceService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/user/voice")
@Api(tags = "语音数据相关接口")
@Slf4j
public class VoiceController {

    @Autowired
    private VoiceService voiceService;

    @PostMapping("/upload")
    public Result handleFileUpload(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            // 保存文件到服务器的某个目录
            String destPath = "../../UserUpload" + file.getOriginalFilename();
            file.transferTo(new File(destPath));
            message = file.getOriginalFilename() + "上传成功！";
            log.info("检测到文件上传：{}",message);
            return Result.success();
        } catch (Exception e) {
            message =  file.getOriginalFilename() + "上传失败！"  + " => " + e.getMessage();
            log.info("检测到文件上传：{}",message);
            return Result.error(message);
        }

    }

}
