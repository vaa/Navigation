//设置全局表单提交格式
Vue.http.options.emulateJSON = true;
const {body} = document;
const WIDTH = 1024;
const RATIO = 3;
const api = {

    index: {
        findSiteRanking: '/site/findSiteRanking',
        userinfo: '/user/info',
    },

    user: {
        update: '/user/update',
        info: '/user/info',
        localUpload: '/storage/upload',
        avatar: '/file/avatar.json',
    },

};