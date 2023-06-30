package test1;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Set;

/**
 * 此题使用时长2h
 */
public class LCAddress {

    private String LCstr;
    private Long LClong;

    public LCAddress(String LCstr) {
        this.LCstr = LCstr;
    }

    public LCAddress(Long LClong) {
        this.LClong=LClong;
    }

    public Long LCstrTransToLCLong(){
        Long lclong= 0L;
        //先判断是字符串中是否有##，没有的话直接分割字符串然后拼接
        if (LCstr.indexOf("##")<0){
            String tempstr= "";
            String[] LCSplit = LCstr.split("#");
            for (String s : LCSplit) {
                String lessFour = isLessFour(s);
                tempstr+=lessFour;
            }
            System.out.println(tempstr);
            BigInteger bigInteger = new BigInteger(tempstr, 16);
            lclong =bigInteger.longValue();
            return lclong;
        }
        //因为零压缩法只能使用一次，所以只存在一个##，然后根据##的位置进行不同的处理，
        int i = LCstr.indexOf("##");
        //根据##将字符串切割成前缀和后缀，根据前后缀的情况来对前后缀进行修改
        String prefix = LCstr.substring(0,i);
        String suffix = LCstr.substring(i+2,LCstr.length());
        //若前缀为空，则就需要根据后缀的情况对前缀进行补0
        if ("".equals(prefix)){
            String[] split = suffix.split("#");
            switch (split.length){
                case 2:
                    prefix="00000000";
                    break;
                case 3:
                    prefix="0000";
                    break;
                default:
                    prefix="000000000000";
                    break;
            }
        }
        //若后缀为空则直接补0
        if ("".equals(suffix)){
            suffix="0000";
        }
        //最后将前后缀直接对#进行分割然后拼接
        String[] prefixSplit = prefix.split("#");
        String prefixTemp = "";
        for (String s : prefixSplit) {
            String lessFour = isLessFour(s);
            prefixTemp+=lessFour;
        }
        prefix=prefixTemp;
        String[] suffixSplit = suffix.split("#");
        String suffixTemp = "";
        for (String s : suffixSplit) {
            String lessFour = isLessFour(s);
            suffixTemp+=lessFour;
        }
        suffix=suffixTemp;
        String LCConcat = prefix+"0000"+suffix;
        System.out.println(LCConcat);
        //将拼接好的十六进制字符串装换成长整型数
        BigInteger bigInteger = new BigInteger(LCConcat, 16);
        lclong=bigInteger.longValue();
        return lclong;
    }

    public String isLessFour(String str){
        if (str.length()<4){
            switch (str.length()){
                case 1:
                    str="000"+str;
                    break;
                case 2:
                    str="00"+str;
                    break;
                case 3:
                    str="0"+str;
                    break;
            }
            return str;
        }else {
            return str;
        }
    }

    public String LCLongTransToLCstr(){
        String str = String.valueOf(LClong);
        BigInteger bigInteger = new BigInteger(str);
        String toString = bigInteger.toString(16);
        if (toString.length() <16){
            int key=16-toString.length();
            for (int i = 0; i < key; i++) {
                toString = "0"+toString;
            }
        }

        char[] toCharArray = toString.toCharArray();
        String bankString = "";
        for (int i = 0; i < toCharArray.length; i++) {
            if (i % 4 ==0 && i > 0){
                bankString=bankString+"#";
            }
            bankString=bankString+toCharArray[i];
        }
        return bankString;
    }

}
