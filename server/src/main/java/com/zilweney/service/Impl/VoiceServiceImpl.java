package com.zilweney.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zilweney.context.BaseContext;
import com.zilweney.dto.VoiceDTO;
import com.zilweney.dto.VoicePageQueryDTO;
import com.zilweney.entity.Voice;
import com.zilweney.mapper.VoiceMapper;
import com.zilweney.result.PageResult;
import com.zilweney.service.VoiceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VoiceServiceImpl implements VoiceService {

    @Autowired
    private VoiceMapper voiceMapper;

    /**
     * 新增语音数据
     * @param voiceDTO
     */
    public void save(VoiceDTO voiceDTO) {

        voiceDTO.setUserId(BaseContext.getCurrentId());//当前用户id
        voiceDTO.setCreateTime(LocalDateTime.now());

        Voice voice = new Voice();
        BeanUtils.copyProperties(voiceDTO,voice);

        voiceMapper.insert(voice);
    }


    /**
     * 语音数据分页查询
     * @param voicePageQueryDTO
     * @return
     */
    public PageResult pageQuery(VoicePageQueryDTO voicePageQueryDTO) {
        //select * from voice limit 0,10
        //开始分页查询
        PageHelper.startPage(voicePageQueryDTO.getPage(),voicePageQueryDTO.getPageSize());
        Page<Voice> page = voiceMapper.pageQuery(voicePageQueryDTO);

        long total = page.getTotal();
        List<Voice> records = page.getResult();
        return new PageResult(total,records);

    }

}
