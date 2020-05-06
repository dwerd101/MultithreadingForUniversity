package com.dwerd.tasks.task3;

import lombok.SneakyThrows;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

/**
 *  3.Создать распараллеливающийся алгоритм сортировки одномерного массива.
 */
public class MyThread3 {


    String text;
    List<Integer> list;
    List<String> listThread;
    Map<Integer, String> map;
    static CountDownLatch countDownLatch;

    public List<Integer> getList() {
        return list;
    }

    public MyThread3(HttpServletRequest request) {
        listThread = new LinkedList<>();
        list = new ArrayList<>();
        map = new HashMap<>();
        countDownLatch = new CountDownLatch(1);
        try {
        text = request.getParameter("text").replaceAll("\\s*,\\s*", " ");
        String[] array = text.split(" ");

            for (String textAr : array
            ) {
                list.add(Integer.parseInt(textAr));
            }
        } catch (NullPointerException | NumberFormatException e) {
            Logger.getLogger("Пожалуйста числа!");
            list.clear();
        }
    }



    @SneakyThrows
    public List<Integer> sort() {
        Calculate1 calculate1 = new Calculate1(list,this);
        Calculate2 calculate2 = new Calculate2(list,this);
        calculate1.start();
        calculate2.start();
        countDownLatch.await();
        listThread.add("Отсортировал все \uD83D\uDE0A");
        return list;
    }

    public List<String> getListThread() {
        return listThread;
    }


    static class Calculate1 extends Thread {

        List<Integer> integers;
        MyThread3 myThread3;

        public Calculate1(List<Integer> integers, MyThread3 myThread3) {
            this.integers = integers;
            this.myThread3 = myThread3;

        }

        @SneakyThrows
        @Override
        public void run() {
            while (!isInterrupted()) {
                Thread.sleep(100);
                myThread3.getMinNumber(integers);

            }


        }
    }

    static class Calculate2 extends Thread {

        List<Integer> integers;
        MyThread3 myThread3;

        public Calculate2(List<Integer> integers, MyThread3 myThread3) {
            this.integers = integers;
            this.myThread3 = myThread3;

        }

        @SneakyThrows
        @Override
        public void run() {
            while (!isInterrupted()) {
                Thread.sleep(100);
                myThread3.setNumber(integers);

            }
        }
    }


    private  synchronized void getMinNumber(List<Integer> list) throws InterruptedException {

        for (int i = 0; i < list.size(); i++) {
            listThread.add("Имя потока: "+Thread.currentThread().getName()+". Выполняю метод: getMinNumber()");
            int minValue = list.get(i);
            int minIndex = i;
            for (int j = i; j < list.size(); j++) {
                if (minValue > list.get(j)) {
                    minValue = list.get(j);
                    minIndex = j;
                }

            }
            String minValueS = Integer.toString(minValue);
            String maxValues = Integer.toString(list.get(i));
            int maxIndex = i;
            if(maxValues.equals(minValueS)) {
                continue;
            }
            map.put(minIndex, minValueS);
            map.put(maxIndex, maxValues);
            notify();
            wait(1000);
        }
        Thread.currentThread().interrupt();
    }
    private  synchronized void setNumber(List<Integer> list) throws InterruptedException {

        int count = 1;
        int minIndex = 0;
        int maxIndex = 0;
        int minValue = 0;
        int maxValue = 0;
        listThread.add("Имя потока: "+Thread.currentThread().getName()+". Выполняю метод: setNumber()");
        if(map.size()==1) {
            notify();

            Thread.currentThread().interrupt();
        }
        else {
            for (Map.Entry<Integer, String> a : map.entrySet()) {
                if (count < 2) {
                    minIndex = a.getKey();
                    minValue = Integer.parseInt(a.getValue());
                    count++;
                } else {
                    maxIndex = a.getKey();
                    maxValue = Integer.parseInt(a.getValue());
                }
            }
            count = 0;
            if (minValue > maxValue) {
                int tempV = minValue;
                int tempK = minIndex;
                minValue = maxValue;
                minIndex = maxIndex;
                maxIndex = tempK;
                maxValue = tempV;
            }

            int temp = list.get(maxIndex);
            list.set(maxIndex, minValue);
            list.set(minIndex, temp);
            map.clear();
            notify();

            wait(1000);

            if (map.isEmpty()) {
                countDownLatch.countDown();
                Thread.currentThread().interrupt();

            }
        }
    }

}