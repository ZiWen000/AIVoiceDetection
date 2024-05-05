package com.zilweney.service;

import com.zilweney.dto.VoiceDTO;

public interface VoiceService {

    /**
     * 新增语音数据
     * @param voiceDTO
     */
    void save(VoiceDTO voiceDTO);
}
