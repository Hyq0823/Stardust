package com.other.intf;

/**
 * 网吧1--新加一个内部类来实现
 */
public class InternetBar {
    private Double account = 200D;
    private InternetBarPeopleFilter internetBarPeopleFilter = new InternetBarPeopleFilter();

    public void takeMoney(Double money){
        account+=money;
    }

    public void take(People people){
        System.out.println("给别人开机子...");

        //校验是否能够开机
        boolean accept = internetBarPeopleFilter.accept(people);

        //来者不拒
        boolean accept1 = ((PeopleFilter) p -> true).accept(people);


        if(accept){
            System.out.println("开机成功");
        }else{
            System.out.println("开机失败!");
        }
    }


    /**
     * 采用内部类的好处是,可以获取到当前内的运行环境（变量 和属性）
     */
    class InternetBarPeopleFilter implements PeopleFilter{
        @Override
        public boolean accept(People people) {
            if(account < 100){
                System.out.println("网吧金额小于100，开不起机子了...准备倒闭");
                return false;
            }
            if(people.getAge() < 18){
                System.out.println("上网人年龄小于18岁，未成年人，拒绝开机....");
                return false;
            }
            return true;

        }
    }



}
