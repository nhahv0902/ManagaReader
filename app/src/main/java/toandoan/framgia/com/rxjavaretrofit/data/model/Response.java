package toandoan.framgia.com.rxjavaretrofit.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by toand on 6/19/2017.
 */

public class Response<T> {
    @Expose
    @SerializedName("apiname")
    private String mApiName;
    @Expose
    @SerializedName("status")
    private String mStatus;
    @Expose
    @SerializedName("code")
    private int mCode;
    @Expose
    @SerializedName("content")
    private T mContent;

    public String getApiName() {
        return mApiName;
    }

    public void setApiName(String apiName) {
        mApiName = apiName;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public int getCode() {
        return mCode;
    }

    public void setCode(int code) {
        mCode = code;
    }

    public T getContent() {
        return mContent;
    }

    public void setContent(T content) {
        mContent = content;
    }
}
