package com.qfjy.project.weixin.bean.req;

import lombok.Data;

/**
 * 图片消息
 */
@Data
public class ImageMessage extends BaseMessage {
	// 图片链接
	private String PicUrl;


}
