package UserDashboard;

public class BookedEvent {
    private String eventId;
    private String eventName;
    private String eventDescription;
    private String eventLocation;
    private double eventPrice;
    private String eventImage;
    private String bookingDate;
    private String bookingId;
    private double bookingPrice;

    // Constructor
    public BookedEvent(String eventId, String eventName, String eventDescription,
            String eventLocation, double eventPrice, String eventImage,
            String bookingDate, double bookingPrice, String bookingId) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventLocation = eventLocation;
        this.eventPrice = eventPrice;
        this.eventImage = eventImage;
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.bookingPrice = bookingPrice;
    }

    // Getters and toString() method for better debugging
    public String getEventId() {
        return eventId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public double getEventPrice() {
        return eventPrice;
    }

    public String getEventImage() {
        return eventImage;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public double getBookingPrice() {
        return bookingPrice;
    }

    @Override
    public String toString() {
        return "BookedEvent{" +
                "eventId='" + eventId + '\'' +
                ", eventName='" + eventName + '\'' +
                ", eventDescription='" + eventDescription + '\'' +
                ", eventLocation='" + eventLocation + '\'' +
                ", eventPrice=" + eventPrice +
                ", eventImage='" + eventImage + '\'' +
                ", bookingDate='" + bookingDate + '\'' +
                ", bookingPrice=" + bookingPrice +
                '}';
    }
}
