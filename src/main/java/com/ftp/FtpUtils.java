package com.ftp;

import org.apache.commons.net.ftp.*;

import java.io.IOException;

/**
 * @Author: huangyunquan
 * @Description:Created on 2018/7/22 12:10.
 */

public class FtpUtils {

    private String username;
    private String password;
    private String server;
    private int port;


    private FTPClient ftpClient = null;
    private static String CODE = "UTF-8";

    public static void main(String[] args) {

    }

    public FTPFile[] listFiles(String dir, FTPFileFilter filter) throws IOException {
        open();
        FTPFile[] ftpFiles = ftpClient.listFiles(dir,filter);
        return ftpFiles;
    }



    void test() throws IOException {
//        ftpClient.changeWorkingDirectory()
        FTPFile[] ftpFiles = ftpClient.listFiles();
        FTPFile ftpFile = ftpFiles[0];
        ftpFile.getTimestamp().getTime();
    }

    /**
     * 连接服务器
     *
     * @return 连接成功与否 true:成功， false:失败
     */
    public boolean open() throws IOException {
        if (ftpClient != null && ftpClient.isConnected()) {
            return true;
        }
        ftpClient = new FTPClient();
        // 连接
        ftpClient.connect(server,port);
        ftpClient.login(username,password);
        // 检测连接是否成功
        int reply = ftpClient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            this.close();
            return false;
        }
        // 设置上传模式UTF-8
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        ftpClient.setControlEncoding(CODE);
        ftpClient.enterLocalPassiveMode();
        return true;
    }

    /**
     * 关闭链接
     */
    public void close() {
        try {
            if (ftpClient != null && ftpClient.isConnected()) {
                ftpClient.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
