package top.hapleow.twins.common;

import lombok.Data;

/**
 * Controller返回结果
 *
 * @author wuyulin
 * @date 2020/9/28
 */
@Data
public class Result {


    private String message;

    private Object data;

    public static final Result SUCCESS = new Result("执行成功!", null);
    public static final Result FAILURE = new Result("执行失败!", null);


    public Result(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public static Result SUCCESS(Object data) {
        return new Result("执行成功！", data);
    }

}
