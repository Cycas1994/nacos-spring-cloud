package com.cycas.dao;

import com.cycas.pojo.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao {

    @Insert({
            "insert into order (",
            "id, user_id,",
            "commodity_code, count,",
            "money, status)",
            "values( ",
            "#{id,jdbcType=INTEGER}, #{userId,jdbcType=VARCHAR},",
            "#{commodityCode,jdbcType=VARCHAR}, #{count,jdbcType=INTEGER},",
            "#{money,jdbcType=INTEGER}, 'N')",
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(Order order);

    @Update({
            "update order set ",
            "status = 'Y'",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateStatus(Integer id);

}
