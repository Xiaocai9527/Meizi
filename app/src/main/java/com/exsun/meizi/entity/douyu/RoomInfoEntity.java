package com.exsun.meizi.entity.douyu;

import java.util.List;

public class RoomInfoEntity {
    private List<?> black;
    private String cate_id;
    private CateLimitBean cate_limit;
    private List<String> cdns;
    private List<CdnsWithNameBean> cdnsWithName;
    private String column_id;
    private String coverSrc;
    private String credit_illegal;
    private String cur_credit;
    private String defaultSrc;
    private String game_icon_url;
    private String game_name;
    private String game_url;
    private int isDefaultAvatar;
    private int isVertical;
    private int is_high_game;
    private int is_pass_player;
    private String is_white_list;
    private String low_credit;
    private List<MultiratesBean> multirates;
    private String nickname;
    private int online;
    private int open_full_screen;
    private String owner_avatar;
    private String owner_uid;
    private P2pSettingsBean p2p_settings;
    private int room_dm_delay;
    private String room_id;
    private RoomIdleBean room_idle;
    private String room_name;
    private String room_src;
    private String rtmp_cdn;
    private String rtmp_live;
    private String rtmp_url;
    private List<ServersBean> servers;
    private String show_details;
    private String show_status;
    private String show_time;
    private String specific_catalog;
    private String specific_status;
    private String url;
    private String use_p2p;
    private String vertical_src;
    private String vod_quality;
    private List<WsproxyBean> wsproxy;

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

    public static class MultiratesBean {
        private String name;
        private int type;

        public int getType() {
            return this.type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class P2pSettingsBean {
        private int m_dm;
        private int name_id;
        private int online_limit;
        private int plan_id;
        private int player;
        private int w_dm;

        public int getPlan_id() {
            return this.plan_id;
        }

        public void setPlan_id(int plan_id) {
            this.plan_id = plan_id;
        }

        public int getW_dm() {
            return this.w_dm;
        }

        public void setW_dm(int w_dm) {
            this.w_dm = w_dm;
        }

        public int getM_dm() {
            return this.m_dm;
        }

        public void setM_dm(int m_dm) {
            this.m_dm = m_dm;
        }

        public int getPlayer() {
            return this.player;
        }

        public void setPlayer(int player) {
            this.player = player;
        }

        public int getName_id() {
            return this.name_id;
        }

        public void setName_id(int name_id) {
            this.name_id = name_id;
        }

        public int getOnline_limit() {
            return this.online_limit;
        }

        public void setOnline_limit(int online_limit) {
            this.online_limit = online_limit;
        }
    }

    public static class RoomIdleBean {
        private int active;
        private int minute_limit;

        public int getActive() {
            return this.active;
        }

        public void setActive(int active) {
            this.active = active;
        }

        public int getMinute_limit() {
            return this.minute_limit;
        }

        public void setMinute_limit(int minute_limit) {
            this.minute_limit = minute_limit;
        }
    }

    public static class ServersBean {
        private String ip;
        private String port;

        public String getIp() {
            return this.ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getPort() {
            return this.port;
        }

        public void setPort(String port) {
            this.port = port;
        }
    }

    public static class WsproxyBean {
        private String domain;
        private String port;

        public String getDomain() {
            return this.domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }

        public String getPort() {
            return this.port;
        }

        public void setPort(String port) {
            this.port = port;
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

    public String getRtmp_url() {
        return this.rtmp_url;
    }

    public void setRtmp_url(String rtmp_url) {
        this.rtmp_url = rtmp_url;
    }

    public String getRtmp_live() {
        return this.rtmp_live;
    }

    public void setRtmp_live(String rtmp_live) {
        this.rtmp_live = rtmp_live;
    }

    public String getRtmp_cdn() {
        return this.rtmp_cdn;
    }

    public void setRtmp_cdn(String rtmp_cdn) {
        this.rtmp_cdn = rtmp_cdn;
    }

    public String getUse_p2p() {
        return this.use_p2p;
    }

    public void setUse_p2p(String use_p2p) {
        this.use_p2p = use_p2p;
    }

    public int getRoom_dm_delay() {
        return this.room_dm_delay;
    }

    public void setRoom_dm_delay(int room_dm_delay) {
        this.room_dm_delay = room_dm_delay;
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

    public int getIs_high_game() {
        return this.is_high_game;
    }

    public void setIs_high_game(int is_high_game) {
        this.is_high_game = is_high_game;
    }

    public RoomIdleBean getRoom_idle() {
        return this.room_idle;
    }

    public void setRoom_idle(RoomIdleBean room_idle) {
        this.room_idle = room_idle;
    }

    public String getCoverSrc() {
        return this.coverSrc;
    }

    public void setCoverSrc(String coverSrc) {
        this.coverSrc = coverSrc;
    }

    public String getDefaultSrc() {
        return this.defaultSrc;
    }

    public void setDefaultSrc(String defaultSrc) {
        this.defaultSrc = defaultSrc;
    }

    public P2pSettingsBean getP2p_settings() {
        return this.p2p_settings;
    }

    public void setP2p_settings(P2pSettingsBean p2p_settings) {
        this.p2p_settings = p2p_settings;
    }

    public int getIsDefaultAvatar() {
        return this.isDefaultAvatar;
    }

    public void setIsDefaultAvatar(int isDefaultAvatar) {
        this.isDefaultAvatar = isDefaultAvatar;
    }

    public List<ServersBean> getServers() {
        return this.servers;
    }

    public void setServers(List<ServersBean> servers) {
        this.servers = servers;
    }

    public List<?> getBlack() {
        return this.black;
    }

    public void setBlack(List<?> black) {
        this.black = black;
    }

    public List<String> getCdns() {
        return this.cdns;
    }

    public void setCdns(List<String> cdns) {
        this.cdns = cdns;
    }

    public List<CdnsWithNameBean> getCdnsWithName() {
        return this.cdnsWithName;
    }

    public void setCdnsWithName(List<CdnsWithNameBean> cdnsWithName) {
        this.cdnsWithName = cdnsWithName;
    }

    public List<MultiratesBean> getMultirates() {
        return this.multirates;
    }

    public void setMultirates(List<MultiratesBean> multirates) {
        this.multirates = multirates;
    }

    public List<WsproxyBean> getWsproxy() {
        return this.wsproxy;
    }

    public void setWsproxy(List<WsproxyBean> wsproxy) {
        this.wsproxy = wsproxy;
    }
}
