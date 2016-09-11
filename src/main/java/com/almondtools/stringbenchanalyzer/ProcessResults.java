package com.almondtools.stringbenchanalyzer;

import java.io.IOException;

public class ProcessResults {

	public static void main(String[] args) throws IOException {
		if (args.length == 1) {
			String file = args[0];
			GenerateOverview generateOverview = new GenerateOverview(file);
			generateOverview.run();
		} else if (args.length == 4) {
			String file = args[0];
			int number = Integer.parseInt(args[1]);
			int alphabet = Integer.parseInt(args[2]);
			int pattern = Integer.parseInt(args[3]);
			GenerateDetail generateDetail = new GenerateDetail(file, number, alphabet, pattern);
			generateDetail.run();
		}
	}

}
