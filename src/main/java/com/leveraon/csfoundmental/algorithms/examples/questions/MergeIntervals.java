package com.leveraon.csfoundmental.algorithms.examples.questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
class Interval {
	int start;
	int end;
}

public class MergeIntervals {

	List<Interval> mergeIntervals(List<Interval> intervals) {

		if (intervals == null || intervals.isEmpty())
			return Collections.emptyList();

		List<Interval> sortedIntervals = sortIntervals(intervals);

		Map<Integer, Integer> result = new HashMap<>();

		int start = 0;
		int end = 0;
		for (Interval interval : sortedIntervals) {
			int currentStart = interval.start;
			int currentEnd = interval.end;
			if (result.isEmpty()) {
				start = currentStart;
				end = currentEnd;
				result.put(currentStart, currentEnd);
			}

			if (currentStart >= start && currentStart <= end) {
				end = Math.max(currentEnd, end);
				result.put(currentStart, end);
			} else {
				start = currentStart;
				end = currentEnd;
				result.put(currentStart, currentEnd);
			}

		}
		List<Interval> finalResult = new ArrayList<>();
		result.forEach((key, value) -> {
			finalResult.add(Interval.builder().start(key).end(value).build());
		});

		return finalResult;
	}

	List<Interval> sortIntervals(List<Interval> intervals) {
		for (int i = 1; i < intervals.size(); i++) {
			Interval current = intervals.get(i);

			int j = i;
			while (j > 0 && intervals.get(j - 1).start > current.start) {
				intervals.set(j, intervals.get(j - 1));
				j--;
			}
			intervals.set(j, current);
		}

		return intervals;
	}
}
