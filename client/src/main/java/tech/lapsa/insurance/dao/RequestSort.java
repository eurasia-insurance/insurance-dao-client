package tech.lapsa.insurance.dao;

import java.io.Serializable;

import tech.lapsa.java.commons.function.MyObjects;

public class RequestSort implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final RequestSort DEFAULT_SORT = new RequestSort(RequestSortField.CREATED, RequestSortOrder.DESC);

    public static enum RequestSortField {
	ID, CREATED, UPDATED, REQUESTER_NAME, REQUESTER_ID_NUMBER, REQUESTER_PHONE;
    }

    public static enum RequestSortOrder {
	ASC, DESC;
    }

    private final RequestSortField field;
    private final RequestSortOrder order;

    public RequestSort(final RequestSortField field, final RequestSortOrder order) throws IllegalArgumentException {
	this.field = MyObjects.requireNonNull(field, "field");
	this.order = MyObjects.requireNonNull(order, "order");
    }

    public RequestSortField getField() {
	return field;
    }

    public RequestSortOrder getOrder() {
	return order;
    }
}
