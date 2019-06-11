package com.zhou.model;


public class Site {
    /**
     * 网址id
     */
    private Integer id;

    /**
     * 网址备注名
     */
    private String name;

    /**
     * 网址的链接
     */
    private String url;

    /**
     * 所属的分类的id
     */
    private Integer categoryId;

    /**
     * 对该网址的描述
     */
    private String describe;

    /**
     * 总点击量
     */
    private Integer hits;

    /**
     * 网址的图标
     */
    private String cover;

    /**
     * 所属的分类
     */
    private String category;

    /**
     * 获取网址id
     *
     * @return id - 网址id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置网址id
     *
     * @param id 网址id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取网址备注名
     *
     * @return name - 网址备注名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置网址备注名
     *
     * @param name 网址备注名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取网址的链接
     *
     * @return url - 网址的链接
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置网址的链接
     *
     * @param url 网址的链接
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取所属的分类的id
     *
     * @return category_id - 所属的分类的id
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * 设置所属的分类的id
     *
     * @param categoryId 所属的分类的id
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取对该网址的描述
     *
     * @return describe - 对该网址的描述
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * 设置对该网址的描述
     *
     * @param describe 对该网址的描述
     */
    public void setDescribe(String describe) {
        this.describe = describe;
    }

    /**
     * 获取总点击量
     *
     * @return Hits - 总点击量
     */
    public Integer getHits() {
        return hits;
    }

    /**
     * 设置总点击量
     *
     * @param hits 总点击量
     */
    public void setHits(Integer hits) {
        this.hits = hits;
    }

    /**
     * 获取网站图标
     *
     * @return cover - 网站图标
     */
    public String getCover() {
        return cover;
    }
    /**
     * 设置获取网站图标
     *
     * @param cover 网站图标
     */
    public void setCover(String cover) {
        this.cover = cover;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Site{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", categoryId=" + categoryId +
                ", describe='" + describe + '\'' +
                ", hits=" + hits +
                ", cover='" + cover + '\'' +
                '}';
    }
}