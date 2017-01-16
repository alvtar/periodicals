package dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import dao.SubscriptionDao;
import domain.Publication;
import domain.Subscription;
import domain.User;
import exception.PersistentException;

public class SubscriptionDaoImpl extends BaseDaoImpl implements SubscriptionDao {

    private static Logger logger = Logger.getLogger(SubscriptionDaoImpl.class);

    // Create new subscription
    @Override
    public Integer create(Subscription subscription) throws PersistentException {
        String sql = "INSERT INTO subscriptions (userId, publicationId, "
                + "subsYear, subsMonths, paymentSum) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            /// statement.setDate(1, new
            /// java.sql.Date(System.currentTimeMillis()));
            statement.setInt(1, subscription.getUser().getId());
            statement.setInt(2, subscription.getPublication().getId());
            statement.setInt(3, subscription.getSubsYear());
            statement.setInt(4, subscription.getSubsMonths());
            statement.setFloat(5, subscription.getPaymentSum());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                logger.error(
                        "There is no autoincremented index after trying " + "to add record into table subscriptions");
                throw new PersistentException();
            }
        } catch (SQLException | NullPointerException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                connection.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    // Read subscription by Id
    @Override
    public Subscription read(Integer id) throws PersistentException {
        String sql = "SELECT regDate, userId, publicationId, subsYear, "
                + "subsMonths, paymentSum FROM subscriptions WHERE id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Subscription subscription = null;
            if (resultSet.next()) {
                subscription = new Subscription();
                subscription.setId(id);
                subscription.setRegDate(resultSet.getDate("regDate"));

                User user = new User();
                user.setId(resultSet.getInt("userId"));
                subscription.setUser(user);

                Publication publication = new Publication();
                publication.setId(resultSet.getInt("publicationId"));
                subscription.setPublication(publication);

                subscription.setSubsYear(resultSet.getInt("subsYear"));
                subscription.setSubsMonths(resultSet.getInt("subsMonths"));
                subscription.setPaymentSum(resultSet.getFloat("paymentSum"));
            }
            return subscription;
        } catch (SQLException | NullPointerException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                connection.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    // Update subscription parameters
    @Override
    public void update(Subscription subscription) throws PersistentException {
        String sql = "UPDATE subscriptions SET userId = ?, publicationId = ?, "
                + "subsYear = ?, subsMonths=?, paymentSum=? WHERE id = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, subscription.getUser().getId());
            statement.setInt(2, subscription.getPublication().getId());
            statement.setInt(3, subscription.getSubsYear());
            statement.setInt(4, subscription.getSubsMonths());
            statement.setFloat(5, subscription.getPaymentSum());
            statement.setInt(6, subscription.getId());
            statement.executeUpdate();
        } catch (SQLException | NullPointerException e) {
            throw new PersistentException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                connection.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    // Delete subscription
    @Override
    public void delete(Integer id) throws PersistentException {
        String sql = "DELETE FROM subscriptions WHERE id = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException | NullPointerException e) {
            throw new PersistentException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                connection.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    // Read all User subscriptions
    @Override
    public List<Subscription> readByUserId(Integer userId) throws PersistentException {
        // String sql = "SELECT id, regDate, userId, publicationId, subsYear, "
        // + "subsMonths, paymentSum FROM subscriptions WHERE userId = ?";

        String sql = "SELECT s.id, s.regdate, s.subsYear, s.subsMonths, s.paymentSum, "
                + "u.login, u.fullName, u.zipCode, u.address, p.issn, p.title FROM subscriptions s "
                + "INNER JOIN users u ON s.userId = u.id INNER JOIN publications p ON s.publicationId = p.Id  "
                + "WHERE userId = ? ORDER BY regDate";

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            List<Subscription> subscriptions = new ArrayList<>();
            Subscription subscription = null;
            while (resultSet.next()) {

                subscription = new Subscription();
                subscription.setId(resultSet.getInt("id"));
                subscription.setRegDate(resultSet.getDate("regDate"));
                subscription.setSubsYear(resultSet.getInt("subsYear"));
                subscription.setSubsMonths(resultSet.getInt("subsMonths"));
                subscription.setPaymentSum(resultSet.getFloat("paymentSum"));
                subscriptions.add(subscription);

                User user = new User();
                // user.setId(resultSet.getInt("userId"));

                user.setLogin(resultSet.getString("login"));
                // user.setPassword(resultSet.getString("password"));
                // user.setRole(Role.getById(resultSet.getInt("role")));
                user.setFullName(resultSet.getString("fullName"));
                user.setZipCode(resultSet.getInt("zipCode"));
                user.setAddress(resultSet.getString("address"));

                subscription.setUser(user);

                Publication publication = new Publication();
                // publication.setId(resultSet.getInt("publicationId"));

                publication.setIssn(resultSet.getInt("issn"));
                publication.setTitle(resultSet.getString("title"));

                subscription.setPublication(publication);

            }
            return subscriptions;
        } catch (SQLException | NullPointerException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                connection.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    // Read subscription by publication Id
    @Override
    public List<Subscription> readByPublicationId(Integer publicationId) throws PersistentException {
        String sql = "SELECT id, regDate, userId, publicationId, subsYear, subsMonths, "
                + "paymentSum FROM subscriptions WHERE publicationId = ?";

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, publicationId);
            resultSet = statement.executeQuery();
            List<Subscription> subscriptions = new ArrayList<>();
            Subscription subscription = null;
            while (resultSet.next()) {

                subscription = new Subscription();
                subscription.setId(resultSet.getInt("id"));
                subscription.setRegDate(resultSet.getDate("regDate"));

                User user = new User();
                user.setId(resultSet.getInt("userId"));
                subscription.setUser(user);

                Publication publication = new Publication();
                publication.setId(publicationId);
                subscription.setPublication(publication);

                subscription.setSubsYear(resultSet.getInt("subsYear"));
                subscription.setSubsMonths(resultSet.getInt("subsMonths"));
                subscription.setPaymentSum(resultSet.getFloat("paymentSum"));
                subscriptions.add(subscription);
            }
            return subscriptions;
        } catch (SQLException | NullPointerException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                connection.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    // List all subscriptions with specific year
    @Override
    public List<Subscription> readBySubsYear(Integer subsYear) throws PersistentException {
        String sql = "SELECT id, regDate, userId, publicationId, subsYear, "
                + "subsMonths, paymentSum FROM subscriptions WHERE subYear = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, subsYear);
            resultSet = statement.executeQuery();
            List<Subscription> subscriptions = new ArrayList<>();
            Subscription subscription = null;
            while (resultSet.next()) {
                subscription = new Subscription();
                subscription.setId(resultSet.getInt("id"));
                subscription.setRegDate(resultSet.getDate("regDate"));

                User user = new User();
                user.setId(resultSet.getInt("userId"));
                subscription.setUser(user);

                Publication publication = new Publication();
                publication.setId(resultSet.getInt("publicationId"));
                subscription.setPublication(publication);

                subscription.setSubsYear(subsYear);
                subscription.setSubsMonths(resultSet.getInt("subsMonths"));
                subscription.setPaymentSum(resultSet.getFloat("paymentSum"));
                subscriptions.add(subscription);
            }
            return subscriptions;
        } catch (SQLException | NullPointerException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                connection.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    // List all suscriptions of all users
    @Override
    public List<Subscription> read() throws PersistentException {

        String sql = "SELECT s.id, s.regdate, s.subsYear, s.subsMonths, s.paymentSum, "
                + "u.login, u.fullName, u.zipCode, u.address, p.issn, p.title FROM subscriptions s "
                + "INNER JOIN users u ON s.userId = u.id INNER JOIN publications p ON s.publicationId = p.Id  "
                + "ORDER BY regDate";

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            List<Subscription> subscriptions = new ArrayList<>();
            Subscription subscription = null;
            while (resultSet.next()) {
                subscription = new Subscription();
                subscription.setId(resultSet.getInt("id"));
                subscription.setRegDate(resultSet.getDate("regDate"));
                subscription.setSubsYear(resultSet.getInt("subsYear"));
                subscription.setSubsMonths(resultSet.getInt("subsMonths"));
                subscription.setPaymentSum(resultSet.getFloat("paymentSum"));
                subscriptions.add(subscription);

                User user = new User();

                user.setLogin(resultSet.getString("login"));
                // user.setPassword(resultSet.getString("password"));
                // user.setRole(Role.getById(resultSet.getInt("role")));
                user.setFullName(resultSet.getString("fullName"));
                user.setZipCode(resultSet.getInt("zipCode"));
                user.setAddress(resultSet.getString("address"));

                subscription.setUser(user);

                Publication publication = new Publication();

                publication.setIssn(resultSet.getInt("issn"));
                publication.setTitle(resultSet.getString("title"));

                subscription.setPublication(publication);

            }
            return subscriptions;
        } catch (SQLException | NullPointerException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                connection.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }
}
