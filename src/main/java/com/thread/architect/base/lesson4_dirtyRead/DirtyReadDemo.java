package com.thread.architect.base.lesson4_dirtyRead;

import java.util.concurrent.TimeUnit;

/**
 * 脏读
 * 在业务中，需要控制好同步，避免出现读脏数据，保证业务的原子性
 */
public class DirtyReadDemo {

    private String username;
    private String password;


    public synchronized  void setValue(String username,String password){
        this.username = username;

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.password = password;
    }



    public  void getValue(){
        System.out.println("username : " + username + "  password: " + password);
    }


    public static void main(String[] args) {

        DirtyReadDemo dirtyReadDemo = new DirtyReadDemo();
        new Thread(()-> dirtyReadDemo.setValue("hyq","123"),"t1").start();

        //getValue 没有控制同步，则业务不完整
        new Thread(()-> dirtyReadDemo.getValue(),"t2").start();


        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dirtyReadDemo.getValue();



    }
}
