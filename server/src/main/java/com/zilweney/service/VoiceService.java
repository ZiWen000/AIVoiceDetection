package com.zilweney.service;

import com.zilweney.dto.VoiceDTO;
import com.zilweney.dto.VoicePageQueryDTO;
import com.zilweney.result.PageResult;

public interface VoiceService {

    /**
     * 新增语音数据
     * @param voiceDTO
     */
    void save(VoiceDTO voiceDTO);

    /**
     * 语音数据分页查询
     * @param voicePageQueryDTO
     * @return
     */
    PageResult pageQuery(VoicePageQueryDTO voicePageQueryDTO);
}
