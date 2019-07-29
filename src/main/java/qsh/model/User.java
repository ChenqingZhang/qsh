package qsh.model;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author zl
 * @date 2019/7/26 1:57 PM
 */
@Data
public class User implements java.io.Serializable {

    private Long id;
    private String username;
    private String name;
    private String password;
    private Date createTime;
    private Date modifyTime;
    @TableField(exist = false)
    private List<Long> roleIds;
    @TableField(exist = false)
    private List<String> roleNames;

    private String oldPassword;

}