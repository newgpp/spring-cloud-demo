package dao;

import java.util.List;
import model.MicroConfig;
import model.MicroConfigExample;

public interface MicroConfigMapper {
    int insert(MicroConfig record);

    int insertSelective(MicroConfig record);

    List<MicroConfig> selectByExample(MicroConfigExample example);

    MicroConfig selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MicroConfig record);

    int updateByPrimaryKey(MicroConfig record);
}