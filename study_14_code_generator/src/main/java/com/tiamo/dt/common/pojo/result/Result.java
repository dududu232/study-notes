package com.tiamo.dt.common.pojo.result;

import com.tiamo.dt.common.constants.ResultConstant;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一接口返回数据格式
 */
@Data
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 返回代码
	 */
	private Integer code;

	/**
	 * 返回处理消息
	 */
	private String message;

	/**
	 * 返回数据对象 data
	 */
	private T data;

	public Result() { }

	/**
	 * 成功
	 * @return Result对象
	 */
	public static Result success() {
		Result r = new Result();
		r.setCode(ResultConstant.SUCCESS_200);
		r.setMessage("操作成功");
		return r;
	}

	/**
	 * 成功
	 * @param message 消息
	 * @return Result对象
	 */
	public static Result success(String message) {
		Result r = new Result();
		r.setCode(ResultConstant.SUCCESS_200);
		r.setMessage(message);
		return r;
	}

	/**
	 * 成功
	 * @param data data数据
	 * @return Result对象
	 */
	public static Result successData(Object data) {
		Result r = new Result();
		r.setCode(ResultConstant.SUCCESS_200);
		r.setData(data);
		return r;
	}

	/**
	 * 成功
	 * @param msg	消息
	 * @param data	data数据
	 * @return Result对象
	 */
	public static Result successData(String msg, Object data) {
		Result r = new Result();
		r.setCode(ResultConstant.SUCCESS_200);
		r.setMessage(msg);
		r.setData(data);
		return r;
	}

	/**
	 * 失败
	 * @return Result对象
	 */
	public static Result error() {
		return error(ResultConstant.SERVER_ERROR_500, "操作失败，请重试！");
	}

	/**
	 * 失败
	 * @param msg 消息
	 * @return Result对象
	 */
	public static Result error(String msg) {
		return error(ResultConstant.SERVER_ERROR_500, msg);
	}

	/**
	 * 失败
	 * @param code	自定义code
	 * @param msg	消息
	 * @return Result对象
	 */
	public static Result error(int code, String msg) {
		Result r = new Result();
		r.setCode(code);
		r.setMessage(msg);
		return r;
	}

	/**
	 * 失败
	 * @param msg 消息
	 * @param data	data数据
	 * @return Result对象
	 */
	public static Result errorData(String msg, Object data) {
		Result r = new Result();
		r.setCode(ResultConstant.SERVER_ERROR_500);
		r.setMessage(msg);
		r.setData(data);
		return r;
	}

	/**
	 * 无权限访问返回结果
	 * @param msg 消息
	 * @return	无权限访问返回结果
	 */
	public static Result unAuth(String msg) {
		return error(ResultConstant.NO_AUTH_401, msg);
	}

//	/**
//	 * 增加返回数据对象
//	 * @param k
//	 * @param v
//	 * @return
//	 */
//	public Result addData(String k, Object v) {
//		if(this.data == null){
//			this.data = new HashMap<>();
//		}
//
//		((Map)this.data).put(k, v);
//		return this;
//	}

	/**
	 * 返回成功或失败
	 * @param flag	成功失败状态值
	 * @return Result对象
	 */
	public static Result status(boolean flag){
		return flag ? success() : error();
	}


}
