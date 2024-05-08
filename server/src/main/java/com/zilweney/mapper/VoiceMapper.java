package com.zilweney.mapper;

import com.github.pagehelper.Page;
import com.zilweney.dto.VoicePageQueryDTO;
import com.zilweney.entity.Voice;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VoiceMapper {

    /**
     * 新增语音数据
     * @param voice
     */
    @Insert("insert into voice (filename, create_time,  user_id , algorithm) " +
            "values (#{filename},#{createTime},#{userId},#{algorithm})")
    void insert(Voice voice);

    /**
     * 语音数据分页查询
     * @param voicePageQueryDTO
     * @return
     */
    Page<Voice> pageQuery(VoicePageQueryDTO voicePageQueryDTO);
}
