package com.almondtools.stringbench.analyzer;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BenchmarkRecords {
	
	private List<BenchmarkRecord> records;
	private Map<String, Date> latest;
	
	public BenchmarkRecords() {
		this.records = new ArrayList<>();
		this.latest = new HashMap<>();
	}

	public void add(BenchmarkRecord record) {
		String name = record.getAlgorithm();
		Date date = record.getDate();
		Date last = latest.get(name);
		if (last == null || last.before(date)) {
			latest.put(name, date);
			records.add(record);
			records.removeIf(element -> element.getDate().before(date));
		} else if (last.equals(date)) {
			records.add(record);
		}
	}
	
	public List<BenchmarkRecord> getRecords() {
		return records;
	}

}
