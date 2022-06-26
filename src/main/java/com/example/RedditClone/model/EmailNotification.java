package com.example.RedditClone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailNotification {
    private String emailSubject;
    private String emailReceiver;
    private String emailBody;
}
