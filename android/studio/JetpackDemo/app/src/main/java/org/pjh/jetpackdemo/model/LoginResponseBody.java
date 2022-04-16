package org.pjh.jetpackdemo.model;

public class LoginResponseBody extends BaseResponseBody{
    private String skey;
    protected User data;

    public String getSkey() {
        return skey;
    }

    public void setSkey(String skey) {
        this.skey = skey;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return super.toString();
        /*return "LoginResponseBody{" +
                "skey='" + skey + '\'' +
                ", data=" + data +
                '}';*/
    }
}
