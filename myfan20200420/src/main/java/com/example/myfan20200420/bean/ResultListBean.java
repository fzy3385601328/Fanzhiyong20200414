package com.example.myfan20200420.bean;

import java.util.List;

public class ResultListBean {


    /**
     * result : [{"imageUrl":"http://mobile.bwstudent.com/images/small/banner/hzp.png","jumpUrl":"wd://commodity_list?arg=1001007005","rank":5,"title":"美妆工具"},{"imageUrl":"http://mobile.bwstudent.com/images/small/banner/lyq.png","jumpUrl":"wd://commodity_info?arg=83","rank":5,"title":"连衣裙"},{"imageUrl":"http://mobile.bwstudent.com/images/small/banner/px.png","jumpUrl":"wd://commodity_info?arg=165","rank":5,"title":"跑鞋"},{"imageUrl":"http://mobile.bwstudent.com/images/small/banner/wy.png","jumpUrl":"wd://commodity_list?arg=1001002004","rank":5,"title":"卫衣"}]
     * message : 查询成功
     * status : 0000
     */

    private List<ResultBean> result;
    private String message;
    private String status;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}
