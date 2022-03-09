package com.duxuan.mapper;

import com.duxuan.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
//@Mapper注解是由Mybatis框架中定义的一个描述数据层接口的注解，注解往往起到的都是一个描述性作用，
// 用于告诉sprigng框架此接口的实现类由Mybatis负责创建，并将其实现类对象存储到spring容器中。
//@Reposity表示将dao层 这里的UserMapper 申明为一个Bean
public interface UserMapper {
    public User queryUserByName(String name);
}
