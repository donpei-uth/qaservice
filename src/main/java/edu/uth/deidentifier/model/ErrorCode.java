package edu.uth.deidentifier.model;

public enum ErrorCode {

    PARAM_ERR(100001, "param error, please check input  "),
    PARAM_CONVERT_ERR(100001, "data convert error, please check input  "),

    UNCATCH_ERR(999998, " uncaught error."),
    UNKNOW_ERR(999999, " i had a trouble, please contact the administrator !"),


    ;

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public static ErrorCode getErrorCode(int code) {
        for (ErrorCode err : ErrorCode.values()) {
            if (err.code == code) {
                return err;
            }
        }
        return UNKNOW_ERR;
    }


}

