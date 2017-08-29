package com.exsun.meizi.entity.douyu;

import java.util.List;

/**
 * Created by xiaokun on 2017/8/29.
 */

public class RoomInfoEntity2
{

    /**
     * error : 0
     * data : {"room_id":"2127419","room_src":"https://rpic.douyucdn.cn/acrpic/170829/2127419_1508.jpg","vertical_src":"https://rpic.douyucdn.cn/acrpic/170829/2127419_1508.jpg","isVertical":0,"cate_id":"181","room_name":"九日：虽千万人，吾往矣。","vod_quality":"0","show_status":"1","show_time":"1503985940","owner_uid":"139272795","specific_catalog":"","specific_status":"0","credit_illegal":"0","is_white_list":"0","cur_credit":"12","low_credit":"4","online":539227,"nickname":"九日98号技师","url":"/2127419","game_url":"/directory/game/wzry","game_name":"王者荣耀","game_icon_url":"https://staticlive.douyucdn.cn/upload/game_cate/f719087663581b7a723c4d39f8721bc1.jpg","rtmp_url":"http://hdla.douyucdn.cn/live","rtmp_live":"","rtmp_cdn":"ws","rtmp_multi_bitrate":{"middle":"_550","middle2":"_900"},"servers":[{"ip":"119.90.49.110","port":"8046"},{"ip":"119.90.49.94","port":"8068"},{"ip":"119.90.49.94","port":"8067"},{"ip":"119.90.49.102","port":"8009"},{"ip":"119.90.49.104","port":"8016"},{"ip":"119.90.49.103","port":"8014"},{"ip":"119.90.49.106","port":"8026"},{"ip":"119.90.49.107","port":"8035"},{"ip":"119.90.49.110","port":"8047"},{"ip":"119.90.49.92","port":"8056"},{"ip":"119.90.49.107","port":"8034"},{"ip":"119.90.49.93","port":"8063"},{"ip":"119.90.49.101","port":"8005"},{"ip":"119.90.49.109","port":"8041"},{"ip":"119.90.49.102","port":"8008"},{"ip":"119.90.49.103","port":"8011"},{"ip":"119.90.49.104","port":"8017"},{"ip":"119.90.49.106","port":"8027"},{"ip":"119.90.49.104","port":"8020"},{"ip":"119.90.49.109","port":"8042"},{"ip":"119.90.49.110","port":"8049"},{"ip":"119.90.49.86","port":"8077"},{"ip":"119.90.49.93","port":"8062"},{"ip":"119.90.49.102","port":"8007"},{"ip":"119.90.49.109","port":"8045"},{"ip":"119.90.49.91","port":"8052"},{"ip":"119.90.49.94","port":"8066"},{"ip":"119.90.49.101","port":"8001"},{"ip":"119.90.49.105","port":"8025"},{"ip":"119.90.49.108","port":"8036"}],"use_p2p":"0","room_dm_delay":90,"black":[],"show_details":"直播时间下午14点    王主任的拆迁办4群：659249453 150个5级符文免费打 群：545726850 合作邮箱：3786976@qq.com","owner_avatar":"https://apic.douyucdn.cn/upload/avanew/face/201708/24/21/3e1f801247236ca6fe68cf580fa6044c_big.jpg","cdns":["ws","tct","ws2"],"cdnsWithName":[{"name":"主线路","cdn":"ws"},{"name":"备用线路5","cdn":"tct"},{"name":"备用线路2","cdn":"ws2"}],"is_pass_player":0,"open_full_screen":0,"multirates":[{"type":0,"name":"超清"},{"type":2,"name":"高清"},{"type":1,"name":"普清"}],"column_id":"14","cate_limit":{"limit_type":0,"limit_num":0,"limit_threshold":0,"limit_time":0},"is_high_game":0,"room_idle":{"active":1,"minute_limit":40},"coverSrc":"","defaultSrc":"https://staticlive.douyucdn.cn/common/douyu/images/dbg2.jpg","p2p_settings":{"plan_id":0,"name_id":0,"w_dm":0,"m_dm":0,"player":0,"online_limit":0},"isDefaultAvatar":0,"wsproxy":[{"domain":"dy2.8686c.com","port":"10010"},{"domain":"dy2.8686c.com","port":"10011"},{"domain":"dy2.8686c.com","port":"10012"},{"domain":"dy2.8686c.com","port":"10013"},{"domain":"dy2.8686c.com","port":"10014"}]}
     */

    private int error;
    private DataBean data;

    public int getError()
    {
        return error;
    }

    public void setError(int error)
    {
        this.error = error;
    }

    public DataBean getData()
    {
        return data;
    }

    public void setData(DataBean data)
    {
        this.data = data;
    }

    public static class DataBean
    {
        /**
         * room_id : 2127419
         * room_src : https://rpic.douyucdn.cn/acrpic/170829/2127419_1508.jpg
         * vertical_src : https://rpic.douyucdn.cn/acrpic/170829/2127419_1508.jpg
         * isVertical : 0
         * cate_id : 181
         * room_name : 九日：虽千万人，吾往矣。
         * vod_quality : 0
         * show_status : 1
         * show_time : 1503985940
         * owner_uid : 139272795
         * specific_catalog :
         * specific_status : 0
         * credit_illegal : 0
         * is_white_list : 0
         * cur_credit : 12
         * low_credit : 4
         * online : 539227
         * nickname : 九日98号技师
         * url : /2127419
         * game_url : /directory/game/wzry
         * game_name : 王者荣耀
         * game_icon_url : https://staticlive.douyucdn.cn/upload/game_cate/f719087663581b7a723c4d39f8721bc1.jpg
         * rtmp_url : http://hdla.douyucdn.cn/live
         * rtmp_live :
         * rtmp_cdn : ws
         * rtmp_multi_bitrate : {"middle":"_550","middle2":"_900"}
         * servers : [{"ip":"119.90.49.110","port":"8046"},{"ip":"119.90.49.94","port":"8068"},{"ip":"119.90.49.94","port":"8067"},{"ip":"119.90.49.102","port":"8009"},{"ip":"119.90.49.104","port":"8016"},{"ip":"119.90.49.103","port":"8014"},{"ip":"119.90.49.106","port":"8026"},{"ip":"119.90.49.107","port":"8035"},{"ip":"119.90.49.110","port":"8047"},{"ip":"119.90.49.92","port":"8056"},{"ip":"119.90.49.107","port":"8034"},{"ip":"119.90.49.93","port":"8063"},{"ip":"119.90.49.101","port":"8005"},{"ip":"119.90.49.109","port":"8041"},{"ip":"119.90.49.102","port":"8008"},{"ip":"119.90.49.103","port":"8011"},{"ip":"119.90.49.104","port":"8017"},{"ip":"119.90.49.106","port":"8027"},{"ip":"119.90.49.104","port":"8020"},{"ip":"119.90.49.109","port":"8042"},{"ip":"119.90.49.110","port":"8049"},{"ip":"119.90.49.86","port":"8077"},{"ip":"119.90.49.93","port":"8062"},{"ip":"119.90.49.102","port":"8007"},{"ip":"119.90.49.109","port":"8045"},{"ip":"119.90.49.91","port":"8052"},{"ip":"119.90.49.94","port":"8066"},{"ip":"119.90.49.101","port":"8001"},{"ip":"119.90.49.105","port":"8025"},{"ip":"119.90.49.108","port":"8036"}]
         * use_p2p : 0
         * room_dm_delay : 90
         * black : []
         * show_details : 直播时间下午14点    王主任的拆迁办4群：659249453 150个5级符文免费打 群：545726850 合作邮箱：3786976@qq.com
         * owner_avatar : https://apic.douyucdn.cn/upload/avanew/face/201708/24/21/3e1f801247236ca6fe68cf580fa6044c_big.jpg
         * cdns : ["ws","tct","ws2"]
         * cdnsWithName : [{"name":"主线路","cdn":"ws"},{"name":"备用线路5","cdn":"tct"},{"name":"备用线路2","cdn":"ws2"}]
         * is_pass_player : 0
         * open_full_screen : 0
         * multirates : [{"type":0,"name":"超清"},{"type":2,"name":"高清"},{"type":1,"name":"普清"}]
         * column_id : 14
         * cate_limit : {"limit_type":0,"limit_num":0,"limit_threshold":0,"limit_time":0}
         * is_high_game : 0
         * room_idle : {"active":1,"minute_limit":40}
         * coverSrc :
         * defaultSrc : https://staticlive.douyucdn.cn/common/douyu/images/dbg2.jpg
         * p2p_settings : {"plan_id":0,"name_id":0,"w_dm":0,"m_dm":0,"player":0,"online_limit":0}
         * isDefaultAvatar : 0
         * wsproxy : [{"domain":"dy2.8686c.com","port":"10010"},{"domain":"dy2.8686c.com","port":"10011"},{"domain":"dy2.8686c.com","port":"10012"},{"domain":"dy2.8686c.com","port":"10013"},{"domain":"dy2.8686c.com","port":"10014"}]
         */

        private String room_id;
        private String room_src;
        private String vertical_src;
        private int isVertical;
        private String cate_id;
        private String room_name;
        private String vod_quality;
        private String show_status;
        private String show_time;
        private String owner_uid;
        private String specific_catalog;
        private String specific_status;
        private String credit_illegal;
        private String is_white_list;
        private String cur_credit;
        private String low_credit;
        private int online;
        private String nickname;
        private String url;
        private String game_url;
        private String game_name;
        private String game_icon_url;
        private String rtmp_url;
        private String rtmp_live;
        private String rtmp_cdn;
        private RtmpMultiBitrateBean rtmp_multi_bitrate;
        private String use_p2p;
        private int room_dm_delay;
        private String show_details;
        private String owner_avatar;
        private int is_pass_player;
        private int open_full_screen;
        private String column_id;
        private CateLimitBean cate_limit;
        private int is_high_game;
        private RoomIdleBean room_idle;
        private String coverSrc;
        private String defaultSrc;
        private P2pSettingsBean p2p_settings;
        private int isDefaultAvatar;
        private List<ServersBean> servers;
        private List<?> black;
        private List<String> cdns;
        private List<CdnsWithNameBean> cdnsWithName;
        private List<MultiratesBean> multirates;
        private List<WsproxyBean> wsproxy;

        public String getRoom_id()
        {
            return room_id;
        }

        public void setRoom_id(String room_id)
        {
            this.room_id = room_id;
        }

        public String getRoom_src()
        {
            return room_src;
        }

        public void setRoom_src(String room_src)
        {
            this.room_src = room_src;
        }

        public String getVertical_src()
        {
            return vertical_src;
        }

        public void setVertical_src(String vertical_src)
        {
            this.vertical_src = vertical_src;
        }

        public int getIsVertical()
        {
            return isVertical;
        }

        public void setIsVertical(int isVertical)
        {
            this.isVertical = isVertical;
        }

        public String getCate_id()
        {
            return cate_id;
        }

        public void setCate_id(String cate_id)
        {
            this.cate_id = cate_id;
        }

        public String getRoom_name()
        {
            return room_name;
        }

        public void setRoom_name(String room_name)
        {
            this.room_name = room_name;
        }

        public String getVod_quality()
        {
            return vod_quality;
        }

        public void setVod_quality(String vod_quality)
        {
            this.vod_quality = vod_quality;
        }

        public String getShow_status()
        {
            return show_status;
        }

        public void setShow_status(String show_status)
        {
            this.show_status = show_status;
        }

        public String getShow_time()
        {
            return show_time;
        }

        public void setShow_time(String show_time)
        {
            this.show_time = show_time;
        }

        public String getOwner_uid()
        {
            return owner_uid;
        }

        public void setOwner_uid(String owner_uid)
        {
            this.owner_uid = owner_uid;
        }

        public String getSpecific_catalog()
        {
            return specific_catalog;
        }

        public void setSpecific_catalog(String specific_catalog)
        {
            this.specific_catalog = specific_catalog;
        }

        public String getSpecific_status()
        {
            return specific_status;
        }

        public void setSpecific_status(String specific_status)
        {
            this.specific_status = specific_status;
        }

        public String getCredit_illegal()
        {
            return credit_illegal;
        }

        public void setCredit_illegal(String credit_illegal)
        {
            this.credit_illegal = credit_illegal;
        }

        public String getIs_white_list()
        {
            return is_white_list;
        }

        public void setIs_white_list(String is_white_list)
        {
            this.is_white_list = is_white_list;
        }

        public String getCur_credit()
        {
            return cur_credit;
        }

        public void setCur_credit(String cur_credit)
        {
            this.cur_credit = cur_credit;
        }

        public String getLow_credit()
        {
            return low_credit;
        }

        public void setLow_credit(String low_credit)
        {
            this.low_credit = low_credit;
        }

        public int getOnline()
        {
            return online;
        }

        public void setOnline(int online)
        {
            this.online = online;
        }

        public String getNickname()
        {
            return nickname;
        }

        public void setNickname(String nickname)
        {
            this.nickname = nickname;
        }

        public String getUrl()
        {
            return url;
        }

        public void setUrl(String url)
        {
            this.url = url;
        }

        public String getGame_url()
        {
            return game_url;
        }

        public void setGame_url(String game_url)
        {
            this.game_url = game_url;
        }

        public String getGame_name()
        {
            return game_name;
        }

        public void setGame_name(String game_name)
        {
            this.game_name = game_name;
        }

        public String getGame_icon_url()
        {
            return game_icon_url;
        }

        public void setGame_icon_url(String game_icon_url)
        {
            this.game_icon_url = game_icon_url;
        }

        public String getRtmp_url()
        {
            return rtmp_url;
        }

        public void setRtmp_url(String rtmp_url)
        {
            this.rtmp_url = rtmp_url;
        }

        public String getRtmp_live()
        {
            return rtmp_live;
        }

        public void setRtmp_live(String rtmp_live)
        {
            this.rtmp_live = rtmp_live;
        }

        public String getRtmp_cdn()
        {
            return rtmp_cdn;
        }

        public void setRtmp_cdn(String rtmp_cdn)
        {
            this.rtmp_cdn = rtmp_cdn;
        }

        public RtmpMultiBitrateBean getRtmp_multi_bitrate()
        {
            return rtmp_multi_bitrate;
        }

        public void setRtmp_multi_bitrate(RtmpMultiBitrateBean rtmp_multi_bitrate)
        {
            this.rtmp_multi_bitrate = rtmp_multi_bitrate;
        }

        public String getUse_p2p()
        {
            return use_p2p;
        }

        public void setUse_p2p(String use_p2p)
        {
            this.use_p2p = use_p2p;
        }

        public int getRoom_dm_delay()
        {
            return room_dm_delay;
        }

        public void setRoom_dm_delay(int room_dm_delay)
        {
            this.room_dm_delay = room_dm_delay;
        }

        public String getShow_details()
        {
            return show_details;
        }

        public void setShow_details(String show_details)
        {
            this.show_details = show_details;
        }

        public String getOwner_avatar()
        {
            return owner_avatar;
        }

        public void setOwner_avatar(String owner_avatar)
        {
            this.owner_avatar = owner_avatar;
        }

        public int getIs_pass_player()
        {
            return is_pass_player;
        }

        public void setIs_pass_player(int is_pass_player)
        {
            this.is_pass_player = is_pass_player;
        }

        public int getOpen_full_screen()
        {
            return open_full_screen;
        }

        public void setOpen_full_screen(int open_full_screen)
        {
            this.open_full_screen = open_full_screen;
        }

        public String getColumn_id()
        {
            return column_id;
        }

        public void setColumn_id(String column_id)
        {
            this.column_id = column_id;
        }

        public CateLimitBean getCate_limit()
        {
            return cate_limit;
        }

        public void setCate_limit(CateLimitBean cate_limit)
        {
            this.cate_limit = cate_limit;
        }

        public int getIs_high_game()
        {
            return is_high_game;
        }

        public void setIs_high_game(int is_high_game)
        {
            this.is_high_game = is_high_game;
        }

        public RoomIdleBean getRoom_idle()
        {
            return room_idle;
        }

        public void setRoom_idle(RoomIdleBean room_idle)
        {
            this.room_idle = room_idle;
        }

        public String getCoverSrc()
        {
            return coverSrc;
        }

        public void setCoverSrc(String coverSrc)
        {
            this.coverSrc = coverSrc;
        }

        public String getDefaultSrc()
        {
            return defaultSrc;
        }

        public void setDefaultSrc(String defaultSrc)
        {
            this.defaultSrc = defaultSrc;
        }

        public P2pSettingsBean getP2p_settings()
        {
            return p2p_settings;
        }

        public void setP2p_settings(P2pSettingsBean p2p_settings)
        {
            this.p2p_settings = p2p_settings;
        }

        public int getIsDefaultAvatar()
        {
            return isDefaultAvatar;
        }

        public void setIsDefaultAvatar(int isDefaultAvatar)
        {
            this.isDefaultAvatar = isDefaultAvatar;
        }

        public List<ServersBean> getServers()
        {
            return servers;
        }

        public void setServers(List<ServersBean> servers)
        {
            this.servers = servers;
        }

        public List<?> getBlack()
        {
            return black;
        }

        public void setBlack(List<?> black)
        {
            this.black = black;
        }

        public List<String> getCdns()
        {
            return cdns;
        }

        public void setCdns(List<String> cdns)
        {
            this.cdns = cdns;
        }

        public List<CdnsWithNameBean> getCdnsWithName()
        {
            return cdnsWithName;
        }

        public void setCdnsWithName(List<CdnsWithNameBean> cdnsWithName)
        {
            this.cdnsWithName = cdnsWithName;
        }

        public List<MultiratesBean> getMultirates()
        {
            return multirates;
        }

        public void setMultirates(List<MultiratesBean> multirates)
        {
            this.multirates = multirates;
        }

        public List<WsproxyBean> getWsproxy()
        {
            return wsproxy;
        }

        public void setWsproxy(List<WsproxyBean> wsproxy)
        {
            this.wsproxy = wsproxy;
        }

        public static class RtmpMultiBitrateBean
        {
            /**
             * middle : _550
             * middle2 : _900
             */

            private String middle;
            private String middle2;

            public String getMiddle()
            {
                return middle;
            }

            public void setMiddle(String middle)
            {
                this.middle = middle;
            }

            public String getMiddle2()
            {
                return middle2;
            }

            public void setMiddle2(String middle2)
            {
                this.middle2 = middle2;
            }
        }

        public static class CateLimitBean
        {
            /**
             * limit_type : 0
             * limit_num : 0
             * limit_threshold : 0
             * limit_time : 0
             */

            private int limit_type;
            private int limit_num;
            private int limit_threshold;
            private int limit_time;

            public int getLimit_type()
            {
                return limit_type;
            }

            public void setLimit_type(int limit_type)
            {
                this.limit_type = limit_type;
            }

            public int getLimit_num()
            {
                return limit_num;
            }

            public void setLimit_num(int limit_num)
            {
                this.limit_num = limit_num;
            }

            public int getLimit_threshold()
            {
                return limit_threshold;
            }

            public void setLimit_threshold(int limit_threshold)
            {
                this.limit_threshold = limit_threshold;
            }

            public int getLimit_time()
            {
                return limit_time;
            }

            public void setLimit_time(int limit_time)
            {
                this.limit_time = limit_time;
            }
        }

        public static class RoomIdleBean
        {
            /**
             * active : 1
             * minute_limit : 40
             */

            private int active;
            private int minute_limit;

            public int getActive()
            {
                return active;
            }

            public void setActive(int active)
            {
                this.active = active;
            }

            public int getMinute_limit()
            {
                return minute_limit;
            }

            public void setMinute_limit(int minute_limit)
            {
                this.minute_limit = minute_limit;
            }
        }

        public static class P2pSettingsBean
        {
            /**
             * plan_id : 0
             * name_id : 0
             * w_dm : 0
             * m_dm : 0
             * player : 0
             * online_limit : 0
             */

            private int plan_id;
            private int name_id;
            private int w_dm;
            private int m_dm;
            private int player;
            private int online_limit;

            public int getPlan_id()
            {
                return plan_id;
            }

            public void setPlan_id(int plan_id)
            {
                this.plan_id = plan_id;
            }

            public int getName_id()
            {
                return name_id;
            }

            public void setName_id(int name_id)
            {
                this.name_id = name_id;
            }

            public int getW_dm()
            {
                return w_dm;
            }

            public void setW_dm(int w_dm)
            {
                this.w_dm = w_dm;
            }

            public int getM_dm()
            {
                return m_dm;
            }

            public void setM_dm(int m_dm)
            {
                this.m_dm = m_dm;
            }

            public int getPlayer()
            {
                return player;
            }

            public void setPlayer(int player)
            {
                this.player = player;
            }

            public int getOnline_limit()
            {
                return online_limit;
            }

            public void setOnline_limit(int online_limit)
            {
                this.online_limit = online_limit;
            }
        }

        public static class ServersBean
        {
            /**
             * ip : 119.90.49.110
             * port : 8046
             */

            private String ip;
            private String port;

            public String getIp()
            {
                return ip;
            }

            public void setIp(String ip)
            {
                this.ip = ip;
            }

            public String getPort()
            {
                return port;
            }

            public void setPort(String port)
            {
                this.port = port;
            }
        }

        public static class CdnsWithNameBean
        {
            /**
             * name : 主线路
             * cdn : ws
             */

            private String name;
            private String cdn;

            public String getName()
            {
                return name;
            }

            public void setName(String name)
            {
                this.name = name;
            }

            public String getCdn()
            {
                return cdn;
            }

            public void setCdn(String cdn)
            {
                this.cdn = cdn;
            }
        }

        public static class MultiratesBean
        {
            /**
             * type : 0
             * name : 超清
             */

            private int type;
            private String name;

            public int getType()
            {
                return type;
            }

            public void setType(int type)
            {
                this.type = type;
            }

            public String getName()
            {
                return name;
            }

            public void setName(String name)
            {
                this.name = name;
            }
        }

        public static class WsproxyBean
        {
            /**
             * domain : dy2.8686c.com
             * port : 10010
             */

            private String domain;
            private String port;

            public String getDomain()
            {
                return domain;
            }

            public void setDomain(String domain)
            {
                this.domain = domain;
            }

            public String getPort()
            {
                return port;
            }

            public void setPort(String port)
            {
                this.port = port;
            }
        }
    }
}
