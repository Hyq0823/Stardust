package com.other;

        import java.util.ArrayList;
        import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("h");
        list.add("y");
        list.add("q");

        for(String s : list){
            System.out.println("外部:"+list.size());
            if("q".equals(s)){
                list.remove(s);
            }
        }
    }
}
