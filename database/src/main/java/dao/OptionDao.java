package dao;

import entity.Option;
import exception.DaoException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OptionDao {

    private static final OptionDao INSTANCE = new OptionDao();

    private static final String GET_ALL_BY_UNIT_ID = "SELECT id, name, price FROM ref_storage.option" +
            " LEFT JOIN ref_storage.unit_option o on option.id = o.option_id " +
            "WHERE unit_id = ?";

    public static OptionDao getInstance() {
        return INSTANCE;
    }

    public List<Option> getAllByUnit(Integer unitId) {
        List<Option> options = new ArrayList<>();
        getAllById(unitId, options, GET_ALL_BY_UNIT_ID);
        return options;
    }

    private void getAllById(Integer unitOrProductId, List<Option> options, String getAllById) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getAllById)) {
            preparedStatement.setInt(1, unitOrProductId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Option option = getOptionFromResultSet(resultSet);
                options.add(option);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Option getOptionFromResultSet(ResultSet resultSet) throws SQLException {
        return Option.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .price(resultSet.getInt("price"))
                .build();
    }

}