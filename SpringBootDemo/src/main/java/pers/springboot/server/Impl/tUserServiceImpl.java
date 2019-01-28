package pers.springboot.server.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pers.springboot.dao.tUserMapper;
import pers.springboot.domain.tUser;
import pers.springboot.server.tUserService;

import java.util.List;


@Service
public class tUserServiceImpl implements tUserService {
    @Autowired
    private tUserMapper tUserMapper;
    @Override
    public List<tUser> getAll() {
        return tUserMapper.getAll();
    }

    @Override
    public int insert(tUser record) {
        return tUserMapper.insert(record);
    }

    @Override
    public int insertSelective(tUser record) {
        return tUserMapper.insertSelective(record);
    }
    //默认key的方式
    //@Cacheable(cacheNames = {"tUser"}, key = "#root.methodName+'['+#id+']'")
    //keyGenerator
    @Cacheable(cacheNames = {"tUser"}/*, keyGenerator = "myKeyGenerator", condition = "#a0 > 1", unless = "#a1 == 2"*/)
    @Override
    public tUser findTUserById(Integer id) {
        tUser tUser = tUserMapper.findTUserById(Long.valueOf(id));
        return tUser;
    }

    @CachePut(cacheNames = {"tUser"}, key = "#root.methodName+'['+#id+']'")
    @Override
    public int updateByPrimaryKeySelective(tUser record) {
        return tUserMapper.updateByPrimaryKeySelective(record);
    }
    //allEntries是否删除所有缓存,allEntries=true
    //beforeInvocation = false,缓存清除是否在方法执行之前
    @CacheEvict(cacheNames = {"tUser"}, key = "#id")
    @Override
    public int deleteById(Long id) {
        return tUserMapper.deleteById(id);
    }
}
