package com.zilweney.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "统计图表使用的数据格式")
public class ChartVO {

    private Integer total;
    //为AI合成的音频数量
    private Integer isAI;
    private Integer isCheat;
    //异常音频文件数量=isAI+isCheat
    private Integer isAbnormal;
    //过去七日每日上传文件数量
    private List<Integer> dateTotal;
}
