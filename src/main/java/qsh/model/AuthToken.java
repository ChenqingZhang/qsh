package qsh.model;

import lombok.Data;

import java.util.List;

/**
 * @author zl
 * @date 2019/7/26 1:38 PM
 */
@Data
public class AuthToken {
    private String token;
    private Long userId;
    private List<String> resourceList;
    private String name;

}
