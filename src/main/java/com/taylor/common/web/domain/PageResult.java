package com.taylor.common.web.domain;

import lombok.Data;

import java.util.List;

/**
 * 分页结果
 *
 * @author loveCamille
 * @date 2025-04-28 13:56:38
 */
@Data
public class PageResult<T> {

    /**
     * 查询数据列表
     */
    private List<T> records;

    /**
     * 数据总条数
     */
    private long total;

    /**
     * 每页显示的条数
     */
    private long size;

    /**
     * 当前页数
     */
    private long current;

}
