package qsh.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author zl
 * @date 2019/7/26 2:16 PM
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DataTable<T> {
    private Integer draw;
    private Long recordsTotal;
    private Long recordsFiltered;
    private List<T> data;
}
