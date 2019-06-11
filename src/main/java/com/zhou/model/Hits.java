package com.zhou.model;


public class Hits {
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 网址id
     */
    private Integer siteId;

    /**
     * 用户访问网址数
     */
    private Integer userHits;

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取网址id
     *
     * @return site_id - 网址id
     */
    public Integer getSiteId() {
        return siteId;
    }

    /**
     * 设置网址id
     *
     * @param siteId 网址id
     */
    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    /**
     * 获取用户访问网址数
     *
     * @return user_hits - 用户范围网址数
     */
    public Integer getUserHits() {
        return userHits;
    }

    /**
     * 设置用户访问网址数
     *
     * @param userHits 用户访问网址数
     */
    public void setUserHits(Integer userHits) {
        this.userHits = userHits;
    }

    @Override
    public String toString() {
        return "Hits{" +
                "userId=" + userId +
                ", siteId=" + siteId +
                ", userHits=" + userHits +
                '}';
    }
}