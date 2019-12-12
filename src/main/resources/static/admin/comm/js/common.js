;
(function () {
    var commonJs = function(){
        var _this = this;

        //default loading
        this.init = function() {
            _this.addForm();
            _this.delete();
        }

        //ueditor
        this.ueditor = function () {
            var ue = UE.getEditor('editor',{
                toolbars: [
                    [
                        'fontfamily', //字体
                        'fontsize', //字号
                        'undo', //撤销
                        'redo', //重做
                        '|',
                        'emotion', //表情
                        'forecolor', //字体颜色
                        'backcolor', //背景色
                        'bold', //加粗
                        'underline', //下划线
                        'strikethrough', //删除线
                        '|',
                        'justifyleft', //居左对齐
                        'justifyright', //居右对齐
                        'justifycenter', //居中对齐
                        '|',
                        'link', //超链接
                        'unlink', //取消链接
                        'simpleupload', //单图上传
                        // 'insertimage', //多图上传
                        //'music', //音乐
                        //'insertvideo', //视频
                        'removeformat', //清除格式
                        'formatmatch', //格式刷
                        'source', //源代码
                    ]
                ]
            });
        }

        //add
        this.addForm = function () {
            $('#add-form').submit(function() {
                //此处可做表单验证
                var postData = $(this).serialize(); //序列化表单，后台可正常通过post方法获取数据
                var objectName = $("#object-name").val();
                console.log(postData);
                $.ajax({
                    url : "/admin/save/object",
                    type: "POST",
                    data: postData,
                    success: function (ret) {
                        console.log("submit-ret");
                        console.log(ret);

                        //添加成功
                        _this.disappearModal("保存成功");
                        window.location.href = '/admin/'+objectName+'/index.html';
                    }
                });
            });
        }

        //delete
        this.delete = function () {
            $(document).on("click", ".delete-single", function () {
                $("#myModal").modal('show');
                //根据id删除
                var id = $(this).data("id");
                var obj = $(this).data("obj");
                $("#obj-id").val(id);
                $("#obj-name").val(obj);
            });

            //确认删除
            $(document).on('click', ".btn-confirm", function () {
                var id =  $("#obj-id").val();
                var obj = $("#obj-name").val();
                $("#myModal").modal('hide');
                $.ajax({
                    url : '/admin/delete/record',
                    data: {id:id, obj:obj},
                    type : "post",
                    success:function (ret) {
                        console.log(ret);
                        if(ret.status>0) {
                            alert(ret.error);
                        }else{
                            //删除成功
                            $("#tr-"+id).remove();
                            _this.disappearModal("删除成功");
                        }
                    }
                });
            });
        }

        //自动隐藏提示框
        this.disappearModal = function (text) {
            $("#modal-disapper-content").html(text);
            $("#myDisapperModal").modal('show');
            setTimeout(function(){
                $("#myDisapperModal").modal('hide');//找到对应的标签隐藏
            },1000);
        }
    };

    window.commonJs = commonJs;
})();