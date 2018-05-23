package cn.bjjoy.bms.base;

public enum Codes {
    OK(200, "成功"),
    SYSTEM_ERROR(600, "系统错误"),
    PARAM_ERROR(601, "参数错误"),
    ROLE_IS_USED(602, "该角色仍被使用"),
    MENU_IS_USED(603, "该资源仍被使用");

    private int code;
    private String msg;

    Codes(int code, String msg){
        this.code = code;
        this.msg = msg;
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
}
