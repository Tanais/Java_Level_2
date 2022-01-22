package ru.as.homeworks;


public class HomeworkApp {

    private static final int size = 10000000;
    private static final int h = size / 2;
    private static float[] arr = new float[size];


    public static void main( String[] args )
    {
        withOutThread();
        dividedArray();

    }

    public static void setArr() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
    }

   public static void withOutThread (){
        setArr();
       long timeWork = System.currentTimeMillis();
       for (int i = 0; i < arr.length; i++) {
           arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
       }
       System.out.println(System.currentTimeMillis() - timeWork);
       System.out.println("Время без потока.");
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
            final long timeCalculationArrayBefore = System.currentTimeMillis();
            for (int i = 0; i < arr.length; i++) {
                arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            final long timeCalculationArrayFinal = System.currentTimeMillis() - timeCalculationArrayBefore;
            System.out.println("Время обработки формулы в массиве (поток " + threadNum + "): " + timeCalculationArrayFinal + " мс");
        }
    }

    private static void dividedArray() {
        System.out.println("2. Обработка массива при помощи потоков:");
        setArr();
        final long timeDivideArrayBefore = System.currentTimeMillis();
        final float[] a1 = new float[h];
        final float[] a2 = new float[h];
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        final long timeDivideArrayFinal = System.currentTimeMillis() - timeDivideArrayBefore;
        System.out.println("Время разделения массива: " + timeDivideArrayFinal + " мс");

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

        final long timeJoinArrayBefore = System.currentTimeMillis();
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        final long timeJoinArrayAfter = System.currentTimeMillis() - timeJoinArrayBefore;
        System.out.println("Время соединения массивов: " + timeJoinArrayAfter + " мс");

        System.out.println("Время общее: " + (System.currentTimeMillis() - timeDivideArrayBefore) + " мс");
    }


}
