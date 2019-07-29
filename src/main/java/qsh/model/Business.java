package qsh.model;

import lombok.Data;

import java.util.Date;

/**
 * @author zl
 * @date 2019/7/28 9:47 AM
 */
@Data
public class Business implements java.io.Serializable {
    private Long id;
    private Date createTime;
    private Date modifyTime;
    private Date signinTime;
    private String mobile;
    private String item;
    private Integer signinStatus;
    private Integer businessType;
}