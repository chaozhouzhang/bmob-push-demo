package cn.bmob.push.bean;

import cn.bmob.v3.BmobUser;

/**
 * Created on 17/8/24 13:03
 */

public class User extends BmobUser {

    String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
