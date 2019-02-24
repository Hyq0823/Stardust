package com.hl.hlf;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class teststart {


    public static void main(String[] args) throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis.cfg.xml");
        SqlSessionFactory sessionFactory  =new SqlSessionFactoryBuilder().build(reader);
        try(SqlSession sqlSession = sessionFactory.openSession()) {
            String queryId= "1";
            Object count = sqlSession.selectOne("com.hl.hlf.GoodsMapper.testCount", queryId);
            System.out.println(count);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}