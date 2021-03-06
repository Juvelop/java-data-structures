package com.example.demo;

import com.googlecode.concurrenttrees.common.CharSequences;
import com.googlecode.concurrenttrees.common.Iterables;
import com.googlecode.concurrenttrees.common.PrettyPrinter;
import com.googlecode.concurrenttrees.radix.node.concrete.DefaultCharArrayNodeFactory;
import com.googlecode.concurrenttrees.radix.node.util.PrettyPrintable;
import com.googlecode.concurrenttrees.suffix.ConcurrentSuffixTree;
import com.googlecode.concurrenttrees.suffix.SuffixTree;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.StreamSupport;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		SuffixTree<Integer> tree = new ConcurrentSuffixTree<Integer>(new DefaultCharArrayNodeFactory());

		tree.put("springboot", 1);
		tree.put("springcloud", 2);
		tree.put("springmvc", 3);
		tree.put("sports", 77);

		PrettyPrinter.prettyPrint((PrettyPrintable) tree, System.out);

		//Exact 매칭
		System.out.println("#1. Exact 매칭 테스트");
		System.out.println("springboot (exact match): " + tree.getValueForExactKey("springboot"));
		System.out.println("springcloud (exact match): " + tree.getValueForExactKey("springcloud"));
		System.out.println("springmvc (exact match): " + tree.getValueForExactKey("springmvc"));
		System.out.println("sports (exact match): " + tree.getValueForExactKey("sports"));


		//Reverse 매칭
		System.out.println("#2. Reverse Suffix 매칭 테스트");
		System.out.println("(reverse suffix match): ");
		StreamSupport.stream(tree.getKeyValuePairsForKeysEndingWith("boot").spliterator(), false)
				.forEach(System.out::println);


		/*
		getKeysContaining
		getValuesForKeysContaining
		getKeyValuePairsForKeysContaining
		 */
		System.out.println("#3. getKeyValuePairsForKeysContaining");
		StreamSupport.stream(tree.getKeyValuePairsForKeysContaining("ring").spliterator(), false)
				.forEach(System.out::println);

	}
}
