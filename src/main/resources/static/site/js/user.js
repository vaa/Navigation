var app = new Vue({
    el: '#app',
    data: {
        catalogs: null,
        catalogs_str:'[{"id":18,"title":"111111","user_id":6,"links":[{"id":36,"title":"bai","user_id":6,"catalog_id":18,"url":"http://www.baidu.com","icon":"/icons/www_baidu_com.ico"},{"id":37,"title":"google","user_id":6,"catalog_id":18,"url":"http://www.google.com","icon":""}]},{"id":19,"title":"22222","user_id":6,"links":[{"id":38,"title":"123hao","user_id":6,"catalog_id":19,"url":"http://www.123hao.com","icon":""}]}]',
        new_catalog_title: '',
        new_link_catalog_id: '',
        new_link_title: '',
        new_link_url: '',
        new_link_icon: '',
        edit_catalog: null,
        edit_link: null,
        drag: false
    },
    computed: {
        isShowNewLink: function () {
            return this.catalogs.length>0
        }
    },
    created: function () {
        if (this.catalogs_str.length > 0) {
            this.catalogs = JSON.parse(this.catalogs_str)
        }else {
            this.catalogs = JSON.parse('[]')
        }
    },
    watch: {
        'edit_link.url': function (newUrl, oldUrl) {
            var that = this
            if (oldUrl != null && newUrl != ''){
                var url = $.url(newUrl).attr('base')+'/favicon.ico';
                axios.get('/test_icon_url',{
                    params: {
                        url: url,
                    }
                }).then(function (response) {
                    if (that.edit_link != null) {
                        that.edit_link.icon = response['data'];
                    }
                }).catch(function (error) {
                    console.log(error);
                });
            }
        },
        new_link_url: function (newUrl, oldUrl) {
            var that = this
            if (newUrl==''){
                that.new_link_icon = ''
            } else {
                var url = $.url(newUrl).attr('base')+'/favicon.ico';
                axios.get('/test_icon_url',{
                    params: {
                        url: url,
                    }
                }).then(function (response) {
                    that.new_link_icon = response['data'];
                }).catch(function (error) {
                    console.log(error);
                });
            }
        }
    },
    methods: {
        uploadCatalogs: function () {
            console.log(this.catalogs)
            axios.post('/set_catalogs', {
                data: JSON.stringify(this.catalogs),
            }).then(function (response) {
                console.log(response);
                $('#editModalCenter').modal('hide');
            }).catch(function (error) {
                console.log(error);
            });
        },
        closeCatalogsModal: function () {
            $('#editModalCenter').modal('hide');
            this.catalogs = JSON.parse(this.catalogs_str)
        },
        deleteCatalog: function (catalog) {
            this.removeFromList(this.catalogs, catalog)
        },
        deleteLink: function (catalog, link) {
            this.removeFromList(catalog.links, link)
        },
        editCatalog: function(catalog){
            this.edit_catalog = catalog
            $('#newCatalogModal').modal('show');
        },
        editCatalogCancel: function(){
            $('#newCatalogModal').modal('hide');
            this.edit_catalog=null;
        },
        editLink: function(link){
            this.edit_link = link
            $('#newLinkModal').modal('show');
        },
        editLinkCancel: function(){
            $('#newLinkModal').modal('hide');
            this.edit_link=null;
        },
        saveCatalog: function () {
            if(this.edit_catalog!=null){
                $('#newCatalogModal').modal('hide');
                this.edit_catalog=null;
            }else {
                if(this.new_catalog_title!=''){
                    this.catalogs.push(JSON.parse('{"id":'+this.getRandomTmpId()+',"title":"'+this.new_catalog_title+'","links":[]}'));
                    $('#newCatalogModal').modal('hide');
                    this.new_catalog_title = '';
                }
            }
        },
        saveLink: function () {
            if(this.edit_link!=null){
                for (var i=0;i<this.catalogs.length;i++){
                    if(this.catalogs[i].id == this.edit_link.catalog_id && this.catalogs[i].links.indexOf(this.edit_link)<0){
                        this.catalogs[i].links.push(this.edit_link)
                    }else if(this.catalogs[i].id != this.edit_link.catalog_id && this.catalogs[i].links.indexOf(this.edit_link)>=0){
                        this.removeFromList(this.catalogs[i].links,this.edit_link);
                    }
                }
                $('#newLinkModal').modal('hide');
                this.edit_link=null;
            }else {
                if(this.new_link_catalog_id!=''||this.new_link_title!=''||this.new_link_title!=''){
                    for( var index = 0; index < this.catalogs.length; index ++){
                        if(this.catalogs[index].id == this.new_link_catalog_id){
                            this.catalogs[index].links.push(JSON.parse('{"id":'+this.getRandomTmpId()+',"title":"'+this.new_link_title+'","catalog_id":2,"url":"'+this.new_link_url+'","icon":"'+this.new_link_icon+'"}'));
                        }
                    }
                    this.new_link_catalog_id= '';
                    this.new_link_title= '';
                    this.new_link_url= '';
                    this.new_link_icon= '';
                    $('#newLinkModal').modal('hide');
                }
            }
        },
        removeFromList: function(arr, val) {
            var index = arr.indexOf(val);
            if (index > -1) {
                arr.splice(index, 1);
            }
        },
        getRandomTmpId: function () {
            return Math.floor(Math.random()*(-10000)-1);
        },
        openLink: function (url) {
            window.open(url)
        }
    }
})