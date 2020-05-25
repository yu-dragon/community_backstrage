package com.yulong.common.utils.result;

import com.alibaba.fastjson.JSONObject;

public class BuildResult {

    /**
     * 构造成功的返回结果
     * @param cardId
     * @param nickName
     * @return
     */
    public static String buildSuccessRes(Integer id, String cardId, String nickName, Integer code) {
        JSONObject result = new JSONObject();
        result.put("code", code);
        result.put("message", "success");
        if (id != null) {
            result.put("id", id);
        }
        if (cardId != null) {
            result.put("card_id", cardId);
        }
        result.put("nick_name", nickName);
        return result.toString();
    }

    /**
     * 构造基础的成功返回结果
     * @param message
     * @return
     */
    public static String buildSuccessBase(String message, Integer code) {
        JSONObject result = new JSONObject();
        result.put("code", code);
        result.put("message", message);
        return result.toString();
    }

    /**
     * 构造失败的返回结果
     * @param message
     * @return
     */
    public static String buildFailRes(String message, Integer code) {
        JSONObject result = new JSONObject();
        result.put("code", code);
        result.put("message", message);
        return result.toString();
    }

    /**
     * 构造异常的返回结果
     * @param message
     * @return
     */
    public static String buildExceptionRes(String message, Integer code) {
        JSONObject result = new JSONObject();
        result.put("code", code);
        result.put("message", message);
        return result.toString();
    }

    /**
     * 构造没有权限的返回结果
     * @param message
     * @return
     */
    public static String buildNoRights(String message, Integer code) {
        JSONObject result = new JSONObject();
        result.put("code", code);
        result.put("message", message);
        return result.toString();
    }

    /**
     * 携带参数的成功返回结果
     * @param message
     * @param code
     * @param data
     * @return
     */
    public static String buildSuccessData(String message, Integer code, JSONObject data) {
        JSONObject result = new JSONObject();
        result.put("code", code);
        result.put("message", message);
        result.put("result", data);
        return result.toString();
    }

}
