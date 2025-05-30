<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<html>
<head>

  <title>Danh sách toà nhà</title>
  <!-- Display Tag CSS -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/displaytag/1.2/displaytag.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/displaytag/1.2/displaytag-print.css">
  <!-- Display Tag JS -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/displaytag/1.2/displaytag.js"></script>
  <style>
    .pagination {
      margin: 10px 0;
    }
    .pagination a {
      padding: 5px 10px;
      margin: 0 2px;
      border: 1px solid #ddd;
      text-decoration: none;
      color: #333;
    }
    .pagination a:hover {
      background-color: #f5f5f5;
    }
    .pagination .active {
      background-color: #337ab7;
      color: white;
      border-color: #337ab7;
    }
    .sortable {
      cursor: pointer;
    }
    .sortable:hover {
      background-color: #f5f5f5;
    }
    .sortable:after {
      content: '↕';
      margin-left: 5px;
    }
    .sortable.asc:after {
      content: '↑';
    }
    .sortable.desc:after {
      content: '↓';
    }
  </style>

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
            Danh sách toà nhà
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
                    <a href="#admin/building-list" data-action="reload">
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
                <form:form id="listForm" method="GET" action="/admin/building-list" modelAttribute="modelSearch">
                  <div class="row" bis_skin_checked="1">
                    <div class="form-group" bis_skin_checked="1">
                      <div class="col-xs-12" bis_skin_checked="1">
                        <div class="col-xs-6" bis_skin_checked="1">
                          <label>Tên toà nhà</label>
                              <form:input class="form-control" path="name" />
                        </div>
                        <div class="col-xs-6" bis_skin_checked="1">
                          <label>Diện tích sàn</label>
                          <form:input class="form-control" path="floorArea" />
                        </div>
                      </div>
                    </div>
                    <div class="form-group">
                      <div class="col-xs-12" bis_skin_checked="1">
                        <div class="col-xs-2" bis_skin_checked="1">
                          <label>Quận</label>
                          <form:select path="district" class="form-control">
                            <form:option value="">---Chọn Quận---</form:option>
                            <form:options items="${district}"/>
                          </form:select>
                        </div>
                        <div class="col-xs-5" bis_skin_checked="1">
                          <label>Phường</label>
                          <form:input class="form-control" path="ward" />
                        </div>
                        <div class="col-xs-5" bis_skin_checked="1">
                          <label>Đường</label>
                          <br />
                          <form:input class="form-control" path="street" />
                        </div>
                      </div>
                    </div>
                    <div class="form-group">
                      <div class="col-xs-12" bis_skin_checked="1">
                        <div class="col-xs-4" bis_skin_checked="1">
                          <label>Số tầng hầm</label>
                          <form:input class="form-control" path="numberOfBasement" />
                        </div>
                        <div class="col-xs-4" bis_skin_checked="1">
                          <label>Hướng</label>
                          <form:input class="form-control" path="direction" />
                        </div>
                        <div class="col-xs-4" bis_skin_checked="1">
                          <label>Hạng</label>
                          <form:input class="form-control" path="level" />
                        </div>
                      </div>
                    </div>
                    <div class="form-group">
                      <div class="col-xs-12" bis_skin_checked="1">
                        <div class="col-xs-3" bis_skin_checked="1">
                          <label>Diện tích từ</label>
                          <form:input class="form-control" path="rentAreaFrom" />
                        </div>
                        <div class="col-xs-3" bis_skin_checked="1">
                          <label>Diện tích đến</label>
                          <form:input class="form-control" path="rentAreaTo" />
                        </div>
                        <div class="col-xs-3" bis_skin_checked="1">
                          <label>Giá thuê từ</label>
                          <form:input class="form-control" path="rentPriceFrom" />
                        </div>
                        <div class="col-xs-3" bis_skin_checked="1">
                          <label>Giá thuê đến</label>
                          <form:input class="form-control" path="rentPriceTo" />
                        </div>
                      </div>
                    </div>
                    <div class="form-group">
                      <div class="col-xs-12" bis_skin_checked="1">
                        <div class="col-xs-5" bis_skin_checked="1">
                          <label>Tên quản lý</label>
                          <form:input class="form-control" path="managerName" />
                        </div>
                        <div class="col-xs-5" bis_skin_checked="1">
                          <label>SĐT quản lý</label>
                          <form:input class="form-control" path="managerPhone" />
                        </div>
                        <div class="col-xs-2" bis_skin_checked="1">
                          <label>Chọn nhân viên</label>
                            <form:select path="staffId" class="form-control">
                                <form:option value="">---Chọn Nhân Viên---</form:option>
                                <form:options items="${staffs}"/>
                            </form:select>
                        </div>
                      </div>
                    </div>
                    <div class="form-group">
                      <div style="margin-left: 15px" class="col-xs-5" bis_skin_checked="1">
                        <form:checkboxes cssStyle="margin-left: 5px;margin-right: 5px" path="typeCode" items="${type}"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <div class="col-xs-12" bis_skin_checked="1">
                        <div class="col-xs-5" bis_skin_checked="1">
                          <button type="button" class="btn btn-pink btn-sm"
                          id="btnSearchBuilding">
                              <span
                                      class="ace-icon fa fa-search icon-on-right bigger-110"
                              ></span>
                            Tìm kiếm
                          </button>
                        </div>
                      </div>
                    </div>
                    <!-- Hidden fields for pagination -->
                    <form:hidden path="page" value="${model.page}"/>
                    <form:hidden path="maxPageItems" value="${model.maxPageItems}"/>
                    <form:hidden path="sortName" value="${model.sortName}"/>
                    <form:hidden path="sortBy" value="${model.sortBy}"/>
                  </div>
                </form:form>


              </div>
            </div>


          <div style="padding-right: 20px" class="pull-right">
            <br>
            <a href="/admin/building-edit">
              <button
                      class="btn btn-app btn-success btn-xs"
                      title="Thêm toà nhà"
              >
                <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width="20"
                        height="20"
                        fill="currentColor"
                        class="bi bi-building-add"
                        viewBox="0 0 16 16"
                >
                  <path
                          d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0"
                  />
                  <path
                          d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"
                  />
                  <path
                          d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"
                  />
                </svg>
              </button>
            </a>

            <button
                    class="btn btn-app btn-danger btn-xs"
                    title="Xoá toà nhà"
                    id="btnDeleteBuilding"
            >
              <svg
                      xmlns="http://www.w3.org/2000/svg"
                      width="20"
                      height="20"
                      fill="currentColor"
                      class="bi bi-building-dash"
                      viewBox="0 0 16 16"
              >
                <path
                        d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1"
                />
                <path
                        d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"
                />
                <path
                        d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"
                />
              </svg>
            </button>
          </div>
        </div>
        <!-- /.span -->
      </div>

      <!-- /.Table list building -->
      <div style="margin-left: 0px;width: 100%" class="row" bis_skin_checked="1">
        <div class="col-xs-12" bis_skin_checked="1">
          <br>
          <div class="hr hr-20 hr-double"></div>
          <br>
          <div class="table-responsive">
            <display:table name="model.listResult" cellspacing="0" cellpadding="0"
                          requestURI="${formUrl}" partialList="true" sort="external"
                          size="${model.totalItems}" defaultsort="2" defaultorder="ascending"
                          id="tableList" pagesize="${model.maxPageItems}"
                          export="false"
                          class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                          style="margin: 3em 0 1.5em;">
                <display:column title="<fieldset class='form-group'>
                                    <input type='checkbox' id='checkAll' class='check-box-element'>
                                    </fieldset>" class="center select-cell"
                                headerClass="center select-cell">
                    <fieldset>
                        <input type="checkbox" name="checkList" value="${tableList.id}"
                               id="checkbox_${tableList.id}" class="check-box-element"/>
                    </fieldset>
                </display:column>
                <display:column headerClass="text-left" property="name" title="Tên toà nhà"/>
                <display:column headerClass="text-left" property="floorArea" title="Diện tích sàn"/>
                <display:column headerClass="text-left" property="district" title="Quận"/>
                <display:column headerClass="text-left" property="ward" title="Phường"/>
                <display:column headerClass="text-left" property="street" title="Đường"/>
                <display:column headerClass="text-left" property="numberOfBasement" title="Số tầng hầm"/>
                <display:column headerClass="text-left" property="direction" title="Hướng"/>
                <display:column headerClass="text-left" property="level" title="Hạng"/>
                <display:column headerClass="text-left" property="rentArea" title="Diện tích thuê"/>
                <display:column headerClass="text-left" property="rentPrice" title="Giá thuê"/>
                <display:column headerClass="text-left" property="managerName" title="Tên quản lý"/>
                <display:column headerClass="text-left" property="managerPhone" title="SĐT quản lý"/>
                <display:column headerClass="col-actions" title="Thao tác">
                    <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                       title="Cập nhật toà nhà"
                       href='<c:url value="/admin/building-edit-${tableList.id}"/>'>
                        <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                    </a>
                </display:column>
            </display:table>
          </div>
        </div>
        <!-- /.span -->
      </div>
    </div>
  </div>
    <div class="modal fade"
          id="assignmentBuildingModal"
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
          <input type="hidden" id="buildingId" value="" />
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
                  id="btnAssignBuilding"
          >
            Giao toà nhà
          </button>
        </div>
      </div>
    </div>
  </div>
</div>

  <script>
    function assignmentBuilding(buildingId) {
      console.log("id toà nhà : " + buildingId);
      $("#assignmentBuildingModal").modal();
      $("#buildingId").val(buildingId);
      loadStaff(buildingId);
    }

    function loadStaff(buildingId){
      //Gui request xuong sever => ajax
      $.ajax({
        type: "GET",
        url: "/api/buildings/" + buildingId + '/staffs',
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

    $("#btnAssignBuilding").click(function (e) {
      e.preventDefault();
      var json = {};
      json["buildingId"] = $("#buildingId").val();
      var staffIds = $("#staffList")
              .find("tbody input[type = checkbox]:checked")
              .map(function () {
                return $(this).val();
              })
              .get();
      json["staffs"] = staffIds;
      console.log("Success");
      if (json["buildingId"] == "") {
        alert("Id Not Found");
      } else {
        assignBuilding(json);
      }
    });

    $("#btnDeleteBuilding").click(function (e) {
      e.preventDefault();
      var buildingIds = $("#tableList")
              .find("tbody input[type = checkbox]:checked")
              .map(function () {
                return $(this).val();
              })
              .get();
      console.log("Success...");
      if (buildingIds === "") {
        alert("No Buildings Is Selected");
      } else {
        deleteBuildings(buildingIds);
      }
    });

    function deleteBuildings(ids) {
      $.ajax({
        type: "DELETE",
        url: "/api/buildings/" + ids,
        dataType: "json",
        success: function (response) {
          console.log("Success");
          alert(response.message);
          window.location.href = "/admin/building-list";
        },
        error: function (response) {
          console.log("Failed");
          alert(response.message);
        },
      });
    }

    function deleteBuilding(id) {
      if (id === "") {
        alert("Id not found");
      } else {
        deleteBuildings(id);
      }
    }

    function assignBuilding(json) {
      $.ajax({
        type: "POST",
        url: "/api/assign",
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

    $("#btnSearchBuilding").click(function (e){
      e.preventDefault();
      // Reset page to 1 when searching
      $("#page").val(1);
      $("#listForm").submit();
    });

    // Handle sorting
    $(".sortable").click(function() {
      var currentSort = $("#sortBy").val();
      var currentSortName = $("#sortName").val();
      var newSortName = $(this).data("sort");
      
      if (currentSortName === newSortName) {
        // Toggle sort direction
        $("#sortBy").val(currentSort === "asc" ? "desc" : "asc");
      } else {
        // Set new sort column and default to ascending
        $("#sortName").val(newSortName);
        $("#sortBy").val("asc");
      }
      
      $("#listForm").submit();
    });

    // Handle pagination
    $(".pagination a").click(function(e) {
      e.preventDefault();
      var page = $(this).data("page");
      $("#page").val(page);
      $("#listForm").submit();
    });
  </script>

</body>
</html>
