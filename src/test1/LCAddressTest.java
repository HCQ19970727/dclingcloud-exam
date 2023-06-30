package test1;

public class LCAddressTest {


    public static void main(String[] args) {

        String ff = "#1#2#3#4";
        LCAddress lcAddress = new LCAddress(ff);
        System.out.println(lcAddress.LCstrTransToLCLong());
        LCAddress lcAddress1 = new LCAddress(28148566776324L);
        System.out.println(lcAddress1.LCLongTransToLCstr());
        System.out.println("hello git!!");
    }
}
