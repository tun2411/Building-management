<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<html>
<head>

  <title>Danh sách khách hàng</title>

</head>
<body>

<div class="main-content" style="font-family: 'Times New Roman', Times, serif">
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

    </div>

    <div class="page-content">

      <div class="page-header">
        <h1>
          Danh sách khách hàng
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
        <div class="widget-box" bis_skin_checked="1">
          <div class="widget-header" bis_skin_checked="1">
            <h4 class="widget-title">Tìm kiếm</h4>

            <span class="widget-toolbar">
                    <a href="#admin/customer-list" data-action="reload">
                      <i class="ace-icon fa fa-refresh"></i>
                    </a>

                    <a href="#" data-action="collapse">
                      <i class="ace-icon fa fa-chevron-up"></i>
                    </a>

                    <a href="#" data-action="close">
                      <i class="ace-icon fa fa-times"></i>
                    </a>
                  </span>
          </div>

          <div class="widget-body" bis_skin_checked="1">
            <div class="widget-main" bis_skin_checked="1">
              <form:form id="listForm" method="GET" action="/admin/customer-list" modelAttribute="modelSearch">
                <div class="row" bis_skin_checked="1">
                  <div class="form-group" bis_skin_checked="1">
                    <div class="col-xs-12" bis_skin_checked="1">
                      <div class="col-xs-6" bis_skin_checked="1">
                        <label>Tên khách hàng</label>
                        <form:input class="form-control" path="fullName" />
                      </div>
                      <div class="col-xs-6" bis_skin_checked="1">
                        <label>Di động</label>
                        <form:input class="form-control" path="phone" />
                      </div>
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-xs-12" bis_skin_checked="1">
                      <div class="col-xs-6" bis_skin_checked="1">
                        <label>Trạng Thái</label>
                        <form:select path="status" class="form-control">
                          <form:option value="">---Chọn Tất cả trạng thái---</form:option>
                          <form:options items="${status}"/>

                        </form:select>
                      </div>
                      <div class="col-xs-6" bis_skin_checked="1">
                        <label>Email</label>
                        <form:input class="form-control" path="email" />
                      </div>
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-xs-12" bis_skin_checked="1">
                      <security:authorize access="hasRole('MANAGER')">
                          <div class="col-xs-4" bis_skin_checked="1">
                            <label>Chọn nhân viên</label>
                            <form:select path="staffId" class="form-control">
                              <form:option value="">---Chọn Nhân Viên---</form:option>
                              <form:options items="${staffs}"/>
                            </form:select>
                          </div>
                      </security:authorize>
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-xs-12" bis_skin_checked="1">
                      <div class="col-xs-5" bis_skin_checked="1">
                        <button type="button" class="btn btn-pink btn-sm"
                                id="btnSearchCustomer">
                              <span
                                      class="ace-icon fa fa-search icon-on-right bigger-110"
                              ></span>
                          Tìm kiếm
                        </button>
                      </div>
                    </div>
                  </div>
                </div>
              </form:form>


            </div>
          </div>

          <div style="padding-right: 20px" class="pull-right">
            <br>
            <a href="/admin/customer-edit">
              <button
                      class="btn btn-app btn-success btn-xs"
                      title="Thêm khách hàng"
              >
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-person-fill-add" viewBox="0 0 16 16">
                  <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0m-2-6a3 3 0 1 1-6 0 3 3 0 0 1 6 0"/>
                  <path d="M2 13c0 1 1 1 1 1h5.256A4.5 4.5 0 0 1 8 12.5a4.5 4.5 0 0 1 1.544-3.393Q8.844 9.002 8 9c-5 0-6 3-6 4"/>
                </svg>
              </button>
            </a>
            <security:authorize access="hasRole('MANAGER')">
            <button
                    class="btn btn-app btn-danger btn-xs"
                    title="Xoá khách hàng"
                    id="btnDeleteCustomer"
            >
              <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-person-fill-dash" viewBox="0 0 16 16">
                <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1m0-7a3 3 0 1 1-6 0 3 3 0 0 1 6 0"/>
                <path d="M2 13c0 1 1 1 1 1h5.256A4.5 4.5 0 0 1 8 12.5a4.5 4.5 0 0 1 1.544-3.393Q8.844 9.002 8 9c-5 0-6 3-6 4"/>
              </svg>
            </button>
            </security:authorize>
          </div>
        </div>
        <!-- /.span -->
      </div>

      <!-- /.Table list customer -->
      <div style="margin-left: 0px;width: 100%" class="row" bis_skin_checked="1">
        <div class="col-xs-12" bis_skin_checked="1">
          <br>
          <div class="hr hr-20 hr-double"></div>
          <br>
          <display:table name="${model.listResult}"
                         class="table table-striped table-bordered table-hover"
                         id="tableList"
                         size="${model.totalItems}"
                         requestURI="/admin/customer-list"
                         pagesize="${model.maxPageItems}"
                         partialList="true"
                         export="false"
          >
            <display:column title="">
              <label class="pos-rel">
                <input type="checkbox" class="ace" value="${tableList.id}" />
                <span class="lbl"></span>
              </label>
            </display:column>
            <display:column title="Tên khách hàng" property="fullName"/>
            <display:column title="Di động" property="phone"/>
            <display:column title="Email" property="email"/>
            <display:column title="Nhu cầu" property="demand"/>
            <display:column title="Người thêm" property="createdBy"/>
            <display:column title="Ngày thêm" property="createdDate"/>
            <display:column title="Tình trạng" property="status"/>
            <display:column title="Thao tác">
              <div
                      class="hidden-sm hidden-xs btn-group"
                      bis_skin_checked="1"
              >
                <security:authorize access="hasRole('MANAGER')">
                  <button style="width: 24px;height: 24px;border:none"
                          class="btn btn-xs btn-success"
                          onclick="assignmentCustomer(${tableList.id})" title = "Giao người dùng"
                  >
                    <i class="ace-icon fa fa-users bigger-120"></i>
                  </button>
                </security:authorize>


                <a href="/admin/customer-edit-${tableList.id} " style="width: 24px;height: 24px;border:none"
                   class="btn btn-xs btn-info" title="Sửa thông tin">
                  <i class="ace-icon fa fa-pencil bigger-120"></i>
                </a>

                <security:authorize access="hasRole('MANAGER')">
                  <button style="width: 24px;height: 24px;border:none"
                          class="btn btn-xs btn-danger"
                          onclick="deleteCustomer(${tableList.id})" title="Xoá người dùng"
                  >
                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                  </button>
                </security:authorize>

              </div>

            </display:column>

          </display:table>
        </div>
        <!-- /.span -->
      </div>
    </div>
  </div>
  <div class="modal fade"
       id="assignmentCustomerModal"
       tabindex="-1"
       role="dialog"
       aria-labelledby="exampleModalLabel"
       aria-hidden="true"
       style="font-family: 'Times New Roman', Times, serif"
  >
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">
            Danh sách nhân viên
          </h5>
          <button
                  type="button"
                  class="close"
                  data-dismiss="modal"
                  aria-label="Close"
          >
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <table
                  id="staffList"
                  class="table table-striped table-bordered table-hover"
          >
            <thead>
            <tr>
              <th class="center">
                <label class="pos-rel"> Chọn </label>
              </th>
              <th class="center">Tên nhân viên</th>
            </tr>
            </thead>

            <tbody>
            </tbody>
          </table>
          <input type="hidden" id="customerId" value="" />
        </div>
        <div class="modal-footer">
          <button
                  type="button"
                  class="btn btn-secondary"
                  data-dismiss="modal"
          >
            Huỷ thao tác
          </button>
          <button
                  type="button"
                  class="btn btn-primary"
                  id="btnAssignCustomer"
          >
            Giao khách hàng
          </button>
        </div>
      </div>
    </div>
  </div>
</div>

<script>
  function assignmentCustomer(customerId) {
    console.log("id khách hàng : " + customerId);
    $("#assignmentCustomerModal").modal();
    $("#customerId").val(customerId);
    loadStaff(customerId);
  }

  function loadStaff(customerId){
    $.ajax({
      type: "GET",
      url: "/api/customers/" + customerId + '/staffs',
      dataType: "json",
      success: function (response) {
        var row = '';
        $.each(response.data, function (index,item){
          row += '<tr>';
          row += '<td class="center"> <input type="checkbox"  value=' + item.staffId + ' id="checkbox_' + item.staffId +'"  class="center"' + item.checked + '/> </td>';
          row +=  '<td class="center">' + item.fullName + '</td>';
          row += '</tr>';
        });
        $('#staffList tbody').html(row);
      },
      error: function (response) {
        console.log("Failed");
      },
    });
  }

  $("#btnAssignCustomer").click(function (e) {
    e.preventDefault();
    var json = {};
    json["customerId"] = $("#customerId").val();
    var staffIds = $("#staffList")
            .find("tbody input[type = checkbox]:checked")
            .map(function () {
              return $(this).val();
            })
            .get();
    json["staffs"] = staffIds;
    console.log("Success");
    if (json["customerId"] === "") {
      alert("Id Not Found");
    } else {
      assignCustomer(json);
    }
  });

  $("#btnDeleteCustomer").click(function (e) {
    e.preventDefault();
    var customerIds = $("#customerList")
            .find("tbody input[type = checkbox]:checked")
            .map(function () {
              return $(this).val();
            })
            .get();
    console.log("Success...");
    if (customerIds === "") {
      alert("No Customers Is Selected");
    } else {
      deleteCustomers(customerIds);
    }
  });

  function deleteCustomers(ids) {
    $.ajax({
      type: "DELETE",
      url: "/api/customers/" + ids,
      dataType: "json",
      success: function (response) {
        console.log("Success");
        alert(response.message);
        window.location.href = "/admin/customer-list";
      },
      error: function (response) {
        console.log("Failed");
        alert(response.message);
      },
    });
  }

  function deleteCustomer(id) {
    if (id === "") {
      alert("Id not found");
    } else {
      deleteCustomers(id);
    }
  }

  function assignCustomer(json) {
    $.ajax({
      type: "POST",
      url: "/api/assignCustomer",
      data: JSON.stringify(json),
      dataType: "json",
      contentType: "application/json",
      success: function (response) {
        console.log("Success");
        alert(response.message);
      },
      error: function (response) {
        console.log("Failed");
        alert(response.message);
      },
    });
  }

  $("#btnSearchCustomer").click(function (e){
    e.preventDefault();
    $("#listForm").submit();
  })

</script>

</body>
</html>