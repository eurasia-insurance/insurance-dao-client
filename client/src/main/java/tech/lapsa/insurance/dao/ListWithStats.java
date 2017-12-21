package tech.lapsa.insurance.dao;

import java.io.Serializable;
import java.util.List;

import tech.lapsa.java.commons.function.MyCollections;

public final class ListWithStats<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private final List<T> part;

    private final int from;
    private final int count;
    private final long totalCount;
    private final Double totalAmount;

    public ListWithStats(List<T> part, int from, int count, long totalCount, double totalAmount) {
	this.part = part;
	this.from = from;
	this.count = count;
	this.totalCount = totalCount;
	this.totalAmount = Double.valueOf(totalAmount);
    }

    public ListWithStats(List<T> part, int from, int count, long totalCount) {
	this.part = part;
	this.from = from;
	this.count = count;
	this.totalCount = totalCount;
	this.totalAmount = null;
    }

    public List<T> getPart() {
	return MyCollections.unmodifiableOrEmptyList(part);
    }

    public int getFrom() {
	return from;
    }

    public int getCount() {
	return count;
    }

    public long getTotalCount() {
	return totalCount;
    }

    public double getTotalAmount() {
	return totalAmount;
    }
}
