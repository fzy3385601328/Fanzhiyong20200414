package com.example.myfan20200420.bean;

public class ResultBean {

    /**
     * imageUrl : http://mobile.bwstudent.com/images/small/banner/hzp.png
     * jumpUrl : wd://commodity_list?arg=1001007005
     * rank : 5
     * title : 美妆工具
     */

    private String imageUrl;
    private String jumpUrl;
    private int rank;
    private String title;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
