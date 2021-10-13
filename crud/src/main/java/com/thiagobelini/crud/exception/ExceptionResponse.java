package com.thiagobelini.crud.exception;


import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse implements Serializable {
    private static final long serialVersionUID = -8709116208390798585L;

    private Date timestamp;
    private String message;
    private String details;

}
