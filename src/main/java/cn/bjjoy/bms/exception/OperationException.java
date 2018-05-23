package cn.bjjoy.bms.exception;

/**
 * @author bjjoy
 * @date 2017/11/15
 **/
public class OperationException extends Exception {

    private int code;

    private String msg;

    /**
     * 传入数据，保留现场
     */
    private Object data;

    public OperationException(int code, String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public OperationException(int code, String msg, Object data){
        super(msg);
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
