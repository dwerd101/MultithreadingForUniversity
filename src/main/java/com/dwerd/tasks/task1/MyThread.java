package com.dwerd.tasks.task1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.logging.Logger;

/**
 * 1.Основной поток создает дочерний. Родительский и дочерний потоки должны распечатать по десять строк текста.
 */
@Data
public class MyThread {

    List<String> mainThreadList;
    List<String> daughterListThread;
    HttpServletRequest request;

    public MyThread(HttpServletRequest request)  {
        try {
            this.request = request;
            Callable<List<String>> listCallable = new DaughterThread();
            FutureTask<List<String>> listFutureTask = new FutureTask<>(listCallable);
            Thread daughter = new Thread(listFutureTask);
            daughter.start();
            mainThreadList = enterTextThread(request);
            daughterListThread = listFutureTask.get();
            daughter.interrupt();
        } catch (InterruptedException | ExecutionException e) {
            Logger.getLogger(e.toString());
        }
    }


    class DaughterThread implements Callable<List<String>> {
        @Override
        public List<String> call() throws Exception {
            return enterTextDaughterThread(request);
        }

    }

    private List<String> enterTextThread(HttpServletRequest request) {
        return getStrings(request);
    }

    private List<String> getStrings(HttpServletRequest request) {
        String text = request.getParameter("text");
        System.out.println("Имя потока: "+Thread.currentThread().getName());
        if(text==null) {
            return new ArrayList<>();
        }
        StringBuffer s = new StringBuffer().append("Имя потока: ").append(Thread.currentThread().getName()).append(". ").append("Текст: ").append(text);
        List<String> list = new ArrayList<>();
        for(int i=0; i<10; i++) {
            list.add(s.toString());
        }
        return list;
    }

    private List<String> enterTextDaughterThread(HttpServletRequest request) {
        return getStrings(request);
    }

}
