package com.zilweney.dto;
import lombok.Data;

import java.io.Serializable;

@Data
public class VoicePageQueryDTO implements Serializable{
    //页码
    private int page;

    //每页记录数
    private int pageSize;

    //语音数据文件名
    private String filename;
}
