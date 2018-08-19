package com.thread.architect.base.lesson5_changeLock;

import java.util.concurrent.TimeUnit;

/**
 * 修改锁内部的属性--不影响锁机制
 *
 * 同一对象属性的修改不会影响锁的情况
 *
 * in : t1
 * t1 ,User{username='zzz', password='zzz'}
 * in : t2
 * t2 ,User{username='fff', password='fff'}
 */
public class ModifyLockAttr {

    private User user = new User("hyq","hyq");

    class  User {
        private String username;
        private String password;


        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return "User{" +
                    "username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }


    public void method(String username,String pwd){
        synchronized(user){
            System.out.println("in : " + Thread.currentThread().getName());
            user.setUsername(username);
            user.setPassword(pwd);
            System.out.println(Thread.currentThread().getName() + " ," + user);
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ModifyLockAttr modifyLockAttr = new ModifyLockAttr();
        new Thread(()->modifyLockAttr.method("zzz","zzz"),"t1").start();

        new Thread(()->modifyLockAttr.method("fff","fff"),"t2").start();
    }


}
