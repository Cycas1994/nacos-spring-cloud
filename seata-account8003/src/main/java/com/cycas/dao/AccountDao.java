package com.cycas.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDao {

    @Update({
            "update account set ",
            "used = used + #{money,jdbcType=INTEGER},",
            "residue = residue - #{money,jdbcType=INTEGER}",
            "where user_id = #{userId,jdbcType=BIGINT}"
    })
    void decrease(@Param("userId") Long userId, @Param("money") Integer money);

}
