package com.leveraon.csfoundmental.datastructure.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode<E> {
	private E element;
	private TreeNode<E> left;
	private TreeNode<E> right;
}
