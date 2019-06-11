//设置全局表单提交格式
Vue.http.options.emulateJSON = true;
const {body} = document;
const WIDTH = 1024;
const RATIO = 3;
const api = {
    common: {
        logout: '/admin/logout'
    },
    index: {
        userCount: '/user/findAllCount',
        categoryCount: '/category/findAllCount',
        siteCount: '/site/findAllCount',
    },
    user: {
        findByPage(pageSize, pageCode) {
            return '/user/findByPage?pageSize=' + pageSize + '&pageCode=' + pageCode
        },
        findById(id) {
            return '/user/findById?id=' + id
        },
        delete: '/user/delete',
        update: '/user/update',
    },
    site: {
        findByPage(pageSize, pageCode) {
            return '/site/findByPage?pageSize=' + pageSize + '&pageCode=' + pageCode
        },
        findById(id) {
            return '/site/findById?id=' + id
        },
        save: '/site/save',
        delete: '/site/delete',
        update: '/site/update',
        allCategory: '/category/findAll',
    },
    category: {
        findByPage(pageSize, pageCode) {
            return '/category/findByPage?pageSize=' + pageSize + '&pageCode=' + pageCode
        },
        findCategoryRanking: '/category/findCategoryRanking',
        delete: '/category/delete',
        update: '/category/update',
        save: '/category/save',
        findById(id) {
            return '/category/findById?id=' + id
        },
    },
    admin: {
        update: '/admin/update',
        info: '/admin/info',
        localUpload: '/storage/upload',
        avatar: '/file/avatar.json',
    },

};