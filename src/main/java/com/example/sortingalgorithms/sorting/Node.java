package com.example.sortingalgorithms.sorting;

import java.util.Objects;

public class Node<T extends Comparable <T>> {
    private final int ZEROFORCOMPAREVALUES = 0; // ноль для сравнений в компараторе
    T data; // данные в узле
    Node<T> left; // левый потомок
    Node<T> right; // правый потомок

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?> node = (Node<?>) o;
        return Objects.equals(data, node.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public void addNewNode(T value){
        Node<T> node = new Node<>(value);

        if(node.data.compareTo(value) < ZEROFORCOMPAREVALUES){
            if (left == null){
                left = node;
            }else {
                left.addNewNode(value);
            }
        }
        else{
            if (right == null){
                right = node;
            }else {
                right.addNewNode(value);
            }
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
