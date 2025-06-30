<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Đăng ký</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        .gradient-custom {
            background: #35bf76;
        }
        .form-outline .form-control {
            background-color: #fff;
            color: #000;
        }
        .form-outline .form-label {
            color: #fff;
        }
        .error {
            font-size: 0.9em;
            margin-top: 0.25rem;
        }
    </style>
</head>
<body class="gradient-custom">
<div class="container">
    <section class="vh-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card text-white" style="border-radius: 1rem; background-color: #35bf76;">
                    <div class="card-body p-2 px-5 text-center">
                        <div class="md-5 md-4 mt-4 pb-2">
                            <h2 class="fw-bold mb-2 text-uppercase">Create an account</h2>
                            <p class="text-white-50 mb-2">Please enter your Information</p>

                            <form id="registerForm" action="/api/register" method="post">
                                <div class="form-outline form-white mb-2">
                                    <label class="form-label" for="fullName">Fullname</label>
                                    <input type="text" id="fullName" name="fullName" class="form-control form-control-lg" required />
                                    <span class="error text-danger" id="fullNameError"></span>
                                </div>

                                <div class="form-outline form-white mb-2">
                                    <label class="form-label" for="email">Email</label>
                                    <input type="email" id="email" name="email" class="form-control form-control-lg" required />
                                    <span class="error text-danger" id="emailError"></span>
                                </div>

                                <div class="form-outline form-white mb-2">
                                    <label class="form-label" for="userName">Username</label>
                                    <input type="text" id="userName" name="userName" class="form-control form-control-lg" required />
                                    <span class="error text-danger" id="userNameError"></span>
                                </div>

                                <div class="form-outline form-white mb-2">
                                    <label class="form-label" for="password">Password</label>
                                    <input type="password" id="password" name="password" class="form-control form-control-lg" required />
                                    <span class="error text-danger" id="passwordError"></span>
                                </div>

                                <div class="form-outline form-white mb-2">
                                    <label class="form-label" for="repeatPassword">Repeat your password</label>
                                    <input type="password" id="repeatPassword" name="repeatPassword" class="form-control form-control-lg" required />
                                    <span class="error text-danger" id="repeatPasswordError"></span>
                                </div>

                                <button class="btn btn-outline-light btn-lg px-5" type="submit">Register</button>
                            </form>

                            <div class="d-flex justify-content-center text-center mt-2 pt-1">
                                <a href="#!" class="login-extension text-white"><i class="fab fa-facebook-f fa-lg"></i></a>
                                <a href="#!" class="login-extension text-white"><i class="fab fa-twitter fa-lg mx-4 px-2"></i></a>
                                <a href="#!" class="login-extension text-white"><i class="fab fa-google fa-lg"></i></a>
                            </div>

                            <p class="text-center text-muted mt-2 mb-0">
                                Have already an account?
                                <a href="${pageContext.request.contextPath}/login" class="fw-bold text-body"><u style="color: white;">Login here</u></a>
                            </p>

                            <div class="mt-3">
                                <span id="successMessage" class="text-success"></span>
                                <span id="generalError" class="text-danger"></span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>

<script>
    $(document).ready(function() {
        const $registerForm = $('#registerForm');
        const $fullName = $('#fullName');
        const $email = $('#email');
        const $userName = $('#userName');
        const $password = $('#password');
        const $repeatPassword = $('#repeatPassword');
        const $fullNameError = $('#fullNameError');
        const $emailError = $('#emailError');
        const $userNameError = $('#userNameError');
        const $passwordError = $('#passwordError');
        const $repeatPasswordError = $('#repeatPasswordError');
        const $successMessage = $('#successMessage');
        const $generalError = $('#generalError');

        $registerForm.on('submit', function(event) {
            event.preventDefault();

            $fullNameError.text('');
            $emailError.text('');
            $userNameError.text('');
            $passwordError.text('');
            $repeatPasswordError.text('');
            $successMessage.text('');
            $generalError.text('');

            const formData = {
                fullName: $fullName.val(),
                email: $email.val(),
                userName: $userName.val(),
                password: $password.val(),
                repeatPassword: $repeatPassword.val()
            };

            if (formData.password !== formData.repeatPassword) {
                $repeatPasswordError.text('Mật khẩu nhập lại không khớp.');
                return;
            }

            $.ajax({
                url: '/api/register',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(formData),
                success: function(response) {
                    $successMessage.text(response.message);
                    alert("Đăng kí thành công, chuyển hướng về Đăng nhập");
                    window.location.href = '${pageContext.request.contextPath}/login';
                },
                error: function(xhr) {
                    const response = xhr.responseJSON;
                    if (response && response.data) {
                        if (response.data.fullName) $fullNameError.text(response.data.fullName);
                        if (response.data.email) $emailError.text(response.data.email);
                        if (response.data.userName) $userNameError.text(response.data.userName);
                        if (response.data.password) $passwordError.text(response.data.password);
                        if (response.data.repeatPassword) $repeatPasswordError.text(response.data.repeatPassword);
                    } else {
                        $generalError.text(response.message || 'Đã có lỗi xảy ra.');
                    }
                }
            });
        });
    });
</script>
</body>
</html>