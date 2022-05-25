package com.fixedasset.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fixedasset.dto.InvoiceListDto;
import com.fixedasset.dto.charts.QueryCountShop;
import com.fixedasset.dto.charts.QueryCountYearWeek;
import com.fixedasset.dto.charts.QueryTotalShop;
import com.fixedasset.dto.charts.QueryTotalYearWeek;
import com.fixedasset.entity.Invoice;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface InvoiceMapper extends BaseMapper<Invoice> {
    String listQuery = "SELECT inv.*, " +
            "m.name as memberName, m.phone as memberPhone, m.address as memberAddress, " +
            "mc.name as mcName , loc.place_code as placeCode, loc.place_name as placeName" +
            " FROM invoice as inv " +
            "left join `member` as m on inv.member_id = m.id " +
            "left join `member_class` as mc on m.class  = mc.id " +
            "left join location as loc on inv.location_id = loc.id ";
    String wrapperSql = "SELECT * from ( " + listQuery + " ) AS q ${ew.customSqlSegment}";

    String selectOne = listQuery + " where inv.id = ${id}";

    String queryTotalYearWeek = "SELECT concat(year(created_at), '-', week(created_at)) as yearWeek, sum(total_amount) as total FROM invoice group by yearWeek;";
    @Select(queryTotalYearWeek)
    List<QueryTotalYearWeek> queryTotalYearWeek();

    String queryCountYearWeek = "SELECT concat(year(created_at), '-', week(created_at)) as yearWeek, count(*) as count FROM invoice group by yearWeek;";
    @Select(queryCountYearWeek)
    List<QueryCountYearWeek> queryCountYearWeek();

    String queryTotalShop = "SELECT loc.place_name as placeName, sum(total_amount) as total " +
            "FROM invoice as inv left join location as loc on inv.location_id = loc.id  group by location_id;";
    @Select(queryTotalShop)
    List<QueryTotalShop> queryTotalShop();

    String queryTotalCount = "SELECT loc.place_name as placeName,  count(inv.id) as count FROM invoice as inv left join location as loc on inv.location_id = loc.id " +
            "and loc.place_name is not null group by location_id;";
    @Select(queryTotalCount)
    List<QueryCountShop> queryCountShop();

    @Select(wrapperSql)
    Page<InvoiceListDto> listAll(Page page, @Param("ew") Wrapper queryWrapper);

    @Select(selectOne)
    InvoiceListDto selectOneId(@Param("id")Long id);
}
