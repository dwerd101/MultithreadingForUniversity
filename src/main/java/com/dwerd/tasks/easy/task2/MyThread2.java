package com.dwerd.tasks.easy.task2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@Component
public class MyThread2 {

    public List<String> getList() {
        return getResults();
    }
    private static List<String> getResults() {
        return results;
    }

    private static List<String> results;
    {
        results = new ArrayList<>();
    }
    public MyThread2() {
        Print print = new Print();
        Print print2 = new Print();
        Print print3 = new Print();
        Print print4 = new Print();
        Print print5 = new Print();
        Print print6 = new Print();
        Print print7 = new Print();
        Print print8 = new Print();
        Print print9 = new Print();
        Print print10 = new Print();
        try {
            changeThread(print, print2, print3, print4, print5);
            changeThread(print6, print7, print8, print9, print10);
        } catch (InterruptedException e) {
            Logger.getLogger(e.toString());
        }
    }

    private void changeThread(Print print, Print print2, Print print3, Print print4, Print print5) throws InterruptedException {
        print();
        print.start();
        print.join();
        print();
        print2.start();
        print2.join();
        print();
        print3.start();
        print3.join();
        print();
        print4.start();
        print4.join();
        print();
        print5.start();
        print5.join();

    }
    static class Print extends Thread {

        @Override
        public void run() {
            MyThread2.print();
        }
    }



    @SneakyThrows
    private static void print() {
        StringBuilder stringBuilder = new StringBuilder();
        String text = stringBuilder.append("Имя потока: ").toString();

            getResults().add(text+Thread.currentThread().getName());
            System.out.println(text+Thread.currentThread().getName());
    }

}
