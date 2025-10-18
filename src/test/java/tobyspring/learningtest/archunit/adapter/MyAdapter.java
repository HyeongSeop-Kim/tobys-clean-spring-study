package tobyspring.learningtest.archunit.adapter;

import tobyspring.learningtest.archunit.application.MyService;
import tobyspring.learningtest.archunit.application.MyService2;

public class MyAdapter {
    MyService myService;

    void run() {
        myService = new MyService();
        System.out.println("myService = " + myService);
    }
}
