package qsh.model;

import lombok.Data;

import java.util.List;

/**
 * @author zl
 * @date 2019/7/26 1:11 PM
 */
@Data

public class Menu implements java.io.Serializable {
    private Long id;
    private String text;
    private String url;
    private List<Menu> children;
}
