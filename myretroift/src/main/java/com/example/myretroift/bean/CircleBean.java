package com.example.myretroift.bean;

import java.util.List;

public class CircleBean {


    /**
     * result : [{"commodityId":1,"content":"??????????","createTime":1587596940000,"greatNum":0,"headPic":"http://mobile.bwstudent.com/images/small/head_pic/2020-04-22/20200422180859.jpg","id":3340,"image":"http://mobile.bwstudent.com/images/small/circle_pic/2020-04-22/8542120200422180900.jpg","nickName":"罗哈哈","userId":47693,"whetherGreat":2},{"commodityId":1,"content":"??????????","createTime":1587596729000,"greatNum":0,"headPic":"http://mobile.bwstudent.com/images/small/head_pic/2020-04-22/20200422180859.jpg","id":3339,"image":"http://mobile.bwstudent.com/images/small/circle_pic/2020-04-22/3714520200422180529.jpg","nickName":"罗哈哈","userId":47693,"whetherGreat":2},{"commodityId":1,"content":"??????????","createTime":1587596177000,"greatNum":0,"headPic":"http://mobile.bwstudent.com/images/small/head_pic/2020-04-22/20200422180859.jpg","id":3338,"image":"http://mobile.bwstudent.com/images/small/circle_pic/2020-04-22/1764920200422175617.jpg","nickName":"罗哈哈","userId":47693,"whetherGreat":2},{"commodityId":1,"content":"??????????","createTime":1587595471000,"greatNum":0,"headPic":"http://mobile.bwstudent.com/images/small/head_pic/2020-04-22/20200422180859.jpg","id":3337,"image":"http://mobile.bwstudent.com/images/small/circle_pic/2020-04-22/0386920200422174431.jpg","nickName":"罗哈哈","userId":47693,"whetherGreat":2},{"commodityId":1,"content":"??????????","createTime":1587595206000,"greatNum":0,"headPic":"http://mobile.bwstudent.com/images/small/head_pic/2020-04-22/20200422180859.jpg","id":3336,"image":"http://mobile.bwstudent.com/images/small/circle_pic/2020-04-22/7608920200422174006.jpg","nickName":"罗哈哈","userId":47693,"whetherGreat":2}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * commodityId : 1
         * content : ??????????
         * createTime : 1587596940000
         * greatNum : 0
         * headPic : http://mobile.bwstudent.com/images/small/head_pic/2020-04-22/20200422180859.jpg
         * id : 3340
         * image : http://mobile.bwstudent.com/images/small/circle_pic/2020-04-22/8542120200422180900.jpg
         * nickName : 罗哈哈
         * userId : 47693
         * whetherGreat : 2
         */

        private int commodityId;
        private String content;
        private long createTime;
        private int greatNum;
        private String headPic;
        private int id;
        private String image;
        private String nickName;
        private int userId;
        private int whetherGreat;

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getGreatNum() {
            return greatNum;
        }

        public void setGreatNum(int greatNum) {
            this.greatNum = greatNum;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getWhetherGreat() {
            return whetherGreat;
        }

        public void setWhetherGreat(int whetherGreat) {
            this.whetherGreat = whetherGreat;
        }
    }
}
