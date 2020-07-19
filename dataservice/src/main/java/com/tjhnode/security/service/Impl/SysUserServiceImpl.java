package com.tjhnode.security.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tjhnode.dataservice.model.vo.QueryPaginationModel;
import com.tjhnode.dataservice.model.vo.ResultPaginationModel;
import com.tjhnode.security.entity.SysUser;
import com.tjhnode.security.mapper.SysUserMapper;
import com.tjhnode.security.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author tjh
 * @since 2020-05-31
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public SysUser getSysUser(String username) {
        try {
            return sysUserMapper.getSysUser(username);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 查询用户列表
     *
     * @param query
     * @return
     */
    public ResultPaginationModel<SysUser> getUserList(QueryPaginationModel query) {
        ResultPaginationModel<SysUser> result = new ResultPaginationModel<>();
        try {
            StringBuilder sb = new StringBuilder();
            if (query.getQuerylist() != null && query.getQuerylist().size() > 0) {
                Set<String> keySet = query.getQuerylist().keySet();
                for (String key : keySet) {
                    sb.append(String.format(" and %s like concat('%',?,'%')", key, query.getQuerylist().get(key)));
                }
            }
            if (query.getPageindex() == 0) {
                query.setPageindex(1);
            }
            int startRow = (query.getPageindex() - 1) * query.getPagesize();
            int endRow = query.getPageindex() * query.getPagesize() - 1;
            sb.append(String.format(" limit %d,%d", startRow, endRow));
            String sql = "select a.id,username,realname,avatar,birthday,sex,email,phone,a.org_code,d.depart_name,d1.depart_name as depart_name1,c.role_name,c.description\n" +
                    "from sys_user a\n" +
                    "inner join sys_user_role b on a.id=b.user_id\n" +
                    "inner join sys_role c on b.role_id=c.id\n" +
                    "inner join sys_user_depart ud on a.id=ud.user_id\n" +
                    "inner join sys_depart d on ud.dep_id=d.id\n" +
                    "inner join sys_depart d1 on ud.dep_id=d1.parent_id where 1=1 ";
            List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql + sb.toString());
            result.setResultList(maps);
            result.setTotal(getTotal(query));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }

    /**
     * 获取总条数
     * @param query
     * @return
     */
    public Long getTotal(QueryPaginationModel query) {
        Long count =(long)0;
        try {
            StringBuilder sb = new StringBuilder();
            if (query.getQuerylist() != null && query.getQuerylist().size() > 0) {
                Set<String> keySet = query.getQuerylist().keySet();
                for (String key : keySet) {
                    sb.append(String.format(" and %s like concat('%',?,'%')", key, query.getQuerylist().get(key)));
                }
            }
            String sql = "select count(1) from sys_user a\n" +
                    "inner join sys_user_role b on a.id=b.user_id\n" +
                    "inner join sys_role c on b.role_id=c.id\n" +
                    "inner join sys_user_depart ud on a.id=ud.user_id\n" +
                    "inner join sys_depart d on ud.dep_id=d.id\n" +
                    "inner join sys_depart d1 on ud.dep_id=d1.parent_id where 1=1 ";
            count =(long) jdbcTemplate.queryForObject(sql + sb.toString(), Integer.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

}