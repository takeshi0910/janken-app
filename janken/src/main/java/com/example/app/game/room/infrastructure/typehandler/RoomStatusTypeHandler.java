package com.example.app.game.room.infrastructure.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import com.example.app.game.room.domain.RoomStatus;

/** 
 * RoomStatus用 TypeHandler
 * 
 * @author takeshi.kashiwagi
 */
@MappedTypes(RoomStatus.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class RoomStatusTypeHandler extends BaseTypeHandler<RoomStatus> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, RoomStatus status, JdbcType jdbcType)
            throws SQLException {
        ps.setString(i, status.name());
    }

    @Override
    public RoomStatus getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return value == null ? null : RoomStatus.valueOf(value);
    }

    @Override
    public RoomStatus getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return value == null ? null : RoomStatus.valueOf(value);
    }

    @Override
    public RoomStatus getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return value == null ? null : RoomStatus.valueOf(value);
    }
}
