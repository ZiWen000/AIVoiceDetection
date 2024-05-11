package com.zilweney.mapper;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.zilweney.dto.VoicePageQueryDTO;
import com.zilweney.entity.Voice;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    @Select("select count(*) from voice")
    Integer getTotal();

    @Select("select count(*) from voice where is_ai >= 50 ")
    Integer getIsAI();

    @Select("select count(*) from voice where is_cheat >= 50 ")
    Integer getIsCheat();

    /**
     * 获取过去七日上传文件的数量
     * @return
     */
    @Select("SELECT \n" +
            "    IFNULL(COUNT(voice.create_time), 0) AS daily_count\n" +
            "FROM \n" +
            "    (\n" +
            "        SELECT CURDATE() - INTERVAL a DAY AS date\n" +
            "        FROM (\n" +
            "            SELECT 0 AS a UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6\n" +
            "        ) AS a\n" +
            "    ) AS DateList\n" +
            "LEFT JOIN\n" +
            "    voice ON DATE(voice.create_time) = DateList.date\n" +
            "GROUP BY \n" +
            "    DateList.date\n" +
            "ORDER BY \n" +
            "    DateList.date;")
    List<Integer> getDateTotal();

    @Select("select count(*) from voice where is_ai >= 50 or is_cheat >= 50")
    Integer getIsAbnormal();
}
