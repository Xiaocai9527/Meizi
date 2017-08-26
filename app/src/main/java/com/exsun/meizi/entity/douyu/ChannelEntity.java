package com.exsun.meizi.entity.douyu;

public class ChannelEntity {
    private String cate_id;
    private String game_icon;
    private String game_name;
    private String game_src;
    private String game_url;
    private String online_room;
    private String online_room_ios;
    private String short_name;

    public String getCate_id() {
        return this.cate_id;
    }

    public void setCate_id(String cate_id) {
        this.cate_id = cate_id;
    }

    public String getGame_name() {
        return this.game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getShort_name() {
        return this.short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getGame_url() {
        return this.game_url;
    }

    public void setGame_url(String game_url) {
        this.game_url = game_url;
    }

    public String getGame_src() {
        return this.game_src;
    }

    public void setGame_src(String game_src) {
        this.game_src = game_src;
    }

    public String getGame_icon() {
        return this.game_icon;
    }

    public void setGame_icon(String game_icon) {
        this.game_icon = game_icon;
    }

    public String getOnline_room() {
        return this.online_room;
    }

    public void setOnline_room(String online_room) {
        this.online_room = online_room;
    }

    public String getOnline_room_ios() {
        return this.online_room_ios;
    }

    public void setOnline_room_ios(String online_room_ios) {
        this.online_room_ios = online_room_ios;
    }
}
