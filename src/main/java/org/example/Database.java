package org.example;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.example.booking.Booking;

public class Database {
  ResultSet resultSet;
  PreparedStatement statement;
  Connection conn = null;

  public Database() {
    connectToDb();
  }

  void connectToDb() {
    try {
      conn =
          DriverManager.getConnection(
              "jdbc:mysql://161.97.144.27:8012/holidayMaker?user=root&password=practicespiderclimb&serverTimezone=UTC");

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void createNewUser(
      String firstName,
      String lastName,
      String email,
      String contactNumber,
      String personalNumber,
      String address,
      LocalDate dob) {
    try {
      statement =
          conn.prepareStatement(
              "INSERT INTO users SET firstName = ?, lastName = ?, email = ?, contactNumber = ?, personalNumber = ?, address = ?, dob = ?");
      statement.setString(1, firstName);
      statement.setString(2, lastName);
      statement.setString(3, email);
      statement.setString(4, contactNumber);
      statement.setString(5, personalNumber);
      statement.setString(6, address);
      statement.setDate(7, Date.valueOf(dob));
      statement.executeUpdate();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void deleteAddedUserAsTest(String firstName) {
    try {
      statement = conn.prepareStatement("DELETE FROM users WHERE firstName = ? ");
      statement.setString(1, firstName);
      statement.executeUpdate();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public ArrayList<User> listOfAllUsers() {
    getAllUsers();
    ArrayList<User> tempList = new ArrayList<User>();
    try {
      while (resultSet.next()) {

        java.sql.Date date = resultSet.getDate("dob");
        LocalDate localDate = date.toLocalDate();
        tempList.add(
            new User(
                resultSet.getInt("id"),
                resultSet.getString("firstName"),
                resultSet.getString("lastName"),
                resultSet.getString("email"),
                resultSet.getString("contactNumber"),
                resultSet.getString("personalNumber"),
                resultSet.getString("address"),
                localDate));
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return tempList;
  }

  void getAllUsers() {
    try {
      statement = conn.prepareStatement("SELECT * FROM users");
      resultSet = statement.executeQuery();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public User searchedUserByPersonalNumber(String personalNumber) {
    getUserByPersonalNumber(personalNumber);
    User fetchedUser;
    try {
      if (resultSet.next()) {
        java.sql.Date date = resultSet.getDate("dob");
        LocalDate localDate = date.toLocalDate();
        fetchedUser =
            new User(
                resultSet.getInt("id"),
                resultSet.getString("firstName"),
                resultSet.getString("lastName"),
                resultSet.getString("email"),
                resultSet.getString("contactNumber"),
                resultSet.getString("personalNumber"),
                resultSet.getString("address"),
                localDate);
        return fetchedUser;
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    ;
    return null;
  }

  public void getUserByPersonalNumber(String personalNumber) {
    try {
      statement = conn.prepareStatement("SELECT * FROM users WHERE personalNumber = ?");
      statement.setString(1, personalNumber);
      resultSet = statement.executeQuery();
      System.out.println("fetchedUser by personal no is " + resultSet);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public ArrayList<Room> listOfAllRooms() {
    getAllRooms();
    ArrayList<Room> tempList = new ArrayList<Room>();
    try {
      while (resultSet.next()) {
        tempList.add(
            new Room(
                resultSet.getInt("roomId"),
                resultSet.getInt("roomNumber"),
                resultSet.getString("RoomType"),
                resultSet.getDouble("roomPrice")));
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return tempList;
  }

  private void getAllRooms() {
    try {
      statement = conn.prepareStatement("SELECT * FROM rooms");
      resultSet = statement.executeQuery();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void createNewEvent(String eventName, double eventPrice) {
    try {
      statement = conn.prepareStatement("INSERT INTO events SET eventName = ?, eventPrice = ?");
      statement.setString(1, eventName);
      statement.setDouble(2, eventPrice);
      statement.executeUpdate();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public ArrayList<Event> listOfAllEvents() {
    getAllEvents();
    ArrayList<Event> tempList = new ArrayList<Event>();
    try {
      while (resultSet.next()) {
        tempList.add(
            new Event(
                resultSet.getInt("eventId"),
                resultSet.getString("eventName"),
                resultSet.getDouble("eventPrice")));
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return tempList;
  }

  void getAllEvents() {
    try {
      statement = conn.prepareStatement("SELECT * FROM events");
      resultSet = statement.executeQuery();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  int createNewBooking(
      LocalDate date,
      boolean paid,
      int userId,
      LocalDate startDate,
      LocalDate endDate,
      int roomId,
      int travelersNo,
      double totalPrice,
      int packageId,
      int eventId) {
    try {
      PreparedStatement statement =
          conn.prepareStatement(
              "INSERT INTO bookings SET bookingDate = ?, paid = ?, userId = ?, tripStartDate = ?, tripEndDate = ?, roomId = ?, noOfTravellers = ?, totalPrice = ?, packageId = ?, eventId = ?",
              Statement.RETURN_GENERATED_KEYS);
      statement.setDate(1, Date.valueOf(date));
      statement.setBoolean(2, paid);
      statement.setInt(3, userId);
      statement.setDate(4, Date.valueOf(startDate));
      statement.setDate(5, Date.valueOf(endDate));
      statement.setInt(6, roomId);
      statement.setInt(7, travelersNo);
      statement.setDouble(8, totalPrice);
      statement.setInt(9, packageId);
      statement.setInt(10, eventId);

      int rowsAffected = statement.executeUpdate();

      if (rowsAffected == 1) {
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            return generatedKeys.getInt(1);
        }
      }
      return -1;
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  List<Booking> getAllBookings() {

    List<Booking> bookingList = new ArrayList<>();

    try {
      statement = conn.prepareStatement("SELECT * FROM bookings");
      resultSet = statement.executeQuery();

      while (resultSet.next()) {
        Booking booking = createBookingFromResultSet(resultSet);
        bookingList.add(booking);
      }
      return bookingList;

    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  public Booking createBookingFromResultSet(ResultSet resultSet) throws SQLException {
    if (resultSet == null) {
      return null;
    }

    Booking booking =
        new Booking(
            resultSet.getDate("bookingDate").toLocalDate(),
            resultSet.getBoolean("paid"),
            resultSet.getInt("userId"),
            resultSet.getDate("tripStartDate").toLocalDate(),
            resultSet.getDate("tripEndDate").toLocalDate(),
            resultSet.getInt("roomId"),
            resultSet.getInt("noOfTravellers"),
            resultSet.getDouble("totalPrice"),
            resultSet.getInt("packageId"),
            resultSet.getInt("eventId"));

    booking.setBookingId(resultSet.getInt(1));

    return booking;
  }

  Booking findBookingByUserId(int userId) {

    try {
      statement = conn.prepareStatement("SELECT * FROM bookings WHERE userId = ?");
      statement.setInt(1, userId);
      resultSet = statement.executeQuery();
      if (resultSet.next()) {
          return createBookingFromResultSet(resultSet);
      }
      return null;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  Booking updateBooking(int bookingId, String columnName, Object updatedValue) {
    try {
      String updateQuery = "UPDATE bookings SET " + columnName + " = ? WHERE bookingId = ?";
      statement = conn.prepareStatement(updateQuery);

      if (updatedValue instanceof Integer) {
        statement.setInt(1, (Integer) updatedValue);
      } else if (updatedValue instanceof LocalDate) {
        statement.setDate(1, Date.valueOf((LocalDate) updatedValue));
      } else if (updatedValue instanceof Boolean) {
        statement.setBoolean(1, (Boolean) updatedValue);
      } else if (updatedValue instanceof Double) {
        statement.setDouble(1, (Double) updatedValue);
      } else {
        throw new IllegalArgumentException("Unsupported data type for updatedValue");
      }

      statement.setInt(2, bookingId);
      int rowsAffected = statement.executeUpdate();

      if (rowsAffected == 1) {
        return getBooking(bookingId);
      } else {
        return null;
      }

    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  Booking getBooking(int bookingId) {
    try {
      statement = conn.prepareStatement("SELECT * FROM bookings WHERE bookingId = ?");
      statement.setInt(1, bookingId);
      resultSet = statement.executeQuery();

      if (resultSet.next()) {
        return createBookingFromResultSet(resultSet);
      } else {
        return null;
      }

    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  public boolean deleteBooking(int bookingId) {
    try {
      statement = conn.prepareStatement("DELETE FROM bookings WHERE bookingId = ?");
      statement.setInt(1, bookingId);
      int rawsAffected = statement.executeUpdate();

      return rawsAffected == 1;

    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }
}
