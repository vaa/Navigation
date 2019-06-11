//设置全局表单提交格式
Vue.http.options.emulateJSON = true;
// Vue实例
new Vue({
    el: '#app',
    data() {
        return {
            register: {
                username: '',
                password: ''
            },
        };
    },
    methods: {
        registerEnter(register) {
            this.$refs[register].validate((valid) => {
                if (valid) {
                    //提交表单
                    this.$http.post('/user/register', {
                        username: this.register.username,
                        password: this.register.password,
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
                            this.$refs[register].resetFields();
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
