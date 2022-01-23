package ru.as.homeworks;


import java.util.Arrays;

public class HomeworkApp {

    private static final int size = 10000000;
    private static final int h = size / 2;
    private static final float[] arr = new float[size];


    public static void main( String[] args )
    {
        calculationArrayWithOutThread();
        calculationArrayWithThread();

    }

    public static void calculationArrayWithOutThread(){
       Arrays.fill(arr, 1);
       long timeWork = System.currentTimeMillis();
       for (int i = 0; i < arr.length; i++) {
           arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
       }
       System.out.printf("Время выполнения без потока - %s. \n", System.currentTimeMillis() - timeWork);

    }


    static class threadA implements Runnable {
        float[] arr;
        int threadNum;

        threadA(float[] arr, int threadNum) {
            this.arr = arr;
            this.threadNum = threadNum;
        }

        @Override
        public void run() {
            final long timeCalculationArrayStart = System.currentTimeMillis();
            for (int i = 0; i < arr.length; i++) {
                arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            final long timeCalculationArrayFinish = System.currentTimeMillis() - timeCalculationArrayStart;
            System.out.printf("Время подсчета - Поток %s Время %d \n", threadNum, timeCalculationArrayFinish);
        }
    }

    private static void calculationArrayWithThread() {
        Arrays.fill(arr, 1);
        System.out.printf("\nВремя выполнения с разделением на потоки: \n");
        final long timeDivideArrayStart = System.currentTimeMillis();
        final float[] a1 = new float[h];
        final float[] a2 = new float[h];
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        final long timeDivideArrayFinish = System.currentTimeMillis() - timeDivideArrayStart;
        System.out.printf("Время разделения массива на два - %s \n", timeDivideArrayFinish);

        Thread thread1 = new Thread(new threadA(a1, 1));
        Thread thread2 = new Thread(new threadA(a2, 2));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final long timeJoinArrayStart = System.currentTimeMillis();
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        final long timeJoinArrayFinish = System.currentTimeMillis() - timeJoinArrayStart;
        System.out.printf("Время объединения массивов - %s \n", timeJoinArrayFinish );

        System.out.printf("Итоговое время - %s \n" , ((System.currentTimeMillis() - timeDivideArrayStart)) );
    }


}
