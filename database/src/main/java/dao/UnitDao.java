package dao;

import entity.Option;
import entity.Unit;
import enumeratiom.BoilingPoint;
import enumeratiom.UnitRange;
import exception.DaoException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UnitDao {

    private static final UnitDao INSTANCE = new UnitDao();

    private static final String GET_ALL = "SELECT id, name, range, boiling_point, ref_capacity, " +
            "weight, length, width, height, price " +
            "FROM ref_storage.unit";

    public static UnitDao getInstance() {
        return INSTANCE;
    }

    public List<Unit> getAll() {
        List<Unit> units = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Unit unit = getUnitFromResultSet(resultSet);
                unit.setOptionList(getOptionList(unit));
                units.add(unit);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return units;
    }

    private List<Option> getOptionList(@NonNull Unit unit) {
        return OptionDao.getInstance().getAllByUnit(unit.getId());
    }

    private Unit getUnitFromResultSet(@NonNull ResultSet resultSet) throws SQLException {
        return Unit.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .range(UnitRange.valueOf(resultSet.getString("range")))
                .boilingPoint(BoilingPoint.valueOf(resultSet.getString("boiling_point")))
                .refCapacity(resultSet.getDouble("ref_capacity"))
                .weight(resultSet.getInt("weight"))
                .length(resultSet.getInt("length"))
                .width(resultSet.getInt("width"))
                .height(resultSet.getInt("height"))
                .price(resultSet.getInt("price"))
                .build();
    }

}