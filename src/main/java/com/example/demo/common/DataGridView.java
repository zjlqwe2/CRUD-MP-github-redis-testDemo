package com.example.demo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * json数据实体
 *      与前端交互数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataGridView {
	
	private Integer code=0;
	private String msg="";
	private Long count=0L;  //一共多少条数据
	private Object data;
	public DataGridView(Long count, Object data) {
		super();
		this.count = count;
		this.data = data;
	}
	public DataGridView(Object data) {
		super();
		this.data = data;
	}
}
