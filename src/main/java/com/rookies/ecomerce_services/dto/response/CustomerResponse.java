package com.rookies.ecomerce_services.dto.response;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    private Long customerId;
    private String email;
    private String firstName;
    private String lastName;
    private String avatar;
    private Date createdOn;
    private Date lastUpdatedOn;
}
