package pers.springboot.server;

import pers.springboot.domain.tUser;

import java.util.List;

public interface tUserService {
    /**
     * 获取用户的所有信息
     * @return List<tUser>
     */
    List<tUser> getAll();

    int insert(tUser record);

    int insertSelective(tUser record);
    /**
     * 根据id获取信息
     * @param id
     * @return
     */
    tUser findTUserById(Integer id);
    /**
     * 根据id更新数据
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(tUser record);

    /**
     * 根据id删除用户信息
     * @param id
     * @return
     */
    int deleteById(Long id);
}
