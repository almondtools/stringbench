package com.almondtools.stringbench;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.stringtemplate.v4.ST;

public class GenerateSinglePatternMatcherBenchmarkTest {

	public static void main(String[] args) throws IOException {
		generate(args[0], args[1]);
	}

	public static void generate(String pkg, String algorithm) throws IOException {
		ST st = new ST(new String(Files.readAllBytes(Paths.get("src/test/resources/TemplateSinglePatternMatcherBenchmarkTest.java")), StandardCharsets.UTF_8));
		st.add("pkg", pkg);
		st.add("algorithm", algorithm);
		String text = st.render();
		Path dir = Paths.get("src/test/java/com/almondtools/stringbench/singlepattern",pkg);
		Files.createDirectories(dir);
		Files.write(dir.resolve(algorithm + "BenchmarkTest.java"), text.getBytes(StandardCharsets.UTF_8));
	}
}
