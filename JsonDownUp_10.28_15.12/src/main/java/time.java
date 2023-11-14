//import java.util.concurrent.LinkedBlockingDeque;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//
//public class time {
//
//        public static void main(String[] args){
//            int[] intervals = {1,3,4,5,1,2,3};
//            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
//                    2 ,
//                    5,
//                    200,
//                    TimeUnit.MICROSECONDS,
//                    new LinkedBlockingDeque<>());
//
//            threadPoolExecutor.submit(new Runnable() {
//                @Override
//                public void run() {
//                    int index = 0;
//                    while(index< intervals.length){
//                        System.out.println(intervals[index]);
//                        try{
//                            Thread.sleep(intervals[index]*1000);
//                        }
//                        catch (InterruptedException e)
//                        {e.printStackTrace();
//                        }
//                        index += 1;
//                    }
//                }
//            });
//        }
//}
