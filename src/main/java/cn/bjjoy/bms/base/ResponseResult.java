package cn.bjjoy.bms.base;

/**
 * @author bjjoy
 * @date 2018/01/26
 */
public class ResponseResult {

    private Integer code;

    private String msg;

    private Object data;

    public ResponseResult(){

    }

    public ResponseResult(Integer code, String msg){
        this.code = code;
        this.msg = msg;
        this.data = "";
    }

    public ResponseResult(Integer code, String msg, Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseResult(Codes codes, Object data){
        this.code = codes.getCode();
        this.msg = codes.getMsg();
        this.data = data;
    }

    public ResponseResult(Codes codes){
        this.code = codes.getCode();
        this.msg = codes.getMsg();
        this.data = "";
    }

    public static ResponseResult ok(Object data){
        return new ResponseResult(Codes.OK, data);
    }

    public static ResponseResult error(Codes codes){
        return new ResponseResult(codes);
    }

    public static ResponseResult error(){
        return new ResponseResult(Codes.SYSTEM_ERROR);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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
