package com.zhou.model;


public class Category {
    /**
     * 网址分类的id
     */
    private Integer id;

    /**
     * 分类的名字(标签)
     */
    private String name;

    /**
     * 分类点击量
     */
    private String value;

    /**
     * 获取网址分类的id
     *
     * @return id - 网址分类的id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置网址分类的id
     *
     * @param id 网址分类的id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取分类的名字(标签)
     *
     * @return name - 分类的名字(标签)
     */
    public String getName() {
        return name;
    }

    /**
     * 设置分类的名字(标签)
     *
     * @param name 分类的名字(标签)
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}