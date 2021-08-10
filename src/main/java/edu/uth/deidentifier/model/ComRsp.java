package edu.uth.deidentifier.model;



public class ComRsp<T> {
    T data;
    String message;
    int code;

    public static ComRsp<Void> ok() {
        ComRsp<Void> b = new ComRsp();
        b.code = 200;
        b.message = "succeed";
        return b;
    }

    public static <T> ComRsp<T> ok(T body) {
        ComRsp<T> b = new ComRsp();
        b.code = 200;
        b.message = "succeed";
        b.data = body;
        return b;
    }

    public static <T> ComRsp<T> ok(T body, ErrorCode code) {
        ComRsp<T> b = new ComRsp();
        b.code = code.getCode();
        b.message = code.getMsg();
        b.data = body;
        return b;
    }

    public static <T> ComRsp<T> ok(T body, int code, String msg) {
        ComRsp<T> b = new ComRsp();
        b.code = code;
        b.message = msg;
        b.data = body;
        return b;
    }

    public static ComRsp<Void> fail() {
        ComRsp<Void> b = new ComRsp();
        b.code = 500;
        b.message = "failed";
        return b;
    }
}

