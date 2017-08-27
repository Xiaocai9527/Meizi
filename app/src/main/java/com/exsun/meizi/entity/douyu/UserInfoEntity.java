package com.exsun.meizi.entity.douyu;

import java.util.List;

public class UserInfoEntity {
    private AvatarBean avatar;
    private String birthday;
    private String email;
    private String email_status;
    private String follow;
    private String gold;
    private String gold1;
    private String groupid;
    private String has_room;
    private String ident_status;
    private int ios_gold_switch;
    private int is_noble;
    private String is_own_room;
    private int is_reg_by_third;
    private String lastlogin;
    private LevelBean level;
    private LocationBean location;
    private String mobile_phone;
    private String nickname;
    private String noble_gold;
    private int noble_lv;
    private String phone_status;
    private List<Integer> privilegeLevelList;
    private String qq;
    private String score;
    private String sex;
    private String uid;
    private UserlevelBean userlevel;
    private String username;

    public static class AvatarBean {
        private String big;
        private String middle;
        private String small;

        public String getSmall() {
            return this.small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getMiddle() {
            return this.middle;
        }

        public void setMiddle(String middle) {
            this.middle = middle;
        }

        public String getBig() {
            return this.big;
        }

        public void setBig(String big) {
            this.big = big;
        }
    }

    public static class LevelBean {
        private CurrentBean current;
        private NextBean next;

        public static class CurrentBean {
            private int lv;
            private String mpic;
            private String name;
            private String pic;
            private String pic_url;
            private int score;

            public int getLv() {
                return this.lv;
            }

            public void setLv(int lv) {
                this.lv = lv;
            }

            public String getPic() {
                return this.pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getMpic() {
                return this.mpic;
            }

            public void setMpic(String mpic) {
                this.mpic = mpic;
            }

            public String getName() {
                return this.name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPic_url() {
                return this.pic_url;
            }

            public void setPic_url(String pic_url) {
                this.pic_url = pic_url;
            }

            public int getScore() {
                return this.score;
            }

            public void setScore(int score) {
                this.score = score;
            }
        }

        public static class NextBean {
            private int lv;
            private String mpic;
            private String name;
            private String pic;
            private String pic_url;
            private int score;

            public int getLv() {
                return this.lv;
            }

            public void setLv(int lv) {
                this.lv = lv;
            }

            public String getPic() {
                return this.pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getMpic() {
                return this.mpic;
            }

            public void setMpic(String mpic) {
                this.mpic = mpic;
            }

            public String getName() {
                return this.name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPic_url() {
                return this.pic_url;
            }

            public void setPic_url(String pic_url) {
                this.pic_url = pic_url;
            }

            public int getScore() {
                return this.score;
            }

            public void setScore(int score) {
                this.score = score;
            }
        }

        public CurrentBean getCurrent() {
            return this.current;
        }

        public void setCurrent(CurrentBean current) {
            this.current = current;
        }

        public NextBean getNext() {
            return this.next;
        }

        public void setNext(NextBean next) {
            this.next = next;
        }
    }

    public static class LocationBean {
        private String city;
        private String province;

        public String getProvince() {
            return this.province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return this.city;
        }

        public void setCity(String city) {
            this.city = city;
        }
    }

    public static class UserlevelBean {
        private int cur_score;
        private int is_full;
        private int lv;
        private int next_level_score;

        public int getCur_score() {
            return this.cur_score;
        }

        public void setCur_score(int cur_score) {
            this.cur_score = cur_score;
        }

        public int getNext_level_score() {
            return this.next_level_score;
        }

        public void setNext_level_score(int next_level_score) {
            this.next_level_score = next_level_score;
        }

        public int getLv() {
            return this.lv;
        }

        public void setLv(int lv) {
            this.lv = lv;
        }

        public int getIs_full() {
            return this.is_full;
        }

        public void setIs_full(int is_full) {
            this.is_full = is_full;
        }
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return this.qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getMobile_phone() {
        return this.mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    public String getPhone_status() {
        return this.phone_status;
    }

    public void setPhone_status(String phone_status) {
        this.phone_status = phone_status;
    }

    public String getEmail_status() {
        return this.email_status;
    }

    public void setEmail_status(String email_status) {
        this.email_status = email_status;
    }

    public String getLastlogin() {
        return this.lastlogin;
    }

    public void setLastlogin(String lastlogin) {
        this.lastlogin = lastlogin;
    }

    public AvatarBean getAvatar() {
        return this.avatar;
    }

    public void setAvatar(AvatarBean avatar) {
        this.avatar = avatar;
    }

    public String getHas_room() {
        return this.has_room;
    }

    public void setHas_room(String has_room) {
        this.has_room = has_room;
    }

    public String getGroupid() {
        return this.groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getIs_own_room() {
        return this.is_own_room;
    }

    public void setIs_own_room(String is_own_room) {
        this.is_own_room = is_own_room;
    }

    public LocationBean getLocation() {
        return this.location;
    }

    public void setLocation(LocationBean location) {
        this.location = location;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getIs_reg_by_third() {
        return this.is_reg_by_third;
    }

    public void setIs_reg_by_third(int is_reg_by_third) {
        this.is_reg_by_third = is_reg_by_third;
    }

    public String getGold1() {
        return this.gold1;
    }

    public void setGold1(String gold1) {
        this.gold1 = gold1;
    }

    public String getScore() {
        return this.score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public LevelBean getLevel() {
        return this.level;
    }

    public void setLevel(LevelBean level) {
        this.level = level;
    }

    public UserlevelBean getUserlevel() {
        return this.userlevel;
    }

    public void setUserlevel(UserlevelBean userlevel) {
        this.userlevel = userlevel;
    }

    public String getFollow() {
        return this.follow;
    }

    public void setFollow(String follow) {
        this.follow = follow;
    }

    public int getIos_gold_switch() {
        return this.ios_gold_switch;
    }

    public void setIos_gold_switch(int ios_gold_switch) {
        this.ios_gold_switch = ios_gold_switch;
    }

    public String getGold() {
        return this.gold;
    }

    public void setGold(String gold) {
        this.gold = gold;
    }

    public String getNoble_gold() {
        return this.noble_gold;
    }

    public void setNoble_gold(String noble_gold) {
        this.noble_gold = noble_gold;
    }

    public String getIdent_status() {
        return this.ident_status;
    }

    public void setIdent_status(String ident_status) {
        this.ident_status = ident_status;
    }

    public int getIs_noble() {
        return this.is_noble;
    }

    public void setIs_noble(int is_noble) {
        this.is_noble = is_noble;
    }

    public int getNoble_lv() {
        return this.noble_lv;
    }

    public void setNoble_lv(int noble_lv) {
        this.noble_lv = noble_lv;
    }

    public List<Integer> getPrivilegeLevelList() {
        return this.privilegeLevelList;
    }

    public void setPrivilegeLevelList(List<Integer> privilegeLevelList) {
        this.privilegeLevelList = privilegeLevelList;
    }
}
