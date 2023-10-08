package dao;

import model.MicroConfig;

public interface MicroConfigMapper {
    int insert(MicroConfig record);

    int insertSelective(MicroConfig record);

    MicroConfig selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MicroConfig record);

    int updateByPrimaryKey(MicroConfig record);
}