package com.qfjy.project.weixin.bean.req;

import lombok.Data;

/**
 * 文本消息
 */
@Data
public class TextMessage extends BaseMessage{
    // 消息内容
    private String Content;

}
