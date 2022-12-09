package com.example.nedemo.recyclerview;



public class CrashItem {
    private String crashType;
    private String crashDesc;
    private String crashSuggest;

    public CrashItem(){}
    public CrashItem(String crashType, String crashDesc) {
        this.crashType = crashType;
        this.crashDesc = crashDesc;
    }

    public void setCrashType(String crashType) {
        this.crashType = crashType;
    }
    public String getCrashType() {
        return crashType;
    }
    public void setCrashDesc(String crashDesc) {
        this.crashDesc = crashDesc;
    }
    public String getCrashDesc() {
        return crashDesc;
    }
    public void setCrashSuggest(String crashSuggest) {
        this.crashSuggest = crashSuggest;
    }
    public String getCrashSuggest() {
        return crashSuggest;
    }

}
