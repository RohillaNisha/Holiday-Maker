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
                        resultSet.getDouble("roomPrice"),
                        resultSet.getInt("roomCapacity")));
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

  public List<Event> getAllEventsNew() {
    List<Event> eventList = new ArrayList<>();
    try {
      statement = conn.prepareStatement("SELECT * FROM events");
      resultSet = statement.executeQuery();
      while (resultSet.next()) {
        Event event = createEventFromResultSet(resultSet);
        eventList.add(event);
      }
      return eventList;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
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
    ArrayList<Event> eventList = new ArrayList<Event>();
    try {
      while (resultSet.next()) {
        Event event = createEventFromResultSet(resultSet);
        eventList.add(event);
      }
      return eventList;

    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  void getAllEvents() {
    try {
      statement = conn.prepareStatement("SELECT * FROM events");
      resultSet = statement.executeQuery();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public Event createEventFromResultSet(ResultSet resultSet) throws SQLException {
    if (resultSet == null) {
      return null;
    }

    Event event =
            new Event(
                    resultSet.getString("eventName"),
                    resultSet.getDouble("eventPrice"),
                    resultSet.getInt("packageId"),
                    resultSet.getDate("startDate").toLocalDate(),
                    resultSet.getDate("endDate").toLocalDate());

    event.setEventId(resultSet.getInt(1));

    return event;
  }

  public List<Event> listOfEventsByStartDate(Date startDate, Date endDate) {

    List<Event> eventList = new ArrayList<>();

    try {
      statement =
              conn.prepareStatement(" SELECT * FROM events WHERE startDate >= ? && endDate <= ?");
      statement.setDate(1, startDate);
      statement.setDate(2, endDate);
      resultSet = statement.executeQuery();
      while (resultSet.next()) {
        Event event = createEventFromResultSet(resultSet);
        eventList.add(event);
      }
      return eventList;

    } catch (SQLException e) {
      e.printStackTrace();
      return null;
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
                    resultSet.getInt("noOfTravellers"),
                    resultSet.getDouble("totalPrice"));

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

  public ArrayList<Package> listOfAllPackages() {
    getAllPackages();
    ArrayList<Package> packageList1 = new ArrayList<>();
    try {
      while (resultSet.next()) {
        packageList1.add(
                new Package(
                        resultSet.getInt("packageId"),
                        resultSet.getString("packageType"),
                        resultSet.getString("packageName")));
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return packageList1;
  }

  private void getAllPackages() {
    try {
      statement = conn.prepareStatement("SELECT * FROM packages");
      resultSet = statement.executeQuery();

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public Package fetchedPackageByPackageType(String packageType) {
    getPackageByPackageType(packageType);
    Package fetchedPackage;
    try {
      if (resultSet.next()) {
        fetchedPackage =
                new Package(
                        resultSet.getInt("packageId"),
                        resultSet.getString("packageType"),
                        resultSet.getString("packageName"));
        return fetchedPackage;
      }

    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }

  public void getPackageByPackageType(String packageType) {
    try {
      statement = conn.prepareStatement("SELECT * FROM packages WHERE packageType = ?");
      statement.setString(1, packageType);
      resultSet = statement.executeQuery();
      // System.out.println("The fetchedPackage by packageType is " + resultSet);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public Package fetchedPackageByPackageName(String packageName) {
    getPackageByPackageName(packageName);
    Package fetchedPackage;
    try {
      if (resultSet.next()) {
        fetchedPackage =
                new Package(
                        resultSet.getInt("packageId"),
                        resultSet.getString("packageType"),
                        resultSet.getString("packageName"));
        return fetchedPackage;
      }

    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }

  public void getPackageByPackageName(String packageName) {
    try {
      statement = conn.prepareStatement("SELECT * FROM packages WHERE packageName = ?");
      statement.setString(1, packageName);
      resultSet = statement.executeQuery();
      // System.out.println("The fetchedPackage by packageName is " + resultSet);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public void createNewPackage(int packageId, String packageType, String packageName) {
    try {
      statement =
              conn.prepareStatement(
                      "INSERT INTO packages SET packageId = ?, packageName = ?, eventPrice = ?");
      statement.setInt(1, packageId);
      statement.setString(2, packageType);

      statement.setString(3, packageName);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public int createNewBooking(
          LocalDate date,
          boolean paid,
          int userId,
          LocalDate startDate,
          LocalDate endDate,
          int travelersNo,
          double totalPrice,
          List<Integer> roomIds,
          List<Integer> eventIds) {
    try {
      int bookingId = -1;
      PreparedStatement statement =
              conn.prepareStatement(
                      "INSERT INTO bookings SET bookingDate = ?, paid = ?, userId = ?, tripStartDate = ?, tripEndDate = ?, noOfTravellers = ?, totalPrice = ?",
                      Statement.RETURN_GENERATED_KEYS);
      statement.setDate(1, Date.valueOf(date));
      statement.setBoolean(2, paid);
      statement.setInt(3, userId);
      statement.setDate(4, Date.valueOf(startDate));
      statement.setDate(5, Date.valueOf(endDate));
      statement.setInt(6, travelersNo);
      statement.setDouble(7, totalPrice);

      int rowsAffected = statement.executeUpdate();

      if (rowsAffected == 0) {
        return -1;
      }

      try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          bookingId = generatedKeys.getInt(1);
          try {
            PreparedStatement statment =
                    conn.prepareStatement(
                            "INSERT INTO reservations SET bookingId = ?, roomId = ?",
                            Statement.RETURN_GENERATED_KEYS);
            for (int roomId : roomIds) {
              statment.setInt(1, bookingId);
              statment.setInt(2, roomId);
              statment.addBatch();
            }
            statment.executeBatch();
            //return bookingId;

          } catch (SQLException e) {
            e.printStackTrace();
            return -1;
          }
          try {
            PreparedStatement eventStatement =
                    conn.prepareStatement(
                            "INSERT INTO eventsReservations SET bookingId = ?, eventId = ?",
                            Statement.RETURN_GENERATED_KEYS
                    );
            for (int eventId : eventIds) {
              eventStatement.setInt(1, bookingId);
              eventStatement.setInt(2, eventId);
              eventStatement.addBatch();
            }
            eventStatement.executeBatch();
          } catch (SQLException e) {
            e.printStackTrace();
            return -1;
          }
          return bookingId;
        }
      }

      return -1;
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  public Booking getBookingWithDetails(int bookingId) {
    Booking booking = null;
    List<Integer> roomIds = new ArrayList<>();
    List<Integer> eventIds = new ArrayList();

    try {
      PreparedStatement bookingStatement = conn.prepareStatement("SELECT * FROM bookings WHERE bookingId = ?");
      bookingStatement.setInt(1, bookingId);
      ResultSet bookingResultSet = bookingStatement.executeQuery();

      if (bookingResultSet.next()) {
        booking = createBookingFromResultSet(bookingResultSet);

        PreparedStatement roomStatement = conn.prepareStatement("SELECT roomId FROM reservations WHERE bookingId = ?");
        roomStatement.setInt(1, bookingId);
        ResultSet roomResultSet = roomStatement.executeQuery();

        while (roomResultSet.next()) {
          roomIds.add(roomResultSet.getInt("roomId"));
        }
        booking.setRoomIds(roomIds);

        PreparedStatement eventStatement = conn.prepareStatement("SELECT eventId FROM eventsReservations WHERE bookingId = ?");
        eventStatement.setInt(1, bookingId);
        ResultSet eventResultSet = eventStatement.executeQuery();

        while (eventResultSet.next()) {
          eventIds.add(eventResultSet.getInt("eventId"));
        }
        booking.setEventIds(eventIds);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return booking;
  }
}


