<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="/common/taglib.jsp"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="main-content" style="font-family: 'Times New Roman', Times, serif" >
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

        </div>

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
                                <form:options items="${status}"/>
                            </form:select>
<%--                    <form:select path="status" class="form-control">--%>
<%--                        <form:option value="" label="--- Chọn trạng thái ---"/>--%>
<%--                        <form:options items="${status}" itemValue="value" itemLabel="value"/>--%>
<%--                    </form:select>--%>
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
<%--            </div>--%>
<%--        </div>--%>
                <c:if test="${not empty customerEdit.id}">
                     <c:forEach var="item" items="${transactionType}">
                        <div class="col-xs-12">
                            <h2 class="smaller lighter blue">
                            ${item.value}
                            <button class="btn btn-md btn-success pull-right" title="Thêm giao dịch" onclick="addTransaction('${item.key}',${customerEdit.id})">
                            <i class="ace-icon glyphicon small-70">Thêm giao dịch</i>
                            </button>
                            </h2>
        </div>
                         <br>
                         <hr>
                         <br>
                         <c:if test="${item.key =='CSKH'}">
        <div class="row">
            <div class="col-xs-12">
            <table id="CSKH-list" class="table table-striped table-bordered table-hover">
                <thead>
                 <tr>
                     <th>Ngày tạo</th>
                     <th>Người tạo</th>
                     <th>Ngày sửa</th>
                     <th>Người sửa</th>
                     <th>Chi tiết giao dịch</th>
                     <th>Thao tác</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="transaction" items="${CSKH}">
                    <tr>
                        <td>${transaction.createdDate}</td>
                        <td>${transaction.createdBy}</td>
                        <td>${transaction.modifiedDate}</td>
                        <td>${transaction.modifiedBy}</td>
                        <td>${transaction.note}</td>
                        <td>
                            <a class="btn btn-xs btn-info" title="Sửa thông tin" onclick="updateTransaction('CSKH',${customerEdit.id},${transaction.id},'${transaction.note}')">
                                <i class="ace-icon fa fa-pencil bigger-120"></i>
                            </a>
<%--                            <button style="width: 24px;height: 24px;border:none"--%>
<%--                                    class="btn btn-xs btn-danger"--%>
<%--                                    onclick="deleteTransaction(${transaction.id},${customerEdit.id})" title="Xoá giao dịch"--%>
<%--                            >--%>
<%--                                <i class="ace-icon fa fa-trash-o bigger-120"></i>--%>
<%--                            </button>--%>
                            <a class="btn btn-xs btn-danger" title="Xoá giao dịch" onclick="deleteTransaction(${transaction.id},${customerEdit.id})">
                                <i class="ace-icon fa fa-trash-o bigger-120"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        </div>
                </c:if>
                         <c:if test="${item.key =='DDX'}">
                             <div class="row">
                                 <div class="col-xs-12">
                                     <table id="DDX-list" class="table table-striped table-bordered table-hover">
                                         <thead>
                                         <tr>
                                             <th>Ngày tạo</th>
                                             <th>Người tạo</th>
                                             <th>Ngày sửa</th>
                                             <th>Người sửa</th>
                                             <th>Chi tiết giao dịch</th>
                                             <th>Thao tác</th>
                                         </tr>
                                         </thead>
                                         <tbody>
                                         <c:forEach var="transaction" items="${DDX}">
                                             <tr>
                                                 <td>${transaction.createdDate}</td>
                                                 <td>${transaction.createdBy}</td>
                                                 <td>${transaction.modifiedDate}</td>
                                                 <td>${transaction.modifiedBy}</td>
                                                 <td>${transaction.note}</td>
                                                 <td>
                                                     <a class="btn btn-xs btn-info" title="Sửa thông tin" onclick="updateTransaction('DDX',${customerEdit.id},${transaction.id},'${transaction.note}')">
                                                         <i class="ace-icon fa fa-pencil bigger-120"></i>
                                                     </a>
<%--                                                     <button style="width: 24px;height: 24px;border:none"--%>
<%--                                                             class="btn btn-xs btn-danger"--%>
<%--                                                             onclick="deleteTransaction(${transaction.id},${customerEdit.id})" title="Xoá giao dịch"--%>
<%--                                                     >--%>
<%--                                                         <i class="ace-icon fa fa-trash-o bigger-120"></i>--%>
<%--                                                     </button>--%>
                                                     <a class="btn btn-xs btn-danger" title="Xoá giao dịch" onclick="deleteTransaction(${transaction.id},${customerEdit.id})">
                                                         <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                                     </a>
                                                 </td>
                                             </tr>
                                         </c:forEach>
                                         </tbody>
                                     </table>
                                 </div>
                             </div>
                         </c:if>
                      </c:forEach>
                    </c:if>
            </div>
        </div>
    </div>
</div>



<div class="modal fade" id="addOrUpdateTransactionModal">
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <h4 class="modal-title" id="exampleModalLabel">Nhập thông tin giao dich </h4>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">x</span>
            </button>
        </div>
        <div class="modal-body">
            <div class="form-group">
            <label class="col-sm-3 control-label">Chi tiết giao dịch</label>
                <div class="col-sm-9">
                    <input id="note" type="text" class="form-control" placeholder="Nhập chi tiết giao dich"/>
                </div>
            </div>
            <input type="hidden" id="customerId" value="">
            <input type="hidden" id="code" value="">
            <input type="hidden" id="transactionId" value="">
<%--            <input type="hidden" id="createdBy" value="">--%>
<%--            <input type="hidden" id="createdDate" value="">--%>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-primary" id="btnAddOrUpdateTransaction">Xác nhận</button>
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
        </div>
    </div>
</div>
</div>

<script>
    function addTransaction(code,customerId){
        $('#addOrUpdateTransactionModal').modal();
        $('#note').val('');
        $('#customerId').val(customerId);
        $('#code').val(code);
    }
    function updateTransaction(code,customerId,transactionId,note){
        $('#addOrUpdateTransactionModal').modal();
        $('#customerId').val(customerId);
        $('#code').val(code);
        $('#transactionId').val(transactionId);
        $('#note').val(note);
    }

    $('#btnAddOrUpdateTransaction').click(function(e) {
        e.preventDefault();
        var data = {};
        data['id'] = $('#transactionId').val();
        data['code'] = $('#code').val();
        data['note'] = $('#note').val();
        data['customerId'] = $('#customerId').val();
        data['createdBy'] = $('#createdBy').val();
        data['createdDate'] = $('#createdDate').val();

        if (data['note'].trim() === '') {
            $('#note').attr('placeholder', 'Vui lòng điền chi tiết giao dịch');
            $('#note').focus();
            return;
        }
        if (data['id'] === '') {
            addTransactionRequest(data);
        } else {
            updateTransactionRequest(data);
        }
    });

    function addTransactionRequest(data) {
        $.ajax({
            type: "POST",
            url: "/api/transactions",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function(response) {
                console.log("Success response:", response);
                alert(response.message || 'Thêm giao dịch thành công');
                $('#addOrUpdateTransactionModal').modal('hide');
                location.reload();
            },
            error: function(response) {
                console.log("Error response:", response);
                let errorMessage = response.responseJSON?.message || 'Thêm giao dịch thất bại';
                alert(errorMessage + (response.responseJSON?.detail ? ': ' + response.responseJSON.detail : ''));
            }
        });
    }

    function updateTransactionRequest(data) {
        $.ajax({
            type: "PUT",
            url: "/api/transactions/" + data['id'],
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function(response) {
                console.log("Success response:", response);
                alert(response.message || 'Cập nhật giao dịch thành công');
                $('#addOrUpdateTransactionModal').modal('hide');
                location.reload();
            },
            error: function(response) {
                console.log("Error response:", response);
                let errorMessage = response.responseJSON?.message || 'Cập nhật giao dịch thất bại';
                alert(errorMessage + (response.responseJSON?.detail ? ': ' + response.responseJSON.detail : ''));
            }
        });
    }

    function deleteTransaction(id, customerId) {
        if (confirm("Bạn có chắc muốn xóa giao dịch này?")) {
            deleteTransactionRequest(id, customerId);
        }
    }

    function deleteTransactionRequest(id, customerId) {
        $.ajax({
            type: "DELETE",
            url: "/api/transactions/" + id,
            data: JSON.stringify({ customerId: customerId }),
            dataType: "json",
            contentType: "application/json",
            success: function(response) {
                console.log("Success response:", response);
                alert(response.message || 'Xoá giao dịch thành công');
                $('#addOrUpdateTransactionModal').modal('hide');
                location.reload();
            },
            error: function(response) {
                console.log("Error response:", response);
                let errorMessage = response.responseJSON?.message || 'Xoá giao dịch thất bại';
                alert(errorMessage + (response.responseJSON?.detail ? ': ' + response.responseJSON.detail : ''));
            }
        });
    }


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
        if (json["demand"]=== '') {
            ok =0;
            $('#demand').after('<span style="color:red" class="error-message">Nhu cầu không được để trống</span>')
        }
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
