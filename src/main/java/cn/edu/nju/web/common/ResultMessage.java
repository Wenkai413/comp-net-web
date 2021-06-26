package cn.edu.nju.web.common;

public class ResultMessage<T> {
    private final ServiceStatusCode code;
    private final T data;

    public ResultMessage(ServiceStatusCode code, T data) {
        this.code = code;
        this.data = data;
    }

    public ServiceStatusCode getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

}
