package concurrency.thread;

import sun.net.ftp.FtpClient;

/**
 * @Author:xiepei5
 * @Decription:
 * @Dete : 16:10 2019/1/7
 */
public class SimpleWN {
    final static Object object =  new Object();
    public static class T1 extends Thread {
        public void run(){
            synchronized (object){
                System.out.println(System.currentTimeMillis() + ": T1 start!");
                try {
                    System.out.println(System.currentTimeMillis() + ": T1 wait for object!");
                    object.wait();//InterruptedException 有这个才能抛出吗
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + "T1 end！");
            }
        }
    }

    public  static class T2 extends Thread{
        @Override
        public void run () {
            synchronized (object){
                System.out.println(System.currentTimeMillis() + ": T2 start!");

                    System.out.println(System.currentTimeMillis() + ": T2 wait for object!");
                    object.notify();//InterruptedException 有这个才能抛出吗

                System.out.println(System.currentTimeMillis() + "T2 end！");
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main ( String[] args ) {
        Thread t1 = new T1();
        Thread t2 = new T2();
        t1.start();
        t2.start();
    }
}
