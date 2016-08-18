package com.linuxtek.kona.app.util;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import com.linuxtek.kona.app.core.entity.KUserRole;

/**
 * KUserRole Handler
 */

public class KUserRoleHandler implements TypeHandler<Long> {

    @Override
    public void setParameter(PreparedStatement ps, int i, 
                             Long parameter, JdbcType jdbcType)
            throws SQLException {

        // parameter to set should be a bit value which mysql will understand
        // e.g. 1 = SYSTEM, 2 = ADMIN, 3 = SYSTEM,ADMIN
        ps.setLong(i, parameter);
    }

    @Override
    public Long getResult(ResultSet rs, String columnName)
            throws SQLException {
    	String s = rs.getString(columnName);
        return valueOf(s);
    }

    @Override
    public Long getResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        String s = cs.getString(columnIndex);
        return valueOf(s);
    }

    @Override
    public Long getResult(ResultSet rs, int col) throws SQLException {
        String s = rs.getString(col);
        return valueOf(s);
    }
    
    private Long valueOf(String s) {
        if (s == null) return null;
        return KUserRole.toLong(s);
    }
}

