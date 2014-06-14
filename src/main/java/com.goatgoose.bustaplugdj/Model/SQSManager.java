package com.goatgoose.bustaplugdj.Model;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import org.bukkit.Bukkit;

public class SQSManager {

    AmazonSQS sqsClient;

    public SQSManager(String queueURL) {
        sqsClient = new AmazonSQSClient(new BasicAWSCredentials("AKIAIZSGNVM2M6NRFUDA", "UIpQ4P/UfZJ5wSXlZT9nhcAmsGlXZ9ZeltqGsWxJ"));
        sqsClient.setRegion(Region.getRegion(Regions.US_WEST_2));

        for(Message message : sqsClient.receiveMessage(new ReceiveMessageRequest(queueURL)).getMessages()) {
            Bukkit.broadcastMessage(message.getBody());
        }
    }



}
