package com.exsun.meizi.entity.douyu;

import java.util.List;

public class SearchEntity {
    private int count;
    private List<RoomBean> room;

    public static class RoomBean {
        private String avatar;
        private String avatar_mid;
        private String avatar_small;
        private String cate_id;
        private String child_id;
        private String fans;
        private String game_name;
        private String game_url;
        private IconDataBean icon_data;
        private int isVertical;
        private String jumpUrl;
        private String nickname;
        private int online;
        private String owner_uid;
        private int ranktype;
        private String room_id;
        private String room_name;
        private String room_src;
        private String show_status;
        private String show_time;
        private String specific_catalog;
        private String specific_status;
        private String subject;
        private String url;
        private String vertical_src;
        private String vod_quality;

        public static class IconDataBean {
            private int icon_height;
            private String icon_url;
            private int icon_width;
            private int status;

            public int getStatus() {
                return this.status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getIcon_url() {
                return this.icon_url;
            }

            public void setIcon_url(String icon_url) {
                this.icon_url = icon_url;
            }

            public int getIcon_width() {
                return this.icon_width;
            }

            public void setIcon_width(int icon_width) {
                this.icon_width = icon_width;
            }

            public int getIcon_height() {
                return this.icon_height;
            }

            public void setIcon_height(int icon_height) {
                this.icon_height = icon_height;
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

        public String getShow_status() {
            return this.show_status;
        }

        public void setShow_status(String show_status) {
            this.show_status = show_status;
        }

        public String getSubject() {
            return this.subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
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

        public String getVod_quality() {
            return this.vod_quality;
        }

        public void setVod_quality(String vod_quality) {
            this.vod_quality = vod_quality;
        }

        public String getNickname() {
            return this.nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getOnline() {
            return this.online;
        }

        public void setOnline(int online) {
            this.online = online;
        }

        public String getChild_id() {
            return this.child_id;
        }

        public void setChild_id(String child_id) {
            this.child_id = child_id;
        }

        public String getAvatar() {
            return this.avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getAvatar_mid() {
            return this.avatar_mid;
        }

        public void setAvatar_mid(String avatar_mid) {
            this.avatar_mid = avatar_mid;
        }

        public String getAvatar_small() {
            return this.avatar_small;
        }

        public void setAvatar_small(String avatar_small) {
            this.avatar_small = avatar_small;
        }

        public String getJumpUrl() {
            return this.jumpUrl;
        }

        public void setJumpUrl(String jumpUrl) {
            this.jumpUrl = jumpUrl;
        }

        public IconDataBean getIcon_data() {
            return this.icon_data;
        }

        public void setIcon_data(IconDataBean icon_data) {
            this.icon_data = icon_data;
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

        public String getFans() {
            return this.fans;
        }

        public void setFans(String fans) {
            this.fans = fans;
        }

        public int getRanktype() {
            return this.ranktype;
        }

        public void setRanktype(int ranktype) {
            this.ranktype = ranktype;
        }
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<RoomBean> getRoom() {
        return this.room;
    }

    public void setRoom(List<RoomBean> room) {
        this.room = room;
    }
}
