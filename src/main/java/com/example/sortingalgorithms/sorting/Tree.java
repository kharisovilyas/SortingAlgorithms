package com.example.sortingalgorithms.sorting;

import java.util.ArrayList;
import java.util.List;

public class Tree <T extends  Comparable<T>> {
    private Node<T> root; // ссылка на корневой элемент дерева
    private int counter;

    /* метод для создания дерева с помощью
    коллекции имплементирующей интерфейс*/
    public  Tree<T> toCreateTreeFromList(List<T> listForCreate){
        Tree<T> tree = new Tree<>();
        for (var value : listForCreate){
            tree.addElementToTree(value);
        }
        return tree;
    }
    /*Метод для добавления элементов в дерево */
    public void addElementToTree(T data){
        if (root == null){
            root = new Node<>(data);
            counter = 1;
            return;
        }
        // рекурсивный выход
        root.addNewNode(data);
        counter++;
    }

    /*Инфиксный обход дерева*/
    public List<T> inorder (){
        if (root == null){
            return new ArrayList<>();
        }
        return inorder(root);
    }

    /*Инфиксный обход - реализация*/
    private List<T> inorder(Node<T> node){
        List<T> list = new ArrayList<>();

        if(node != null){
            /*рекурсивно обходим дерево*/
            if(node.getLeft() != null){
                list.addAll(inorder(node.getLeft()));
            }

            list.add(node.getData());

            if(node.getRight() != null){
                list.addAll(inorder(node.getRight()));
            }
        }

        return list;

    }

}
