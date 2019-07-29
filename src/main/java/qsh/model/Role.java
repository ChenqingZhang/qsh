package qsh.model;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

import java.util.List;

/**
 * @author zl
 * @date 2019/7/26 1:55 PM
 */
@Data
public class Role implements java.io.Serializable {

    private Long id;
    private Long pid;
    private String pname;
    private String name;
    private String remark;
    private Integer seq;

    @TableField(exist = false)
    private List<Long> resourceIds;
    @TableField(exist = false)
    private List<String> resourceNames;

}