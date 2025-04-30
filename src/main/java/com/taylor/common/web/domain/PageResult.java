package com.taylor.common.web.domain;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 分页结果
 *
 * @author loveCamille
 * @date 2025-04-28 13:56:38
 */
@Data
@Schema(description = "通用分页结果")
public class PageResult<T> {

    @Schema(description = "查询数据列表")
    private List<T> records;

    @Schema(description = "数据总条数")
    private Long total;

    @Schema(description = "每页显示的条数")
    private Long size;

    @Schema(description = "当前页数")
    private Long current;

    /**
     * 将 MyBatis-Plus的Page对象转成 PageResult
     * @author loveCamille
     * @param page yBatis-Plus的Page对象
     * @return {@link PageResult<T>} PageResult
    */
    public static <T> PageResult<T> of(IPage<T> page) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setRecords(page.getRecords());
        pageResult.setTotal(page.getTotal());
        pageResult.setSize(page.getSize());
        pageResult.setCurrent(page.getCurrent());
        return pageResult;
    }

    public <V> PageResult<?> copyProperties(Class<V> clazz) {
        PageResult<V> result = new PageResult<>();
        BeanUtils.copyProperties(this, result);
        return result;
    }

}
