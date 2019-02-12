package com.hl.hlf;

import java.io.*;
import java.sql.SQLException;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * Hello world!
 *
 */
public class App 
{
    private static String reg = "(?<TxId>[A-Za-z0-9]+\\^[A-Za-z0-9]+\\^[A-Za-z0-9]+)\\.*";
    private static Pattern pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);


    public static Properties loadProperties(InputStream is) throws IOException {
        Properties pt = new Properties();
        pt.load(is);
        return pt;

    }

    public static void main( String[] args ) throws SQLException, IOException {
//        File file =new File("C:\\Users\\Administrator\\Downloads\\所有时长为负的数据.txt");
        File file =new File("D:\\My Documents\\WeChat Files\\h13881553005\\Files\\20190103log\\2(1)");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = "";
        while((line=br.readLine())!=null){
//            System.out.println(line);
            boolean matches = pattern.matcher(line).find();
            if(!matches){
                System.out.println(line);
            }
        }
    }

}
