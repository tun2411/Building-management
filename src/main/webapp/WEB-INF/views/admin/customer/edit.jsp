<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="/common/taglib.jsp"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div
        class="main-content"
        style="font-family: 'Times New Roman', Times, serif"
>
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check("breadcrumbs", "fixed");
                } catch (e) {}
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Home</a>
                </li>
                <li class="active">Dashboard</li>
            </ul>
            <!-- /.breadcrumb -->
        </div>

        <div class="page-content">

            <div class="page-header">
                <h1>
                    Thông tin khách hàng
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        overview &amp; stats
                    </small>
                </h1>
            </div>
            <!-- /.page-header -->

            <!-- /.row -->
        </div>

        <!-- /.page-content -->
        <div class="row">
            <div class="col-xs-12" bis_skin_checked="1">
                <form:form class="form-horizontal" role="form" id="form-edit" action="/admin/customer-edit" method="GET" modelAttribute="customerEdit">
                    <div class="form-group" bis_skin_checked="1">
                        <label class="col-xs-3 control-label">Tên khách hàng</label>
                        <div class="col-xs-6">
                            <form:input class="form-control" path="fullName" />
                        </div>
                        <span class="error-message" style="color: red" id="fullName"></span>
                    </div>
                    <div class="form-group" bis_skin_checked="1">
                        <label class="col-xs-3 control-label">Số điện thoại</label>
                        <div class="col-xs-6">
                            <form:input class="form-control" path="phone" />
                        </div>
                    </div>
                    <div class="form-group" bis_skin_checked="1">
                        <label class="col-xs-3 control-label">Email</label>
                        <div class="col-xs-6">
                            <form:input class="form-control" path="email" />
                        </div>
                    </div>
                    <div class="form-group" bis_skin_checked="1">
                        <label class="col-xs-3 control-label">Tên công ty</label>
                        <div class="col-xs-6">
                            <form:input class="form-control" path="companyName" />
                        </div>
                    </div>
                    <div class="form-group" bis_skin_checked="1">
                        <label class="col-xs-3 control-label">Nhu cầu</label>
                        <div class="col-xs-6">
                            <form:input class="form-control" path="demand" />
                        </div>
                    </div>
                    <div class="form-group" bis_skin_checked="1">
                        <label class="col-xs-3 control-label">Trạng thái xử lý</label>
                        <div class="col-xs-2">
                            <form:select path="status" class="form-control">
<%--                                <form:option value="">---Chọn Quận---</form:option>--%>
                                <form:options items="${status}"/>
                            </form:select>
                        </div>
                        <span class="error-message" style="color: red" id="status"></span>
                    </div>



                    <div class="form-group" bis_skin_checked="1">
                        <label class="col-xs-3 control-label"></label>
                        <div class="col-xs-9">
                            <c:if test="${not empty customerEdit.id}">
                                <button
                                        type="button"
                                        class="btn btn-warning"
                                        id="btnAddCustomer"
                                >
                                    Cập nhật thông tin
                                </button>
                            </c:if>
                            <c:if test="${empty customerEdit.id}">
                                <button
                                        type="button"
                                        class="btn btn-primary"
                                        id="btnAddCustomer"
                                >
                                    Thêm khách hàng
                                </button>
                            </c:if>
                            <a href="/admin/customer-list">
                                <button type="button" class="btn btn-danger">
                                    Huỷ thao tác
                                </button>
                            </a>
                        </div>
                    </div>
                    <input type="hidden" id="id" value="${customerEdit.id}"></input>
                </form:form>
            </div>
        </div>
    </div>
</div>

<script>
    var ok;
    function validateDataCustomer(json){
        $('.error-message').html('');
        if (json["fullName"] === '') {
            ok =0;
            $('#fullName').after('<span style="color:red" class="error-message">Tên khách hàng không được trống</span>')
        }
        if (json["phone"] === '') {
            ok =0;
            $('#phone').after('<span style="color:red" class="error-message">Số điện thoại không được trống</span>')
        }
        // if (json["demand"]=== '') {
        //     ok =0;
        //     $('#demand').after('<span style="color:red" class="error-message">Nhu cầu không được trống</span>')
        // }
    }


    $("#btnAddCustomer").click(function () {
        var formData = $("#form-edit").serializeArray();
        var json = {};
        $.each(formData, function (i, it) {
                json["" + it.name + ""] = it.value;
        });
        json['id'] = $('#id').val();
        console.log("Oke");
        ok=1;
        validateDataCustomer(json);
        if(ok === 0){
            alert("Failed");
        }else{
            if(json["id"] ===''){
                addCustomer(json);
            }else{
                updateCustomer(json);
            }
        }
    });

    function addCustomer(json) {
        //Gui request xuong sever
        $.ajax({
            type: "POST",
            url: "/api/customers",
            data: JSON.stringify(json),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                alert(response.message);
                console.log("Success");
                window.location.href = "/admin/customer-list";
            },
            error: function (response) {
                alert(response.data.join('\n'));
            },
        });
    }
    function updateCustomer(json) {
        //Gui request xuong sever
        $.ajax({
            type: "PUT",
            url: "/api/customers",
            data: JSON.stringify(json),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                alert(response.message);
                console.log("Success");
                window.location.href = "/admin/customer-list";
            },
            error: function (response) {
                console.log("Failed");
                //alert(response.error);
            },
        });
    }
</script>
</body>
</html>
