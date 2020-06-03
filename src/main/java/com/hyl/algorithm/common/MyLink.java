package com.hyl.algorithm.common;

import lombok.Data;

/**
 * 链表
 *
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-06-03 05:32
 */
public class MyLink {

    private Node head;
    private int size;

    public MyLink() {
        head = null;
        size = 0;
    }

    public void add(int i) {
        Node node = new Node();
        node.setData(i);
        if (head == null) {
            head = node;
        } else {

            if (head.getNext()==null){
                head.setNext(node);
            }else {
                Node next = head.getNext();
                while (true){
                    if (next.getNext()==null){
                        next.setNext(node);
                        break;
                    }else {
                        next = next.getNext();
                    }
                }
            }
        }
        size++;
    }

    public void print(){
        if (size==0){
            System.out.println();
        }
        System.out.print(head.getData()+"\t");
        Node next = head.getNext();
        while (next!=null){
            System.out.print(next.getData()+"\t");
            next = next.getNext();
        }

    }

    public int getSize() {
        return size;
    }

    public static void main(String[] args) {
        MyLink myLink = new MyLink();
        myLink.add(1);
        myLink.add(2);
        myLink.add(3);
        myLink.add(5);
        myLink.print();
    }

    @Data
    public static class Node {
        private int data;
        private Node next;
    }
}
