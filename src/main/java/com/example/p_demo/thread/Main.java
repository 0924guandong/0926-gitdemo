package com.example.p_demo.thread;

import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        Thread t = new MyThread();
//        t.start();
//        Thread.sleep(1000);
//        t.interrupt(); // 中断t线程
//        t.join(); // 等待t线程结束
//        System.out.println("end");

//        MyThread2 t2 = new MyThread2();
//        t2.start();
//        Thread.sleep(1);
//        t2.interrupt();
//        t2.join();
//        System.out.println("main end");

//        AddThread add = new AddThread();
//        DecThread dec = new DecThread();
//        add.start();
//        dec.start();
//        add.join();
//        dec.join();
//        System.out.println(Counter.count);
//        TaskQueue tq = new TaskQueue();
//        String task = tq.getTask();
//        if (StringUtils.isNotBlank(tq.getTask())){
//            System.out.println(task);
//        }
//        tq.addTask("测试");

//        TaskQueue taskQueue = new TaskQueue();
//        ArrayList<Thread> list = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            Thread t = new Thread(() -> {
//                while (true){
//                    try{
//                        String task = taskQueue.getTask();
//                        System.out.println("execute -- " + task);
//                    } catch (InterruptedException e) {
//                        return;
//                    }
//                }
//            });
//
//            t.start();
//            list.add(t);
//        }
//
//        Thread r = new Thread(() -> {
//            for (int i = 0; i < 10; i++) {
//                String time = "t -" + Math.random();
//                System.out.println("add -- " + time);
//                taskQueue.addTask(time);
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        r.start();
//        r.join();
//
//        for (Thread thread : list) {
//            thread.interrupt();
//        }

//        TaskQueue taskQueue = new TaskQueue();
//        new Thread(() -> {
//            System.out.println(taskQueue.test());
//        }).start();
//        new Thread(() -> {
//            System.out.println(taskQueue.test2());
//        }).start();
//        new Thread(() -> {
//            System.out.println(taskQueue.test3());
//        }).start();
//
//        System.out.println("----");
//        new Thread(() -> {
//            taskQueue.test5();
//        }).start();
//        TaskQueue taskQueue = new TaskQueue();
//        Thread t =new Thread(taskQueue::add);
//        Thread r =new Thread(taskQueue::dec);
//        t.start();
//        r.start();
//        t.join();
//        r.join();
//        System.out.println(TaskQueue.num);

//        TaskQueue taskQueue = new TaskQueue();
//        ArrayList<Thread> list = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            Thread t = new Thread(() ->{
//                while (true){
//                    String data = null;
//                    try {
//                        data = taskQueue.getData();
//                        System.out.println("get --- "+Thread.currentThread().getName()  +data);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            });
//            t.start();
//            list.add(t);
//        }
//
//        for (int i = 0; i < 10; i++) {
//            Thread b = new Thread(() -> {
//                String s = "t --" + Math.random();
//                taskQueue.addData(s);
//                System.out.println("add -- " +s);
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//            b.start();
//        }
//
//        for (Thread thread : list) {
//            thread.interrupt();
//        }

        ExecutorService es = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            es.submit(new Task("thread -- "+ i));
        }
        es.shutdown();

    }
}


class Task implements Runnable{
    private final String name ;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("task --- "+ name);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class TaskQueue{
    Queue<String> queue = new LinkedList<>();
    private final Lock lock  = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    public static Integer num = 0;

    public void addData(String str){
        lock.lock();
        try {
            queue.add(str);
            this.condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public String getData() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()){
                condition.await();
            }
            return queue.remove();
        } finally {
            lock.unlock();
        }
    }

    public String test6(){
        return "test6";
    }

    public void add(){
        lock.lock();
        try {
            for (int i = 0; i < 10000; i++) {
                TaskQueue.num += 1;
            }
            System.out.println(TaskQueue.num);
            System.out.println("测试");
        }finally {
            lock.unlock();
        }
    }

    public void dec(){
        lock.lock();
        try{
            for (int i = 0; i < 10000; i++) {
                TaskQueue.num -= 1;
            }
            System.out.println(TaskQueue.num);
            System.out.println("减法");
        }finally {
            lock.unlock();
        }
    }

    public synchronized void test5(){
        this.notifyAll();
    }

    public synchronized String  test(){
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "test";
    }

    public synchronized String  test2(){
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "test2";
    }

    public synchronized String  test3(){
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "test3";
    }

    public  synchronized void addTask(String str){
        this.queue.add(str);
        this.notifyAll();
    }

    public synchronized String getTask() throws InterruptedException {
        while (queue.isEmpty()){
            this.wait();
        }
        return queue.remove();
    }
}

class Counter{
    public static final Object lock = new Object();
    public static int count = 0;
}

class AddThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            synchronized (Counter.lock){
                Counter.count += 1;
            }
        }
    }
}

class DecThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            synchronized (Counter.lock){
                Counter.count -= 1;
            }
        }
    }
}

class MyThread2 extends Thread{
    @Override
    public void run() {
        int num =0;
        if (!isInterrupted()){
            num++;
            System.out.println("thread " + num);
        }
//       while (!isInterrupted()){
//
//       }
    }
}

class MyThread extends Thread {
    public void run() {
        Thread hello = new HelloThread();
        hello.start(); // 启动hello线程
        try {
            hello.join(); // 等待hello线程结束
        } catch (InterruptedException e) {
            System.out.println("interrupted!");
        }
        hello.interrupt();
    }
}

class HelloThread extends Thread {
    public void run() {
        int n = 0;
        while (!isInterrupted()) {
            n++;
            System.out.println(n + " hello!");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
