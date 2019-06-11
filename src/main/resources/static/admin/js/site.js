var app = new Vue({
    el: '#app',
    data: {
        //实体类
        site: [{
            id: '',
            name: '',
            url: '',
            category: '',
            categoryId: '',
            hits: '',
            cover: ''
        }],
        editor: {
            id: '',
            name: '',
            url: '',
            category: '',
            categoryId: '',
            hits: '',
            cover: ''
        },
        pageConf: {
            //设置一些初始值(会被覆盖)
            pageCode: 1, //当前页
            pageSize: 6, //每页显示的记录数
            totalPage: 12, //总记录数
            pageOption: [6, 10, 20], //分页选项
        },
        defaultActive: '3',

        //条件查询单独封装的对象
        searchEntity: {},

        dialogType: true, //dialog分类：true：新增，false：修改
        dialogVisible: false,

        mobileStatus: false, //是否是移动端
        sidebarStatus: true, //侧边栏状态，true：打开，false：关闭
        sidebarFlag: ' openSidebar ', //侧边栏标志
    },
    created() {
        window.onload = function() {
            app.changeDiv();
        }
        window.onresize = function() {
            app.changeDiv();
        }
        this.search(this.pageConf.pageCode, this.pageConf.pageSize);
        this.init();
    },
    mounted() {
        this.$refs.loader.style.display = 'none';
    },
    methods: {
        _notify(message, type) {
            this.$message({
                message: message,
                type: type
            })
        },
        //关闭侧边栏
        handleClose(key, keyPath) {
            this.dialogVisible = false;
        },
        init() {
            //分类数据
            this.$http.get(api.site.allCategory).then(result => {
                this.options = [];
                result.body.data.forEach(row => {
                    if (row.name != null) {
                        this.options.push({value: row.id, label: row.name});
                    }
                });
            });
        },
        //刷新列表
        reloadList() {
            this.search(this.pageConf.pageCode, this.pageConf.pageSize);
        },
        //条件查询
        search(pageCode, pageSize) {
            this.$http.post(api.site.findByPage(pageSize, pageCode), this.searchEntity).then(result => {
                this.site = result.body.data.rows;
                this.pageConf.totalPage = result.body.data.total;
            });

        },
        //pageSize改变时触发的函数
        handleSizeChange(val) {
            this.search(this.pageConf.pageCode, val);
        },
        //当前页改变时触发的函数
        handleCurrentChange(val) {
            this.pageConf.pageCode = val; //为了保证刷新列表后页面还是在当前页，而不是跳转到第一页
            this.search(val, this.pageConf.pageSize);
        },

        //删除
        sureDelete(ids) {
            this.$confirm('你确定永久删除此用户信息？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                center: true
            }).then(() => {
                this.$http.post(api.site.delete, JSON.stringify(ids)).then(result => {
                    if (result.body.code == 200) {
                        this._notify(result.body.msg, 'success')
                        if ((this.pageConf.totalPage - 1) / this.pageConf.pageSize === (this.pageConf.pageCode - 1)) {
                            this.pageConf.pageCode = this.pageConf.pageCode - 1;
                        }
                        this.reloadList();
                    } else {
                        this._notify(result.body.msg, 'error')
                        this.reloadList();
                    }
                });
            }).catch(() => {
                this._notify('已取消删除', 'info')
            });
        },

        //删除按钮
        handleDelete(id) {
            var ids = new Array();
            ids.push(id);
            this.sureDelete(ids);
        },

        //保存
        save() {
            if (this.editor.name == null || this.editor.name == '' || this.editor.url == null || this.editor.url == '') {
                this.reloadList();
                this._notify('输入的信息不能为空', 'warning')
                return;
            } else {
                this.$http.post(api.site.save, JSON.stringify(this.editor)).then(result => {
                    this.reloadList();
                    if (result.body.code == 200) {
                        this.editor.site = {};
                        this._notify(result.body.msg, 'success')
                    } else {
                        this._notify(result.body.msg, 'error')
                    }
                });
            }
            this.editor = {};
        },


        handleSave(){
            this.editor = {};
            this.editor.hits='0';
            this.dialogVisible = true;
            this.dialogType = true;
        },

        //触发编辑按钮
        handleEdit(id) {
            this.dialogVisible = true;
            this.editor = {}; //清空表单
            //查询当前id对应的数据
            this.$http.get(api.site.findById(id)).then(result => {
                this.editor = result.body.data;
            });
            this.dialogType = false; //更新
        },

        handleGo() { //新增、更新公用
            this.dialogVisible = false;
            if (this.editor.name == null || this.editor.name == '') {
                this._notify('输入的网站名称不能为空', 'error');
                return false;
            }
            if (this.dialogType) {
                //新增
                this.$http.post(api.site.save, JSON.stringify(this.editor)).then(result => {
                    if (result.body.code == 200) {
                        this._notify(result.body.msg, 'success');
                    } else {
                        this._notify(result.body.msg, 'error');
                    }
                    this.reloadList();
                });
            } else {
                this.$http.put(api.site.update, JSON.stringify(this.editor)).then(result => {
                    if (result.body.code == 200) {
                        this._notify(result.body.msg, 'success');
                    } else {
                        this._notify(result.body.msg, 'error');
                    }
                    this.reloadList();
                });
            }

        },
        /**
         * 监听窗口改变UI样式（区别PC和Phone）
         */
        changeDiv() {
            let isMobile = this.isMobile();
            if (isMobile) {
                //手机访问
                this.sidebarFlag = ' hideSidebar mobile ';
                this.sidebarStatus = false;
                this.mobileStatus = true;
            } else {
                this.sidebarFlag = ' openSidebar';
                this.sidebarStatus = true;
                this.mobileStatus = false;
            }
        },
        isMobile() {
            let rect = body.getBoundingClientRect();
            return rect.width - RATIO < WIDTH
        },
        handleSidebar() {
            if (this.sidebarStatus) {
                this.sidebarFlag = ' hideSidebar ';
                this.sidebarStatus = false;

            } else {
                this.sidebarFlag = ' openSidebar ';
                this.sidebarStatus = true;
            }
            let isMobile = this.isMobile();
            if (isMobile) {
                this.sidebarFlag += ' mobile ';
                this.mobileStatus = true;
            }
        },
        //蒙版
        drawerClick() {
            this.sidebarStatus = false;
            this.sidebarFlag = ' hideSidebar mobile '
        }
    },
});
