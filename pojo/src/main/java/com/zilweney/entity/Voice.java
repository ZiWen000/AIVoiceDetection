package com.zilweney.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Voice implements Serializable {

//    private static final long serialVersionUID = 1L;

    private Long id;

//    //微信用户唯一标识
//    private String openid;

    //文件名
    private String filename;

    //文本内容
    private String content;

    //为ai合成的概率
    private Double isAi;

    //为诈骗文本的概率
    private Double isCheat;

    //创建时间
    private LocalDateTime createTime;
}
