package com.zilweney.mapper;

import com.zilweney.entity.Voice;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VoiceMapper {

    /**
     * 新增语音数据
     * @param voice
     */
    @Insert("")
    void insert(Voice voice);
}
