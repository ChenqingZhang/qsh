package qsh.model;

import lombok.Data;

import java.util.Date;

/**
 * @author zl
 * @date 2019/7/28 9:40 AM
 */
@Data
public class Member implements java.io.Serializable {
    private Long id;
    private Date createTime;
    private Date modifyTime;
    private Date memberTime;
    private String company;
    private String principal;
    private String mobile;
    private String address;
    private Integer memberStatus;

}