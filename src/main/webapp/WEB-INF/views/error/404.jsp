<%--
  Created by IntelliJ IDEA.
  User: tuand
  Date: 6/26/2025
  Time: 1:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>404 Page Not Found</title>
</head>
<style>
    /* Reset mặc định để loại bỏ margin và padding */
    html, body {
        margin: 0;
        padding: 0;
        width: 100%;
        height: 100%;
        overflow: hidden; /* Ngăn thanh cuộn xuất hiện */
    }

    /* CSS cho thẻ img */
    img {
        width: 100vw; /* 100% chiều rộng viewport */
        height: 100vh; /* 100% chiều cao viewport */
        object-fit: cover; /* Đảm bảo hình ảnh phủ kín mà không bị méo */
        position: absolute; /* Đặt vị trí tuyệt đối để chiếm toàn màn hình */
        top: 0;
        left: 0;
        z-index: -1; /* Đặt hình ảnh ở nền nếu có nội dung khác */
    }
</style>
<body>
<div>
    <img src="/img/404.jpg" alt="404 Error Image" title="404 Page Not Found" id="view">
</div>

</body>
</html>
