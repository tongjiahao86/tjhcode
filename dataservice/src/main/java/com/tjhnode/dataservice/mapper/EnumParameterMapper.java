package com.tjhnode.dataservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tjhnode.dataservice.model.entities.Enumparameters;
import com.tjhnode.dataservice.model.vo.QueryPaginationModel;
import com.tjhnode.dataservice.model.vo.ResultPaginationModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 参数注册接口
 * </p>
 *
 * @author tjh
 * @since 2019-11-30
 */
@Mapper
@Component
public interface EnumParameterMapper extends BaseMapper<Enumparameters> {

    /**
     * 参数注册列表
     * @param query 分页、条件查询
     * @return
     */
    ResultPaginationModel<Enumparameters> getEnumParameterList(QueryPaginationModel query);

    /**
     * 新增参数
     * @param model
     * @return
     */
    int insertEnumParameter(Enumparameters model);

    /**
     * 修改参数
     * @param model
     * @return
     */
    int updateEnumParameter(Enumparameters model);

    /**
     * 删除
     * @param KeyId 主键
     * @return
     */
    int deleteEnumParameter(String KeyId);

    /**
     * 参数注册详情
     * @param KeyId
     * @return
     */
    Enumparameters getParameterDetail(String KeyId);
}
