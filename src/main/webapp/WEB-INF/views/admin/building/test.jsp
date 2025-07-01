<%--
  Created by IntelliJ IDEA.
  User: tuand
  Date: 7/2/2025
  Time: 12:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table
        id="buildingList"
        class="table table-striped table-bordered table-hover"
>
  <thead>
  <tr>
    <th class="center">
      <label class="pos-rel">
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
          <security:authorize access="hasRole('MANAGER')">
            <button style="width: 24px;height: 24px;border:none"
                    class="btn btn-xs btn-success"
                    onclick="assignmentBuilding(${building.id})" title = "Giao toà nhà"
            >
              <i class="ace-icon fa fa-users bigger-120"></i>
            </button>
          </security:authorize>


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

</body>
</html>
