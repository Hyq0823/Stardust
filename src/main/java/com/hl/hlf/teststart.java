package com.hl.hlf;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;

public class teststart {


    public static void main(String[] args){
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("mybatis.cfg.xml");
        } catch (IOException e) {

        }
        SqlSessionFactory sessionFactory  =new SqlSessionFactoryBuilder().build(reader);
        GoodsMapper mapper = sessionFactory.openSession().getMapper(GoodsMapper.class);
        try {
            List<Map> list = mapper.selectAllGoods();
            System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("运行错误");
        }
    }
}