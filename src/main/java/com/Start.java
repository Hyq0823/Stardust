package com;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

@RestController
//@EnableAutoConfiguration
@SpringBootApplication
public class Start {


    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("test1")
    public String test1(){
        System.out.println("teset1");
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://47.92.214.6:9988/test/", String.class);
        return responseEntity.getBody();
    }


    @RequestMapping("test2")
    public String test2() throws Exception {
        InputStream is = null;
        try {
            is = Start.class.getClassLoader().getResourceAsStream("druid.properties");
            Properties properties = loadProperties(is);

            DruidDataSource dataSource = new DruidDataSource();
            dataSource.configFromPropety(properties);


            DruidPooledConnection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            boolean execute = statement.execute("select * from user ");
            ResultSet resultSet = statement.getResultSet();
            System.out.println(resultSet.toString());
            return resultSet.toString();
        } finally {
            if(is != null){
                is.close();
            }
        }
    }

    public static Properties loadProperties(InputStream is) throws IOException {
        Properties pt = new Properties();
        pt.load(is);
        return pt;

    }

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        return new RestTemplate(factory);
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(5000);//ms
        factory.setConnectTimeout(15000);//ms
        return factory;
    }

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Start.class, args);


//        Object arg0 = "INDEX_TYPE_1".getBytes("UTF-8");
//        Serializable obj = (Serializable) arg0;
//
//        ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
//        serialize(obj, baos);
//        byte[] bytes = baos.toByteArray();
//        System.out.println(bytes);
    }


    public static void serialize(Serializable obj, OutputStream outputStream) {
        if (outputStream == null) {
            throw new IllegalArgumentException("The OutputStream must not be null");
        }
        ObjectOutputStream out = null;
        try {
            // stream closed in the finally
            out = new ObjectOutputStream(outputStream);
            out.writeObject(obj);

        } catch (IOException ex) {

        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ex) {
                // ignore close exception
            }
        }
    }
}
