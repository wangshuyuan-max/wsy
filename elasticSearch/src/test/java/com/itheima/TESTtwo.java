package com.itheima;

import org.junit.Test;

/**
 * @Classname TESTtwo
 * @Description TODO
 * @Date 2019/8/19 9:37
 * @Created by wangshuyuan
 */
public class TESTtwo {
    static boolean print(char c){
        System.out.println(c);
        return true;
    }

    public static void main(String[] args) {
        int i=0;
        for(print('A');print('B')&&(i<2);print('C')){
            i++;
            print('D');
        }
    }
    @Test
    public void test(){
        for (int i = 0; i < 5; ++i) {

            System.out.println(i);
        }
    }
}
