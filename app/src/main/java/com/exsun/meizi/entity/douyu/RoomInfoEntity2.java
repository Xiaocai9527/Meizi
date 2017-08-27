package com.exsun.meizi.entity.douyu;

import java.util.List;

public class RoomInfoEntity2 {
    private String anchor_city;
    private List<?> black;
    private String cate_id;
    private int cate_id1;
    private List<String> cdns;
    private List<CdnsWithNameBean> cdnsWithName;
    private String credit_illegal;
    private String cur_credit;
    private String fans;
    private String game_icon_url;
    private String game_name;
    private String game_url;
    private GgadBean ggad;
    private List<GiftBean> gift;
    private String gift_ver;
    private String hls_url;
    private int isVertical;
    private int is_pass_player;
    private String is_white_list;
    private String low_credit;
    private String nickname;
    private int online;
    private String owner_avatar;
    private String owner_uid;
    private String owner_weight;
    private P2pSettingsBean p2p_settings;
    private int room_dm_delay;
    private int room_id;
    private String room_name;
    private String room_src;
    private String rtmp_cdn;
    private String rtmp_live;
    private RtmpMultiBitrateBean rtmp_multi_bitrate;
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

    public static class GgadBean {
        private String play1;
        private String play2;
        private String play3;
        private String play4;
        private String play5;
        private String videop;

        public String getPlay4() {
            return this.play4;
        }

        public void setPlay4(String play4) {
            this.play4 = play4;
        }

        public String getPlay1() {
            return this.play1;
        }

        public void setPlay1(String play1) {
            this.play1 = play1;
        }

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

        public String getPlay5() {
            return this.play5;
        }

        public void setPlay5(String play5) {
            this.play5 = play5;
        }

        public String getPlay3() {
            return this.play3;
        }

        public void setPlay3(String play3) {
            this.play3 = play3;
        }
    }

    public static class GiftBean {
        private String big_effect_icon;
        private String brgb;
        private String ch;
        private String cimg;
        private String desc;
        private String drgb;
        private int ef;
        private String effect;
        private String grgb;
        private String gt;
        private int gx;
        private String himg;
        private String id;
        private String intro;
        private String m_ef_gif_1;
        private String m_ef_gif_2;
        private String mgif;
        private String mimg;
        private String mobile_big_effect_icon_0;
        private String mobile_big_effect_icon_1;
        private String mobile_big_effect_icon_2;
        private String mobile_big_effect_icon_3;
        private String mobile_icon_v2;
        private String mobile_small_effect_icon;
        private String mobile_stay_time;
        private String mobimg;
        private String name;
        private String pad_big_effect_icon;
        private String pc;
        private String pdbimg;
        private String pdhimg;
        private String pimg;
        private String pt;
        private String small_effect_icon;
        private String sort;
        private int stay_time;
        private String type;
        private String urgb;

        public int getStay_time() {
            return this.stay_time;
        }

        public void setStay_time(int stay_time) {
            this.stay_time = stay_time;
        }

        public String getPdhimg() {
            return this.pdhimg;
        }

        public void setPdhimg(String pdhimg) {
            this.pdhimg = pdhimg;
        }

        public int getGx() {
            return this.gx;
        }

        public void setGx(int gx) {
            this.gx = gx;
        }

        public String getMobile_big_effect_icon_0() {
            return this.mobile_big_effect_icon_0;
        }

        public void setMobile_big_effect_icon_0(String mobile_big_effect_icon_0) {
            this.mobile_big_effect_icon_0 = mobile_big_effect_icon_0;
        }

        public String getMgif() {
            return this.mgif;
        }

        public void setMgif(String mgif) {
            this.mgif = mgif;
        }

        public String getCimg() {
            return this.cimg;
        }

        public void setCimg(String cimg) {
            this.cimg = cimg;
        }

        public String getMobile_big_effect_icon_1() {
            return this.mobile_big_effect_icon_1;
        }

        public void setMobile_big_effect_icon_1(String mobile_big_effect_icon_1) {
            this.mobile_big_effect_icon_1 = mobile_big_effect_icon_1;
        }

        public String getBig_effect_icon() {
            return this.big_effect_icon;
        }

        public void setBig_effect_icon(String big_effect_icon) {
            this.big_effect_icon = big_effect_icon;
        }

        public String getPdbimg() {
            return this.pdbimg;
        }

        public void setPdbimg(String pdbimg) {
            this.pdbimg = pdbimg;
        }

        public String getMobile_stay_time() {
            return this.mobile_stay_time;
        }

        public void setMobile_stay_time(String mobile_stay_time) {
            this.mobile_stay_time = mobile_stay_time;
        }

        public String getMimg() {
            return this.mimg;
        }

        public void setMimg(String mimg) {
            this.mimg = mimg;
        }

        public String getBrgb() {
            return this.brgb;
        }

        public void setBrgb(String brgb) {
            this.brgb = brgb;
        }

        public String getMobile_big_effect_icon_3() {
            return this.mobile_big_effect_icon_3;
        }

        public void setMobile_big_effect_icon_3(String mobile_big_effect_icon_3) {
            this.mobile_big_effect_icon_3 = mobile_big_effect_icon_3;
        }

        public String getM_ef_gif_2() {
            return this.m_ef_gif_2;
        }

        public void setM_ef_gif_2(String m_ef_gif_2) {
            this.m_ef_gif_2 = m_ef_gif_2;
        }

        public String getPimg() {
            return this.pimg;
        }

        public void setPimg(String pimg) {
            this.pimg = pimg;
        }

        public String getPt() {
            return this.pt;
        }

        public void setPt(String pt) {
            this.pt = pt;
        }

        public String getId() {
            return this.id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIntro() {
            return this.intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getPc() {
            return this.pc;
        }

        public void setPc(String pc) {
            this.pc = pc;
        }

        public String getM_ef_gif_1() {
            return this.m_ef_gif_1;
        }

        public void setM_ef_gif_1(String m_ef_gif_1) {
            this.m_ef_gif_1 = m_ef_gif_1;
        }

        public String getUrgb() {
            return this.urgb;
        }

        public void setUrgb(String urgb) {
            this.urgb = urgb;
        }

        public int getEf() {
            return this.ef;
        }

        public void setEf(int ef) {
            this.ef = ef;
        }

        public String getSort() {
            return this.sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getMobile_big_effect_icon_2() {
            return this.mobile_big_effect_icon_2;
        }

        public void setMobile_big_effect_icon_2(String mobile_big_effect_icon_2) {
            this.mobile_big_effect_icon_2 = mobile_big_effect_icon_2;
        }

        public String getCh() {
            return this.ch;
        }

        public void setCh(String ch) {
            this.ch = ch;
        }

        public String getEffect() {
            return this.effect;
        }

        public void setEffect(String effect) {
            this.effect = effect;
        }

        public String getHimg() {
            return this.himg;
        }

        public void setHimg(String himg) {
            this.himg = himg;
        }

        public String getType() {
            return this.type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getGt() {
            return this.gt;
        }

        public void setGt(String gt) {
            this.gt = gt;
        }

        public String getMobile_small_effect_icon() {
            return this.mobile_small_effect_icon;
        }

        public void setMobile_small_effect_icon(String mobile_small_effect_icon) {
            this.mobile_small_effect_icon = mobile_small_effect_icon;
        }

        public String getGrgb() {
            return this.grgb;
        }

        public void setGrgb(String grgb) {
            this.grgb = grgb;
        }

        public String getDrgb() {
            return this.drgb;
        }

        public void setDrgb(String drgb) {
            this.drgb = drgb;
        }

        public String getPad_big_effect_icon() {
            return this.pad_big_effect_icon;
        }

        public void setPad_big_effect_icon(String pad_big_effect_icon) {
            this.pad_big_effect_icon = pad_big_effect_icon;
        }

        public String getDesc() {
            return this.desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getMobimg() {
            return this.mobimg;
        }

        public void setMobimg(String mobimg) {
            this.mobimg = mobimg;
        }

        public String getSmall_effect_icon() {
            return this.small_effect_icon;
        }

        public void setSmall_effect_icon(String small_effect_icon) {
            this.small_effect_icon = small_effect_icon;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile_icon_v2() {
            return this.mobile_icon_v2;
        }

        public void setMobile_icon_v2(String mobile_icon_v2) {
            this.mobile_icon_v2 = mobile_icon_v2;
        }
    }

    public static class P2pSettingsBean {
        private int m_dm;
        private int player;
        private int use_p2p;
        private int w_dm;

        public int getPlayer() {
            return this.player;
        }

        public void setPlayer(int player) {
            this.player = player;
        }

        public int getUse_p2p() {
            return this.use_p2p;
        }

        public void setUse_p2p(int use_p2p) {
            this.use_p2p = use_p2p;
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
    }

    public static class RtmpMultiBitrateBean {
        private String middle;
        private String middle2;

        public String getMiddle() {
            return this.middle;
        }

        public void setMiddle(String middle) {
            this.middle = middle;
        }

        public String getMiddle2() {
            return this.middle2;
        }

        public void setMiddle2(String middle2) {
            this.middle2 = middle2;
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

    public String getUse_p2p() {
        return this.use_p2p;
    }

    public void setUse_p2p(String use_p2p) {
        this.use_p2p = use_p2p;
    }

    public String getShow_details() {
        return this.show_details;
    }

    public void setShow_details(String show_details) {
        this.show_details = show_details;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRtmp_url() {
        return this.rtmp_url;
    }

    public void setRtmp_url(String rtmp_url) {
        this.rtmp_url = rtmp_url;
    }

    public GgadBean getGgad() {
        return this.ggad;
    }

    public void setGgad(GgadBean ggad) {
        this.ggad = ggad;
    }

    public String getAnchor_city() {
        return this.anchor_city;
    }

    public void setAnchor_city(String anchor_city) {
        this.anchor_city = anchor_city;
    }

    public String getSpecific_status() {
        return this.specific_status;
    }

    public void setSpecific_status(String specific_status) {
        this.specific_status = specific_status;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRtmp_cdn() {
        return this.rtmp_cdn;
    }

    public void setRtmp_cdn(String rtmp_cdn) {
        this.rtmp_cdn = rtmp_cdn;
    }

    public String getSpecific_catalog() {
        return this.specific_catalog;
    }

    public void setSpecific_catalog(String specific_catalog) {
        this.specific_catalog = specific_catalog;
    }

    public int getCate_id1() {
        return this.cate_id1;
    }

    public void setCate_id1(int cate_id1) {
        this.cate_id1 = cate_id1;
    }

    public String getShow_status() {
        return this.show_status;
    }

    public void setShow_status(String show_status) {
        this.show_status = show_status;
    }

    public String getGame_icon_url() {
        return this.game_icon_url;
    }

    public void setGame_icon_url(String game_icon_url) {
        this.game_icon_url = game_icon_url;
    }

    public String getGame_name() {
        return this.game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public P2pSettingsBean getP2p_settings() {
        return this.p2p_settings;
    }

    public void setP2p_settings(P2pSettingsBean p2p_settings) {
        this.p2p_settings = p2p_settings;
    }

    public String getShow_time() {
        return this.show_time;
    }

    public void setShow_time(String show_time) {
        this.show_time = show_time;
    }

    public int getIsVertical() {
        return this.isVertical;
    }

    public void setIsVertical(int isVertical) {
        this.isVertical = isVertical;
    }

    public String getRtmp_live() {
        return this.rtmp_live;
    }

    public void setRtmp_live(String rtmp_live) {
        this.rtmp_live = rtmp_live;
    }

    public String getFans() {
        return this.fans;
    }

    public void setFans(String fans) {
        this.fans = fans;
    }

    public String getGame_url() {
        return this.game_url;
    }

    public void setGame_url(String game_url) {
        this.game_url = game_url;
    }

    public String getRoom_src() {
        return this.room_src;
    }

    public void setRoom_src(String room_src) {
        this.room_src = room_src;
    }

    public String getIs_white_list() {
        return this.is_white_list;
    }

    public void setIs_white_list(String is_white_list) {
        this.is_white_list = is_white_list;
    }

    public String getRoom_name() {
        return this.room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public String getOwner_uid() {
        return this.owner_uid;
    }

    public void setOwner_uid(String owner_uid) {
        this.owner_uid = owner_uid;
    }

    public String getOwner_avatar() {
        return this.owner_avatar;
    }

    public void setOwner_avatar(String owner_avatar) {
        this.owner_avatar = owner_avatar;
    }

    public String getVertical_src() {
        return this.vertical_src;
    }

    public void setVertical_src(String vertical_src) {
        this.vertical_src = vertical_src;
    }

    public int getRoom_dm_delay() {
        return this.room_dm_delay;
    }

    public void setRoom_dm_delay(int room_dm_delay) {
        this.room_dm_delay = room_dm_delay;
    }

    public String getOwner_weight() {
        return this.owner_weight;
    }

    public void setOwner_weight(String owner_weight) {
        this.owner_weight = owner_weight;
    }

    public int getIs_pass_player() {
        return this.is_pass_player;
    }

    public void setIs_pass_player(int is_pass_player) {
        this.is_pass_player = is_pass_player;
    }

    public String getHls_url() {
        return this.hls_url;
    }

    public void setHls_url(String hls_url) {
        this.hls_url = hls_url;
    }

    public int getRoom_id() {
        return this.room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getCur_credit() {
        return this.cur_credit;
    }

    public void setCur_credit(String cur_credit) {
        this.cur_credit = cur_credit;
    }

    public String getGift_ver() {
        return this.gift_ver;
    }

    public void setGift_ver(String gift_ver) {
        this.gift_ver = gift_ver;
    }

    public String getLow_credit() {
        return this.low_credit;
    }

    public void setLow_credit(String low_credit) {
        this.low_credit = low_credit;
    }

    public RtmpMultiBitrateBean getRtmp_multi_bitrate() {
        return this.rtmp_multi_bitrate;
    }

    public void setRtmp_multi_bitrate(RtmpMultiBitrateBean rtmp_multi_bitrate) {
        this.rtmp_multi_bitrate = rtmp_multi_bitrate;
    }

    public int getOnline() {
        return this.online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public String getCredit_illegal() {
        return this.credit_illegal;
    }

    public void setCredit_illegal(String credit_illegal) {
        this.credit_illegal = credit_illegal;
    }

    public String getVod_quality() {
        return this.vod_quality;
    }

    public void setVod_quality(String vod_quality) {
        this.vod_quality = vod_quality;
    }

    public String getCate_id() {
        return this.cate_id;
    }

    public void setCate_id(String cate_id) {
        this.cate_id = cate_id;
    }

    public List<ServersBean> getServers() {
        return this.servers;
    }

    public void setServers(List<ServersBean> servers) {
        this.servers = servers;
    }

    public List<CdnsWithNameBean> getCdnsWithName() {
        return this.cdnsWithName;
    }

    public void setCdnsWithName(List<CdnsWithNameBean> cdnsWithName) {
        this.cdnsWithName = cdnsWithName;
    }

    public List<?> getBlack() {
        return this.black;
    }

    public void setBlack(List<?> black) {
        this.black = black;
    }

    public List<GiftBean> getGift() {
        return this.gift;
    }

    public void setGift(List<GiftBean> gift) {
        this.gift = gift;
    }

    public List<String> getCdns() {
        return this.cdns;
    }

    public void setCdns(List<String> cdns) {
        this.cdns = cdns;
    }
}
