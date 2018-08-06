package com.other.intf;

/**
 * 网吧2--采用静态变量 + 匿名内部类 来实现
 */
public class InternetBar2 {
    private Double account = 200D;
    private final PeopleFilter NEED_AGE = people -> people.getAge() >= 18;
    public  final  PeopleFilter NEED_MONEY = people -> people.getMoney() > 10;



    public void takeMoney(Double money){
        account+=money;
    }

    public void take(People people){
        System.out.println("给别人开机子...");

        //校验是否能够开机
        boolean accept = NEED_AGE.accept(people);

        //无限加条件
        boolean accept2 = ((PeopleFilter) p -> p.getMoney() > 10).and(p -> p.getAge() > 10).accept(people);

        //声明好所有条件，自由组合
        PeopleFilter and = NEED_AGE.and(NEED_MONEY);

        boolean accept1 = and.accept(people);
        if(accept1){
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
