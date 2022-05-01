package com.example.pagination.model;

public class ApiResponse<T> {
    int totalRecords;
    T data;

    public ApiResponse() {
    }

    public ApiResponse(int totalRecords, T data) {
        this.totalRecords = totalRecords;
        this.data = data;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
