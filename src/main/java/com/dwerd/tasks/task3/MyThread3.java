package com.dwerd.tasks.task3;

import lombok.SneakyThrows;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

/**
 *  3.Создать распараллеливающийся алгоритм сортировки одномерного массива.
 *  Используется сортировка выбором.
 *  @autor Болобан Роман
 */
public class MyThread3 {

    /**Текст полученый из запроса*/
    String text;

    /**Список чисел*/
    List<Integer> list;

    /**Список имен потоков */
    List<String> listThread;

    /** Карта максимальных и минимальных значении */
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


    /**
     * Сортирует список при помощи потоков.
     * @return {@link MyThread3#list}.
     * @version 1.0
     */
    @SneakyThrows
    public List<Integer> sort() {
        FirstPartSort firstPartSort = new FirstPartSort(list,this);
        SecondPartSort secondPartSort = new SecondPartSort(list,this);
        firstPartSort.start();
        secondPartSort.start();
        countDownLatch.await();
        listThread.add("Отсортировал все \uD83D\uDE0A");
        return list;
    }

    public List<String> getListThread() {
        return listThread;
    }


    static class FirstPartSort extends Thread {

        List<Integer> list;
        MyThread3 myThread3;

        public FirstPartSort(List<Integer> list, MyThread3 myThread3) {
            this.list = list;
            this.myThread3 = myThread3;

        }

        @SneakyThrows
        @Override
        public void run() {
            while (!isInterrupted()) {
                Thread.sleep(100);
                myThread3.getNumber(list);

            }


        }
    }

    static class SecondPartSort extends Thread {

        List<Integer> list;
        MyThread3 myThread3;

        public SecondPartSort(List<Integer> list, MyThread3 myThread3) {
            this.list = list;
            this.myThread3 = myThread3;

        }

        @SneakyThrows
        @Override
        public void run() {
            while (!isInterrupted()) {
                Thread.sleep(100);
                myThread3.setNumber(list);

            }
        }
    }

    /**
     * Ищет минимальные, максимальмальные значения и кладет в {@link MyThread3#map}.
     * Затем нить останавливается и ждет ответа от метода {@link MyThread3#setNumber(List<Integer> list)}.
     * Если ответа нет, то в течение одной секунды нить продолжит работу.
     * Когда список будет пустым, нить вызовет interrupt().
     * @param list
     * @throws InterruptedException если целевой ресурс выдает это исключение.
     * @version 1.0
     */
    private  synchronized void getNumber(List<Integer> list) throws InterruptedException {

        for (int i = 0; i < list.size(); i++) {
            listThread.add("Имя потока: "+Thread.currentThread().getName()+". Выполняю метод: getNumber()");
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

    /**
     * Меняет максимальные и минимальные значения местами в {@link MyThread3#list}.
     * Если размер {@link MyThread3#list} равняется нулю, вызывает  {@link MyThread3#countDownLatch}, декрементирует его
     * и прерывается нить вызовом interrupt().
     * Иначе меняем местами значения в {@link MyThread3#list}.
     * Затем нить останавливается и ждет ответа от метода {@link MyThread3#getNumber(List<Integer> list)}.
     * Если ответа нет, то в течение одной секунды нить продолжит работу.
     * @see CountDownLatch
     * @param list
     * @throws InterruptedException если целевой ресурс выдает это исключение.
     * @version 1.0
     */
    private  synchronized void setNumber(List<Integer> list) throws InterruptedException {

        int count = 1;
        int minIndex = 0;
        int maxIndex = 0;
        int minValue = 0;
        int maxValue = 0;
        listThread.add("Имя потока: "+Thread.currentThread().getName()+". Выполняю метод: setNumber()");
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