// Vue实例
var app = new Vue({
    el: '#app',
    data: {
        entity: {
            archives: [{
                category: {
                    id: '',
                    name: ''
                },
                sites: [{
                    id: '',
                    name: '',
                    url: '',
                    category: '',
                    categoryId: '',
                    describe: '',
                    hits: '',
                    cover: ''
                }],
            }],
        },
        user: {username: ''},
        flag:false
    },
    methods: {
        /**
         * 一些初始化数据
         */
        init() {
            this.$http.get(api.index.findSiteRanking).then(result => {
                this.entity.archives = result.body;
            });
        },

        //获取当前用户信息
        getUserInfo() {
            this.$http.get(api.user.info).then(result => {
                this.user = result.body.data;
                if(this.user.username!=''){
                    this.flag=true;
                }
            });
        },


    },
    // 生命周期函数
    created() {
        this.init();
        this.getUserInfo();
    },

});
