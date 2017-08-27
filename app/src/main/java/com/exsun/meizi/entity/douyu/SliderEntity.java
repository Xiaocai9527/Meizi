package com.exsun.meizi.entity.douyu;

import java.util.List;

public class SliderEntity {
    private int id;
    private String pic_url;
    private RoomBean room;
    private String title;
    private String tv_pic_url;

    public static class RoomBean {
        private String cate_id;
        private CateLimitBean cate_limit;
        private List<CdnsWithNameBean> cdnsWithName;
        private String column_id;
        private String credit_illegal;
        private String cur_credit;
        private String fans;
        private String game_icon_url;
        private String game_name;
        private String game_url;
        private int isVertical;
        private int is_pass_player;
        private String is_white_list;
        private String low_credit;
        private String nickname;
        private int online;
        private int open_full_screen;
        private String owner_avatar;
        private String owner_uid;
        private String owner_weight;
        private String room_id;
        private String room_name;
        private String room_src;
        private String show_details;
        private String show_status;
        private String show_time;
        private String specific_catalog;
        private String specific_status;
        private String url;
        private String vertical_src;
        private String vod_quality;

        public static class CateLimitBean {
            private int limit_num;
            private int limit_threshold;
            private int limit_time;
            private int limit_type;

            public int getLimit_type() {
                return this.limit_type;
            }

            public void setLimit_type(int limit_type) {
                this.limit_type = limit_type;
            }

            public int getLimit_num() {
                return this.limit_num;
            }

            public void setLimit_num(int limit_num) {
                this.limit_num = limit_num;
            }

            public int getLimit_threshold() {
                return this.limit_threshold;
            }

            public void setLimit_threshold(int limit_threshold) {
                this.limit_threshold = limit_threshold;
            }

            public int getLimit_time() {
                return this.limit_time;
            }

            public void setLimit_time(int limit_time) {
                this.limit_time = limit_time;
            }
        }

        public static class CdnsWithNameBean {
            private String cdn;
            private String name;

            public String getName() {
                return this.name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCdn() {
                return this.cdn;
            }

            public void setCdn(String cdn) {
                this.cdn = cdn;
            }
        }

        public String getRoom_id() {
            return this.room_id;
        }

        public void setRoom_id(String room_id) {
            this.room_id = room_id;
        }

        public String getRoom_src() {
            return this.room_src;
        }

        public void setRoom_src(String room_src) {
            this.room_src = room_src;
        }

        public String getVertical_src() {
            return this.vertical_src;
        }

        public void setVertical_src(String vertical_src) {
            this.vertical_src = vertical_src;
        }

        public int getIsVertical() {
            return this.isVertical;
        }

        public void setIsVertical(int isVertical) {
            this.isVertical = isVertical;
        }

        public String getCate_id() {
            return this.cate_id;
        }

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
        }

        public String getRoom_name() {
            return this.room_name;
        }

        public void setRoom_name(String room_name) {
            this.room_name = room_name;
        }

        public String getVod_quality() {
            return this.vod_quality;
        }

        public void setVod_quality(String vod_quality) {
            this.vod_quality = vod_quality;
        }

        public String getShow_status() {
            return this.show_status;
        }

        public void setShow_status(String show_status) {
            this.show_status = show_status;
        }

        public String getShow_time() {
            return this.show_time;
        }

        public void setShow_time(String show_time) {
            this.show_time = show_time;
        }

        public String getOwner_uid() {
            return this.owner_uid;
        }

        public void setOwner_uid(String owner_uid) {
            this.owner_uid = owner_uid;
        }

        public String getSpecific_catalog() {
            return this.specific_catalog;
        }

        public void setSpecific_catalog(String specific_catalog) {
            this.specific_catalog = specific_catalog;
        }

        public String getSpecific_status() {
            return this.specific_status;
        }

        public void setSpecific_status(String specific_status) {
            this.specific_status = specific_status;
        }

        public String getCredit_illegal() {
            return this.credit_illegal;
        }

        public void setCredit_illegal(String credit_illegal) {
            this.credit_illegal = credit_illegal;
        }

        public String getIs_white_list() {
            return this.is_white_list;
        }

        public void setIs_white_list(String is_white_list) {
            this.is_white_list = is_white_list;
        }

        public String getCur_credit() {
            return this.cur_credit;
        }

        public void setCur_credit(String cur_credit) {
            this.cur_credit = cur_credit;
        }

        public String getLow_credit() {
            return this.low_credit;
        }

        public void setLow_credit(String low_credit) {
            this.low_credit = low_credit;
        }

        public int getOnline() {
            return this.online;
        }

        public void setOnline(int online) {
            this.online = online;
        }

        public String getNickname() {
            return this.nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getGame_url() {
            return this.game_url;
        }

        public void setGame_url(String game_url) {
            this.game_url = game_url;
        }

        public String getGame_name() {
            return this.game_name;
        }

        public void setGame_name(String game_name) {
            this.game_name = game_name;
        }

        public String getGame_icon_url() {
            return this.game_icon_url;
        }

        public void setGame_icon_url(String game_icon_url) {
            this.game_icon_url = game_icon_url;
        }

        public String getShow_details() {
            return this.show_details;
        }

        public void setShow_details(String show_details) {
            this.show_details = show_details;
        }

        public String getOwner_avatar() {
            return this.owner_avatar;
        }

        public void setOwner_avatar(String owner_avatar) {
            this.owner_avatar = owner_avatar;
        }

        public int getIs_pass_player() {
            return this.is_pass_player;
        }

        public void setIs_pass_player(int is_pass_player) {
            this.is_pass_player = is_pass_player;
        }

        public int getOpen_full_screen() {
            return this.open_full_screen;
        }

        public void setOpen_full_screen(int open_full_screen) {
            this.open_full_screen = open_full_screen;
        }

        public String getOwner_weight() {
            return this.owner_weight;
        }

        public void setOwner_weight(String owner_weight) {
            this.owner_weight = owner_weight;
        }

        public String getFans() {
            return this.fans;
        }

        public void setFans(String fans) {
            this.fans = fans;
        }

        public String getColumn_id() {
            return this.column_id;
        }

        public void setColumn_id(String column_id) {
            this.column_id = column_id;
        }

        public CateLimitBean getCate_limit() {
            return this.cate_limit;
        }

        public void setCate_limit(CateLimitBean cate_limit) {
            this.cate_limit = cate_limit;
        }

        public List<CdnsWithNameBean> getCdnsWithName() {
            return this.cdnsWithName;
        }

        public void setCdnsWithName(List<CdnsWithNameBean> cdnsWithName) {
            this.cdnsWithName = cdnsWithName;
        }
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic_url() {
        return this.pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getTv_pic_url() {
        return this.tv_pic_url;
    }

    public void setTv_pic_url(String tv_pic_url) {
        this.tv_pic_url = tv_pic_url;
    }

    public RoomBean getRoom() {
        return this.room;
    }

    public void setRoom(RoomBean room) {
        this.room = room;
    }
}
