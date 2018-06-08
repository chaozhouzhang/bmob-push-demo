package cn.bmob.push.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created on 17/9/19 16:39
 */

public class Dynamic extends BmobObject {
    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
