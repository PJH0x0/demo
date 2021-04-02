package org.pjh.jetpackdemo.model;

public class BaseResponseBody {
    @Override
    public String toString() {
        return "BaseResponseBody{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    protected String code;

    protected String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }



    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
