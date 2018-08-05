package com.hl.hlf;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{


    public static Properties loadProperties(InputStream is) throws IOException {
        Properties pt = new Properties();
        pt.load(is);
        return pt;

    }

    public static void main( String[] args ) throws SQLException, IOException {

        InputStream is = null;
        try {
             is = App.class.getClassLoader().getResourceAsStream("druid.properties");
            Properties properties = loadProperties(is);

            DruidDataSource dataSource = new DruidDataSource();
            dataSource.configFromPropety(properties);


            DruidPooledConnection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            boolean execute = statement.execute("select * from user ");
            ResultSet resultSet = statement.getResultSet();
            System.out.println(resultSet.toString());
        } finally {
            if(is != null){
                is.close();
            }
        }
    }
}
