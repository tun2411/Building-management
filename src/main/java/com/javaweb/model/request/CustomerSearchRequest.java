package com.javaweb.model.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerSearchRequest {
    private String fullName;
    private String phone;
    private String email;
    private String status;
    private Long staffId;
    private Long is_Active;

}
