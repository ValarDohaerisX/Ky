package com.akz.ky.message;

/**
 * @author lzx
 * @version 1.0
 * @date 2019/9/27 10:38
 */
public class ApiReturnCode extends AbstractReturnCode {


    public ApiReturnCode(int code,String desc){
        super(desc,code);
    }

    public ApiReturnCode(String desc,int code){
        super(desc,code);
    }

    public static final int C_Success_1 = 1000;
    public static final AbstractReturnCode SUCCESS = new ApiReturnCode("操作成功",C_Success_1);

    public static final int C_Fail_Insert_1 = 1001;
    public static final AbstractReturnCode C_Fail_Insert = new ApiReturnCode("添加失败",C_Fail_Insert_1);

    public static final int C_Fail_Repeat_1 = 1002;
    public static final AbstractReturnCode C_Fail_User_Repeat = new ApiReturnCode("查询重复信息",C_Fail_Repeat_1);

    public static final int C_Fail_Insert_Repeat_1 = 1003;
    public static final AbstractReturnCode C_Fail_Insert_Repeat = new ApiReturnCode("查询重复信息",C_Fail_Insert_Repeat_1);


    public static final int C_Fail_Get_1 = 1004;
    public static final AbstractReturnCode C_Fail_Get = new ApiReturnCode("查询失败",C_Fail_Get_1);

    public static final int C_Fail_Delete_1 = 1005;
    public static final AbstractReturnCode C_Fail_Delete = new ApiReturnCode("删除失败",C_Fail_Delete_1);

    public static final int C_Fail_Update_1 = 1006;
    public static final AbstractReturnCode C_Fail_Update = new ApiReturnCode("更新失败",C_Fail_Update_1);

    private ApiReturnCode(){

    }
}
