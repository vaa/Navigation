var app = new Vue({
    el: '#app',
    data: {
        //实体类
        category: [{
            id: '',
            name: ''
        }],
        editor: {
            id: '',
            name: '',
        },

        //分页选项
        pageConf: {
            //设置一些初始值(会被覆盖)
            pageCode: 1, //当前页
            pageSize: 6, //每页显示的记录数
            totalPage: 12, //总记录数
            pageOption: [6, 10, 20], //分页选项
        },

        dialogVisible: false,
        dialogType: true, //dialog分类：true：新增，false：修改
        defaultActive: '4',

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
        handleClose(key, keyPath) {
            this.dialogVisible = false;
        },

        //刷新列表
        reloadList() {
            this.search(this.pageConf.pageCode, this.pageConf.pageSize);
        },
        //条件查询
        search(pageCode, pageSize) {
            this.$http.post(api.category.findByPage(pageSize, pageCode)).then(result => {
                this.category = result.body.data.rows;
                this.pageConf.totalPage = result.body.data.total;
            });
        },

        //pageSize改变时触发的函数
        handleSizeChange(val) {
            this.search( this.pageConf.pageCode, val);
        },
        //当前页改变时触发的函数
        handleCurrentChange(val) {
            this.pageConf.pageCode = val;
            this.search( val, this.pageConf.pageSize);
        },

        //删除
        sureDelete(ids) {
            this.$confirm('你确定永久删除此用户信息？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                center: true
            }).then(() => {
                this.$http.post(api.category.delete, JSON.stringify(ids)).then(result => {
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
        //新增按钮
        handleSave() {
            this.editor = {};
            this.dialogVisible = true;
            this.dialogType = true;
        },
        //更新按钮
        handleEdit(id) {
            this.dialogVisible = true;
            //查询当前id对应的数据
            this.$http.get(api.category.findById(id)).then(result => {
                this.editor = result.body.data;
            });
            this.dialogType = false; //更新
        },
        handleGo() { //新增、更新公用
            this.dialogVisible = false;
            if (this.editor.name == null || this.editor.name == '') {
                this._notify('输入的信息不能为空', 'error');
                return false;
            }
            if (this.dialogType) {
                //新增
                this.$http.post(api.category.save, JSON.stringify(this.editor)).then(result => {
                    if (result.body.code == 200) {
                        this._notify(result.body.msg, 'success');
                    } else {
                        this._notify(result.body.msg, 'error');
                    }
                    this.reloadList();
                });
            } else {
                this.$http.put(api.category.update, JSON.stringify(this.editor)).then(result => {
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