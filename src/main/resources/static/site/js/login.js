//设置全局表单提交格式
Vue.http.options.emulateJSON = true;
// Vue实例
new Vue({
    el: '#app',
    data() {
        return {
            login: {
                username: '',
                password: ''
            },
        };
    },
    methods: {
        loginEnter(login) {
            this.$refs[login].validate((valid) => {
                if (valid) {
                    //提交表单
                    this.$http.post('/user/login', {
                        username: this.login.username,
                        password: this.login.password,
                    }).then(result => {
                        if (result.body.code == 200) {
                            window.location.href = "/user";
                        } else {
                            this.$message({
                                    message: result.body.msg,
                                    type: 'error'
                                }
                            );
                            // 清空表单状态
                            this.$refs[login].resetFields();
                        }
                    });
                } else {
                    this.$message({
                            message: '输入信息有误！',
                            type: 'error'
                        }
                    );
                    return false;
                }
            });
        },

    },
});
