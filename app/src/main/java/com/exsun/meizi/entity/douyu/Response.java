package com.exsun.meizi.entity.douyu;

public class Response<T> {
    private T data;
    private int error;

    public void setData(T data) {
        this.data = data;
    }

    public void setError(int error) {
        this.error = error;
    }

    public int getError() {
        return this.error;
    }

    public T getData() {
        return this.data;
    }
}
