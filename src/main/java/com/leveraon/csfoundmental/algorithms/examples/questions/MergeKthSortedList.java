package com.leveraon.csfoundmental.algorithms.examples.questions;

import java.util.Arrays;

public class MergeKthSortedList {

	// Wrong
//	int[] mergeKthList(int[][] lists) {
//		int[] result = {};
//
//		if (lists == null)
//			return null;
//
//		int head = 0;
//		int tail = 0;
//
//		for (int[] list : lists) {
//			if (result.length == 0) {
//				result = Arrays.copyOf(list, list.length);
//				head = list[0];
//				tail = list[list.length - 1];
//			} else {
//				int currentHead = list[0];
//				int currentTail = list[1];
//
//				// list greatest element is less or equal to head, move list to front. update
//				// head
//				if (currentTail <= head) {
//					result = addBeforeOrAfter(list, result);
//					head = currentHead;
//					continue;
//				}
//
//				// list smallest element is greater or equal to tail, move list to end. update
//				// tail
//				if (currentHead >= tail) {
//					result = addBeforeOrAfter(result, list);
//					tail = currentTail;
//					continue;
//				}
//
//				// list head in between current head and tail range. insert
//				result = addInBetween(result, list);
//
//			}
//		}
//
//		return result;
//	}
//
//	int[] addInBetween(int[] source, int[] target) {
//		if (source == null)
//			return null;
//
//		if (target == null)
//			return source;
//
//		int[] result = {};
//		int j = 0;
//		for (int i = 0; i < source.length; i++) {
//			result[i] = source[i];
//			while (j < target.length) {
//				if (target[j] >= result[i]) {
//					result[i + j] = target[j];
//					j++;
//				} else {
//					int temp = result[i];
//					result[i] = target[j];
//					result[i + j] = temp;
//					i++;
//				}
//			}
//		}
//
//		return result;
//	}
//
//	int[] addBeforeOrAfter(int[] source, int[] target) {
//		if (source == null) {
//			return target;
//		}
//
//		if (target == null) {
//			return source;
//		}
//
//		int[] newArray = {};
//
//		int index = 0;
//		for (int i = 0; i < source.length; i++) {
//			newArray[i] = source[i];
//			index++;
//		}
//		for (int i = 0; i < target.length; i++) {
//			newArray[index] = target[i];
//			index++;
//		}
//
//		return newArray;
//	}

}