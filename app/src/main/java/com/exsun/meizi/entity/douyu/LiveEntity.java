package com.exsun.meizi.entity.douyu;

public class LiveEntity {
    private GgadBean ggad;
    private int inNA;
    private int rateSwitch;
    private String room_id;
    private String rtmp_cdn;
    private String rtmp_live;
    private String rtmp_url;

    public static class GgadBean {
        private String play1;
        private String play2;
        private String play3;
        private String videop;

        public String getVideop() {
            return this.videop;
        }

        public void setVideop(String videop) {
            this.videop = videop;
        }

        public String getPlay2() {
            return this.play2;
        }

        public void setPlay2(String play2) {
            this.play2 = play2;
        }

        public String getPlay3() {
            return this.play3;
        }

        public void setPlay3(String play3) {
            this.play3 = play3;
        }

        public String getPlay1() {
            return this.play1;
        }

        public void setPlay1(String play1) {
            this.play1 = play1;
        }
    }

    public int getInNA() {
        return this.inNA;
    }

    public void setInNA(int inNA) {
        this.inNA = inNA;
    }

    public String getRoom_id() {
        return this.room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getRtmp_cdn() {
        return this.rtmp_cdn;
    }

    public void setRtmp_cdn(String rtmp_cdn) {
        this.rtmp_cdn = rtmp_cdn;
    }

    public String getRtmp_live() {
        return this.rtmp_live;
    }

    public void setRtmp_live(String rtmp_live) {
        this.rtmp_live = rtmp_live;
    }

    public String getRtmp_url() {
        return this.rtmp_url;
    }

    public void setRtmp_url(String rtmp_url) {
        this.rtmp_url = rtmp_url;
    }

    public int getRateSwitch() {
        return this.rateSwitch;
    }

    public void setRateSwitch(int rateSwitch) {
        this.rateSwitch = rateSwitch;
    }

    public GgadBean getGgad() {
        return this.ggad;
    }

    public void setGgad(GgadBean ggad) {
        this.ggad = ggad;
    }
}
