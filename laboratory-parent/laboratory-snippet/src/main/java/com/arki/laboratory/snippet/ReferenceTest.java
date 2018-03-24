package com.arki.laboratory.snippet;

import com.arki.laboratory.common.Logger;
import org.junit.Test;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

public class ReferenceTest {

    @Test
    public void softReferenceTest() throws InterruptedException {
        Object o = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        SoftReference<Object> softReference = new SoftReference<>(o, referenceQueue);
        Logger.info(String.valueOf(softReference.get()));
        Logger.info(String.valueOf(referenceQueue.poll()));

        // 清除强引用,触发GC
        o = null;
        System.gc();

        Logger.info(String.valueOf(softReference.get()));

        Thread.sleep(200);
        Logger.info(String.valueOf(referenceQueue.poll()));
    }

    @Test
    public void test1() {
        MyQueue A = new MyQueue("A");
        MyQueue B = new MyQueue("B");
        A.setNext(B);
        B = null;
        Logger.info(String.valueOf(A));
        Logger.info(String.valueOf(B));
        MyQueue next = A.getNext();
        Logger.info(String.valueOf(next));
    }

    private class MyQueue {
        private String name;
        private MyQueue next;

        public MyQueue(String name) {
            this.name = name;
        }
        public MyQueue getNext() {
            return next;
        }

        public void setNext(MyQueue next) {
            this.next = next;
        }
        public String toString() {
            if(this.next==null) return this.name+"-->null";
            return this.name+"-->"+this.next.toString();
        }
    }
}
