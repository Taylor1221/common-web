package com.taylor.common.web.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

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
    private long total;

    @Schema(description = "每页显示的条数")
    private long size;

    @Schema(description = "当前页数")
    private long current;

}
