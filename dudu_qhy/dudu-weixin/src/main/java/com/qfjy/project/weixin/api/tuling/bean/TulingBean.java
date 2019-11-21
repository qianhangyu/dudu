package com.qfjy.project.weixin.api.tuling.bean;

import lombok.Data;

@Data
public class TulingBean {
    private int reqType=0;//输入类型:0-文本(默认)、1-图片、2-音频

    private  Perception perception;

    private UserInfo userInfo;
}
