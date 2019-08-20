package com.itheima;

/**
 * @Classname TestOne
 * @Description TODO
 * @Date 2019/8/18 16:36
 * @Created by wangshuyuan
 */
public class TestOne
{
    public static void main(String[] args) {
        System.out.println(getValue(2));

    }
   public static int getValue(int i){
        int result=0;
        switch (i){
            case 1:
                result=result+i;
            case 2:
                result=result+i*2;
            case 3:
                result=result+i*3;

        }
        return result;
   }
}
