package com.taylor.common.web.domain;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 分页查询请求
 *
 * @author loveCamille
 * @date 2025-04-28 14:10:47
 */
@Data
@Schema(description = "通用分页请求")
public class PageRequest<T> {

    @Schema(description = "当前页数")
    private Long current = 1L;

    @Schema(description = "每页大小，默认 10")
    private Long size = 10L;

    @Schema(description = "查询条件")
    private T queryCondition;

    @Schema(description = "排序列表")
    private List<OrderItem> orderItems;

    /**
     * 转换为 MyBatis-Plus 的 Page 对象，方便直接用于 Mapper 查询
     * @author loveCamille
     * @return {@link Page<T>} MyBatis-Plus 的 Page 对象
    */
    public Page<T> toMyBatisPage() {
        Page<T> page = new Page<>(current, size);
        if (orderItems != null && !orderItems.isEmpty()) {
            page.setOrders(orderItems);
        }
        return page;
    }

}
