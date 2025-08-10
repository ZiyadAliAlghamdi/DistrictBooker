package org.example.capstone2.Service;

import lombok.RequiredArgsConstructor;
import org.example.capstone2.Model.Booking;
import org.example.capstone2.Model.Facility;
import org.example.capstone2.Model.User;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private final SimpleMailMessage mailMessage = new SimpleMailMessage();

    public void sendFeedbackMail(User user, Facility facility ,String text) {
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Feedback response —");
        String emailBody = "Dear " + user.getUsername() + ",\n\n" +
                           "Thank you for your feedback regarding " + facility.getName() + ".\n\n" +
                           "Admin's Response:\n" +
                           text + "\n\n" +
                           "Facility Details:\n" +
                           "  Name:        " + facility.getName() + "\n" +
                           "  Description: " + facility.getDescription() + "\n" +
                           "Best regards,\n" +
                           "DistrictBooker";
        mailMessage.setText(emailBody);
        javaMailSender.send(mailMessage);

    }

    public void sendBookingConfirmationEmail(Booking booking, User user, Facility facility) {
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Booking Confirmation — " + facility.getName());

        String text =
                "Dear " + user.getUsername() + ",\n\n" +
                        "Your booking for " + facility.getName() + " has been confirmed.\n\n" +
                        "Booking Details:\n" +
                        "  Start Time: " + booking.getStartTime() + "\n" +
                        "  End Time:   " + booking.getEndTime() + "\n" +
                        "  Status:     " + booking.getStatus() + "\n\n" +
                        "Thank you for your booking!\n" +
                        "DistrictBooker";

        mailMessage.setText(text);
        javaMailSender.send(mailMessage);
    }

    public void sendBookingAcceptedEmail(Booking booking, User user, Facility facility) {
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Booking Accepted — " + facility.getName());

        String text =
                "Dear " + user.getUsername() + ",\n\n" +
                        "Your booking for " + facility.getName() + " has been accepted.\n\n" +
                        "Booking Details:\n" +
                        "  Start Time: " + booking.getStartTime() + "\n" +
                        "  End Time:   " + booking.getEndTime() + "\n" +
                        "  Status:     " + booking.getStatus() + "\n\n" +
                        "We look forward to seeing you!\n" +
                        "DistrictBooker";

        mailMessage.setText(text);
        javaMailSender.send(mailMessage);
    }

    public void sendBookingCancelledEmail(Booking booking, User user, Facility facility) {
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Booking Cancelled — " + facility.getName());

        String text =
                "Dear " + user.getUsername() + ",\n\n" +
                        "Your booking for " + facility.getName() + " has been cancelled.\n\n" +
                        "Booking Details:\n" +
                        "  Start Time: " + booking.getStartTime() + "\n" +
                        "  End Time:   " + booking.getEndTime() + "\n" +
                        "  Status:     " + booking.getStatus() + "\n\n" +
                        "We hope to see you again soon.\n" +
                        "DistrictBooker";

        mailMessage.setText(text);
        javaMailSender.send(mailMessage);
    }
}
