package com.pdata.neo4j.utils;

public interface CsvProcessor<T> {
	public T process(T inData);
}