<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<html>
<head>

  <title>Danh sách toà nhà</title>

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
                          d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"
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
                        d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"
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
          <table
                  id="buildingList"
                  class="table table-striped table-bordered table-hover"
          >
            <thead>
            <tr>
              <th class="center">
                <label class="pos-rel">
                  <input
                          type="checkbox"
                          class="ace"
                          value="1"
                  />
                  <span class="lbl"></span>
                </label>
              </th>
              <th>Tên toà nhà</th>
              <th>Địa chỉ</th>
              <th>Số tầng hầm</th>

              <th>Tên quản lý</th>
              <th>SĐT quản lý</th>

              <th>Diện tích sàn</th>
              <th>Diện tích thuê</th>
              <th>Diện tích trống</th>
              <th>Giá thuê</th>
              <th>Phí dịch vụ</th>
              <th>Phí môi giới</th>
              <th>Thao tác</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var = "building" items="${buildingSearchResponses}">
              <tr>
                <td class="center">
                  <label class="pos-rel">
                    <input type="checkbox" class="ace" value="${building.id}" />
                    <span class="lbl"></span>
                  </label>
                </td>

                <td>${building.name}</td>
                <td>${building.address}</td>
                <td>${building.numberOfBasement}</td>
                <td>${building.managerName}</td>

                <td>${building.managerPhone}</td>
                <td>${building.floorArea}</td>
                <td>${building.rentArea}</td>
                <td></td>
                <td>${building.rentPrice}</td>
                <td>${building.serviceFee}</td>
                <td>${building.brokerageFee}</td>

                <td>
                  <div
                          class="hidden-sm hidden-xs btn-group"
                          bis_skin_checked="1"
                  >
                    <button style="width: 24px;height: 24px;border:none"
                            class="btn btn-xs btn-success"
                            onclick="assignmentBuilding(${building.id})" title = "Giao toà nhà"
                    >
                      <i class="ace-icon fa fa-users bigger-120"></i>
                    </button>
                    <a href="/admin/building-edit-${building.id} " style="width: 24px;height: 24px;border:none"
                       class="btn btn-xs btn-info" title="Sửa thông tin">
                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                    </a>

                      <button style="width: 24px;height: 24px;border:none"
                              class="btn btn-xs btn-danger"
                              onclick="deleteBuilding(${building.id})" title="Xoá toà nhà"
                      >
                        <i class="ace-icon fa fa-trash-o bigger-120"></i>
                      </button>



                  </div>
                </td>
              </tr>
            </c:forEach>

            </tbody>
          </table>
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
          //console.log(url);
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
      var buildingIds = $("#buildingList")
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
      //Gui request xuong sever => ajax
      $.ajax({
        type: "DELETE",
        url: "/api/buildings/" + ids,
        //data: JSON.stringify(json),
        dataType: "json",
        //contentType: "application/json",
        success: function (response) {
          console.log("Success");
          alert(response.message);
          window.location.href = "/admin/building-list";
        },
        error: function (response) {
          console.log("Failed");
          //console.log(url);
          alert(response.message);
        },
      });
    }

    function deleteBuilding(id) {
      if (id == "") {
        alert("Id not found");
      } else {
        deleteBuildings(id);
      }
    }

    function assignBuilding(json) {
      //Gui request xuong sever => ajax
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
      $("#listForm").submit();
    })

  </script>

</body>
</html>
