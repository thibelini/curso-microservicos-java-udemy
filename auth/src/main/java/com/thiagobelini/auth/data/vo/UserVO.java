package com.thiagobelini.auth.data.vo;

import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserVO implements Serializable {

    private static final long serialVersionUID = 8286642105284555648L;
    private String userName;
    private String password;

}
