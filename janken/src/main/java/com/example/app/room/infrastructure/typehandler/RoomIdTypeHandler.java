package com.example.app.room.infrastructure.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.example.app.room.domain.RoomId;

public class RoomIdTypeHandler extends BaseTypeHandler<RoomId> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, RoomId parameter,
            JdbcType jdbcType)
            throws SQLException {
        // RoomId → DB(int)
        ps.setInt(i, parameter.value());
    }

    @Override
    public RoomId getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int value = rs.getInt(columnName);
        return rs.wasNull() ? null : new RoomId(value);
    }

    @Override
    public RoomId getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int value = rs.getInt(columnIndex);
        return rs.wasNull() ? null : new RoomId(value);
    }

    @Override
    public RoomId getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int value = cs.getInt(columnIndex);
        return cs.wasNull() ? null : new RoomId(value);
    }

}
