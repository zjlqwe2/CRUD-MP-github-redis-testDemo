package com.example.demo.vo;

import com.example.demo.domain.Zhao_UserDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserVo extends Zhao_UserDO {

	private Integer page=1;
	private Integer limit=5;


	private Integer offset;

}
