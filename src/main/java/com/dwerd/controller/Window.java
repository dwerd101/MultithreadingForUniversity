package com.dwerd.controller;

import com.dwerd.tasks.task1.MyThread;
import com.dwerd.tasks.task2.MyThread2;
import com.dwerd.tasks.task3.MyThread3;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class Window {

    @GetMapping("/")
    public String mainPage() {
        return "main";
    }

    @RequestMapping(value = "/list_tasks", method = {RequestMethod.GET, RequestMethod.POST})
    private String listTasksPage() {
        return "listTasks";
    }

    @RequestMapping(value = "/list_tasks/task/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    private String listEasyTask1(@PathVariable("id") int id, ModelMap map, HttpServletRequest request) {
        switch (id) {
            case 1:
                MyThread myThread = new MyThread(request);
                List<String> list = Stream.concat(myThread.getMainThreadList().stream(), myThread.getDaughterListThread().stream()).collect(Collectors.toList());
                if(list.size()==0) {
                    map.addAttribute("lists",null);
                }
               else map.addAttribute("lists", list);
            return "Task";
            case 2:
                MyThread2 myThread2 = new MyThread2();
                List<String> list1 = myThread2.getList();
                map.addAttribute("list1",list1);
                return "Task";
            case 3:
                MyThread3 myThread3 = new MyThread3(request);
                if(request.getParameter("text")==null) {
                    map.addAttribute("listIsEmpty", this);
                    return "Task";
                }
                else if(myThread3.getList().isEmpty()) {
                    map.addAttribute("notNumbers", this);
                    return "Task";
                }
                List<Integer> list2 = myThread3.sort();
                List<String> list3 = myThread3.getListThread();
                map.addAttribute("list2", list2);
                map.addAttribute("list3", list3);
                return "Task";


        }
        return "/error";
    }

}
