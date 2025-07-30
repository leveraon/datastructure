package com.leveraon.csfoundmental.datastructure.lists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Node<E> {
    private E element;
    private Node<E> left;
    private Node<E> right;
}
